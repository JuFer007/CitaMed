# Observaciones del Backend — CitaMed

## Resumen general

El backend cumple con todos los requisitos funcionales solicitados, con un solo hallazgo de mejora en manejo de transacciones.

---

## 1. Arquitectura en capas ✅

Clara separación en tres capas organizadas por feature (`package-by-feature`):

| Capa | Ruta | Cantidad |
|------|------|----------|
| **Controller** | `Controller/{Paciente,Medico,Agenda,Administrativo,MicroControllers,Util}/` | 20 clases |
| **Service** | `Service/{Paciente,Medico,Agenda,Administrativo,MicroServicios}/` | 15 clases |
| **Repository** | `Repository/{Paciente,Medico,Agenda,Administrativo}/` | 14 interfaces |

## 2. Spring Data JPA (Hibernate) ✅

- Dependencia `spring-boot-starter-data-jpa` en `pom.xml`
- Todas las entidades anotadas con `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, relaciones `@ManyToOne`, `@OneToOne`, `@OneToMany`
- `ddl-auto=update` en `application.properties`

## 3. CRUD completos ✅

### Pacientes — `PacienteController.java`
| Método | Endpoint | Función |
|--------|----------|---------|
| `GET` | `/api/paciente` | Listar (con paginación y búsqueda) |
| `GET` | `/api/paciente/{id}` | Buscar por ID |
| `GET` | `/api/paciente/dni/{dni}` | Buscar por DNI |
| `POST` | `/api/paciente` | Crear |
| `PUT` | `/api/paciente/{id}` | Actualizar |
| `DELETE` | `/api/paciente/{id}` | Eliminación lógica |
| `PATCH` | `/api/paciente/{id}/estado` | Activar/desactivar |

### Médicos — `MedicoController.java`
| Método | Endpoint | Función |
|--------|----------|---------|
| `GET` | `/api/medico` | Listar |
| `GET` | `/api/medico/{id}` | Buscar por ID |
| `GET` | `/api/medico/especialidad/{id}` | Buscar por especialidad |
| `POST` | `/api/medico` | Crear |
| `PUT` | `/api/medico/{id}` | Actualizar |
| `PATCH` | `/api/medico/{id}/estado` | Activar/desactivar |

### Citas — `CitaController.java`
| Método | Endpoint | Función |
|--------|----------|---------|
| `GET` | `/api/cita` | Listar |
| `GET` | `/api/cita/{id}` | Buscar por ID |
| `GET` | `/api/cita/medico/{id}` | Buscar por médico |
| `GET` | `/api/cita/paciente/{id}` | Buscar por paciente |
| `POST` | `/api/cita` | Crear |
| `PATCH` | `/api/cita/{id}/reprogramar` | Reprogramar |
| `PATCH` | `/api/cita/{id}/cancelar` | Cancelar |
| `PATCH` | `/api/cita/{id}/completar` | Completar |
| `PATCH` | `/api/cita/{id}/no-asistio` | Marcar no asistió |

## 4. JPQL — Consultas personalizadas ✅ (más de 2)

| Archivo | Línea | Consulta |
|---------|-------|----------|
| `PacienteRepository.java` | 21-30 | `buscarConFiltros` — JPQL con filtro por término (nombre/apellido/dni) e inclusión de inactivos |
| `MedicoRepository.java` | 16-32 | `medicosActivos` — JPQL con GROUP BY, COUNT, DTO projection (`MedicoActivoDTO`) |
| `CitaRepository.java` | 31 | `pacientesActivos` — JPQL con COUNT DISTINCT |
| `CitaRepository.java` | 34-36 | `ultimasCitas` — JPQL con ORDER BY y DTO projection (`UltimaCitaDTO`) |
| `CitaRepository.java` | 38-40 | `citasPorEspecialidad` — JPQL con GROUP BY y DTO projection (`EspecialidadDTO`) |
| `CitaRepository.java` | 42-53 | `agendaHoyNative` — Native query SQL con JOIN múltiple |
| `PagoRepository.java` | 18-19 | `ingresos` — JPQL con SUM y COALESCE |
| `PagoRepository.java` | 20-23 | `ultimosPagos` — JPQL con DTO projection (`UltimoPagoDTO`) |

## 5. @Transactional ⚠️

### Usado correctamente en:
- `CitaService.save()` (línea 51)
- `CitaService.cancelar()` (línea 95)
- `CitaService.reprogramar()` (línea 131)
- `MedicoService.cambiarEstado()` (línea 143)

### Hallazgo: `PacienteService.save()` sin @Transactional ⚠️

**Archivo**: `Service/Paciente/PacienteService.java:46-68`

El método `save()` ejecuta **dos operaciones de escritura**:
1. `pacienteRepository.save(paciente)` (línea 61)
2. `historialMedicoRepository.save(historial)` (línea 65)

Si la segunda operación falla (error de BD, constraint, etc.), el paciente queda persistido sin su historial médico. Debería estar anotado con `@Transactional` para garantizar atomicidad.

## 6. Validaciones con jakarta.validation ✅

| Entidad | Anotaciones |
|---------|-------------|
| `Paciente.java` | `@NotBlank`, `@Size(max=50/150)`, `@Pattern` (DNI 8 dígitos, teléfono 9 dígitos), `@Email`, `@NotNull`, `@Past` |
| `Medico.java` | `@NotBlank`, `@Size(max=50/150)`, `@Pattern` (DNI 8 dígitos, teléfono 9 dígitos), `@Email`, `@NotNull`, `@Past` |
| `Cita.java` | `@NotNull`, `@NotBlank`, `@Size(max=200)` |

Además, `PacienteController` usa `@jakarta.validation.Valid` en los DTO de entrada (líneas 54 y 62).

## 7. Seguridad con Spring Security ✅

- **Autenticación**: `POST /api/auth/login` → `AuthService.login()` con BCrypt + JWT
- **Roles definidos** (`Rol.java`): `ADMIN`, `MEDICO`, `RECEPCIONISTA`, `ENFERMERO`, `LIMPIEZA` (5 roles, superando el mínimo de 2)
- **Autorización por endpoint** (`SecurityConfig.java`):
  - `/api/usuario/**` → solo `ADMIN`
  - `/api/medico/**`, `/api/horarioMedico/**`, `/api/dashboard/**` → `ADMIN`, `MEDICO`
  - `/api/cita/**` → `ADMIN`, `MEDICO`, `RECEPCIONISTA`
  - `/api/paciente/**` → `ADMIN`, `RECEPCIONISTA`, `ENFERMERO`, `MEDICO`
  - Endpoints públicos: `/api/auth/**`, `/api/reniec/**`, `/api/especialidad/**`, `/api/landing/**`, `/api/contacto/**`
- **JWT Filter**: `JwtFilter` extiende `OncePerRequestFilter`, validación con `JwtUtil` (JJWT 0.12.3)
- **Method Security**: `@EnableMethodSecurity(prePostEnabled = true)` habilitado

## 8. API REST con CORS ✅

Configurado en `SecurityConfig.java:71-81`:
- Orígenes permitidos: `http://localhost:4200`, `https://*.vercel.app`
- Métodos: `GET`, `POST`, `PUT`, `DELETE`, `PATCH`, `OPTIONS`
- Headers: `*`
- Credentials: `true`
- Aplica a toda la API (`/**`)

Controladores también anotados con `@CrossOrigin(origins = "*")`.

---

## Resumen de hallazgos

| # | Tipo | Descripción | Archivo |
|---|------|-------------|---------|
| 1 | ⚠️ Mejora | `PacienteService.save()` ejecuta 2 `save()` sin `@Transactional` | `Service/Paciente/PacienteService.java:46-68` |

Todo lo demás cumple satisfactoriamente.
