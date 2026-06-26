<div align="center">
  <img src="frontend/public/logoCitaMed.png" alt="CitaMed" width="100"/>
  <br><br>
  <p><strong>Sistema de gestión de consultas médicas con citas, pacientes, médicos, historial clínico y reportes</strong></p>

  ![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
  ![HTML](https://img.shields.io/badge/HTML-E34F26?logo=html5&logoColor=white)
  ![CSS](https://img.shields.io/badge/CSS-1572B6?logo=css3&logoColor=white)
  ![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?logo=typescript&logoColor=white)
  ![Angular](https://img.shields.io/badge/Angular-DD0031?logo=angular&logoColor=white)
  ![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-06B6D4?logo=tailwindcss&logoColor=white)
</div>

---

# Documentación CitaMed

Sistema de Gestion de Consultas Medicas - CitaMed

---

## 1. Landing Page

Pagina principal publica del sistema.

Hero:

![hero](capturas/lading1.png)

Especialidades:

![especialidades](capturas/lading2.png)

Doctor destacado:

![doctor](capturas/lading3.png)

Testimonios:

![testimonios](capturas/lading4.png)

Contacto / Footer:

![contacto](capturas/lading5.png)

---

## 2. Login

Autenticacion de usuarios con JWT.

![login](capturas/login.png)

---

## 3. Dashboard

Vista principal con metricas del sistema.

Dashboard parte 1 - cards de estadisticas:

![dashboard1](capturas/dashboard1.png)

Dashboard parte 2 - ultimas citas, especialidades, agenda, medicos, pagos:

![dashboard2](capturas/dashboard2.png)

El dashboard muestra:
- Citas de hoy
- Pacientes activos del mes
- Ingresos del mes
- Citas canceladas de la semana
- Total de pacientes registrados
- Total de medicos registrados
- Total de citas programadas
- Ultimas citas registradas
- Especialidades mas solicitadas
- Agenda del dia
- Medicos activos (top por citas)
- Ultimos pagos

---

## 4. Gestion de Pacientes

CRUD completo de pacientes.

![pacientes](capturas/pacientes.png)

Funcionalidades:
- Listar pacientes con paginacion
- Buscar por nombre, apellido o DNI
- Incluir pacientes inactivos
- Registrar nuevo paciente
- Editar datos del paciente
- Eliminar (soft-delete) / Activar / Desactivar

---

## 5. Gestion de Medicos

CRUD completo de medicos.

![medicos](capturas/medicos.png)

Funcionalidades:
- Listar medicos con paginacion
- Buscar por nombre o DNI
- Filtrar por especialidad
- Registrar nuevo medico
- Editar datos del medico
- Cambiar especialidad
- Activar / Desactivar medico

---

## 6. Gestion de Especialidades

CRUD completo de especialidades.

![especialidades](capturas/especialidades.png)

Modal de especialidad:

![modalEspecialidades](capturas/modalEspecialidades.png)

Funcionalidades:
- Listar especialidades con paginacion
- Buscar por nombre
- Registrar nueva especialidad
- Editar especialidad
- Activar / Desactivar especialidad

---

## 7. Gestion de Citas

CRUD completo y operaciones de estado.

Listado de citas:

![citas](capturas/citas.png)

Detalle de cita:

![detalle](capturas/citaInfo.png)

Informacion adicional:

![detalle2](capturas/citaInfo2.png)

Funcionalidades:
- Listar citas con paginacion
- Buscar por paciente, medico o DNI
- Filtrar por estado y rango de fechas
- Crear cita (paciente + medico + fecha + consultorio)
- Ver detalle completo de la cita
- Cancelar cita
- Completar cita (marcar como atendida)
- Reprogramar cita
- Marcar como "no asistio"
- Eliminar cita

---

## 8. Gestion de Horarios

Administracion de horarios por medico.

![horarios](capturas/horarioMedico.png)

Funcionalidades:
- Listar horarios por medico
- Agregar horario (dia, hora inicio, hora fin, consultorio)
- Editar horario
- Activar / Desactivar horario

---

---

## 9. Historial Medico

Consulta del historial clinico completo de los pacientes, con descarga de PDF.

Listado de pacientes e historial:

![historial](capturas/historialMedico.png)

Modal con detalle de atenciones y diagnosticos | PDF generado del historial medico
:---:|:---:
![modal](capturas/modalHistorialmEDICO.png) | ![pdf](capturas/pdfHistorialMedico.png)

Funcionalidades:
- Listar pacientes con busqueda por nombre o DNI
- Ver historial completo (citas + diagnosticos) en modal
- Detalle de cada consulta con diagnostico, receta e indicaciones
- Descargar PDF del historial medico
- PDF se abre en nueva pestana con nombre estandarizado
- Acceso por roles ADMIN / MEDICO / RECEPCIONISTA
- Filtra solo pacientes del medico cuando el rol es MEDICO

---

## Resumen de Funcionalidades

| Modulo | Estado |
|--------|--------|
| Landing Page | Completo |
| Login / Autenticacion | JWT + roles |
| Dashboard | Metricas completas |
| Pacientes CRUD | Completo |
| Medicos CRUD | Completo |
| Especialidades CRUD | Completo |
| Citas CRUD | Completo (+ estados) |
| Horarios | Completo |
| Historial Medico | Completo (+ PDF) |
| Seguridad | Roles ADMIN / MEDICO / RECEPCIONISTA |
| Backend API | Spring Boot + JPA + MySQL |
| Frontend | Angular + PrimeNG + Tailwind |
