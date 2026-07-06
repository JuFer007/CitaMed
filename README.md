<div align="center">
  <img src="frontend/public/logoCitaMed.png" alt="CitaMed" width="100"/>
  <br><br>
  <p><strong>Sistema de gestión de consultas médicas con citas, pacientes, médicos, historial clínico y reportes</strong></p>

  ![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
  ![HTML](https://img.shields.io/badge/HTML-E34F26?logo=html5&logoColor=white)
  ![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?logo=typescript&logoColor=white)
  ![Angular](https://img.shields.io/badge/Angular-DD0031?logo=angular&logoColor=white)
  ![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-06B6D4?logo=tailwindcss&logoColor=white)
  ![Node.js](https://img.shields.io/badge/Node.js-339933?logo=node.js&logoColor=white)
  ![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
  ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?logo=springboot&logoColor=white)
</div>

---

# Documentación CitaMed

Sistema de Gestion de Consultas Medicas - CitaMed

---

## 1. Landing Page

Pagina principal publica del sistema.

Hero:

![hero](frontend/public/capturas/lading%20(1).png)

Especialidades:

![especialidades](frontend/public/capturas/lading%20(2).png)

Doctor destacado:

![doctor](frontend/public/capturas/lading%20(3).png)

Reserva de citas:

![reserva](frontend/public/capturas/lading%20(4).png)

Consultas / FAQ:

![consultas](frontend/public/capturas/lading%20(5).png)

Testimonios / Footer:

![testimonios](frontend/public/capturas/lading%20(6).png)

---

## 2. Login

Autenticacion de usuarios con JWT.

![login](frontend/public/capturas/login.png)

---

## 3. Dashboard

Vista principal con metricas del sistema.

Dashboard parte 1 - cards de estadisticas:

![dashboard1](frontend/public/capturas/dashboard1.png)

Dashboard parte 2 - ultimas citas, especialidades, agenda, medicos, pagos:

![dashboard2](frontend/public/capturas/dashboard2.png)

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

![pacientes](frontend/public/capturas/pacientes.png)

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

Listado de medicos | Modal de registro / edición
:---:|:---:
![medicos](frontend/public/capturas/medicosmodulo%20(1).png) | ![medicosModal](frontend/public/capturas/medicosmodulo%20(2).png)

Funcionalidades:
- Listar medicos con paginacion
- Buscar por nombre o DNI
- Filtrar por especialidad
- Registrar nuevo medico con foto
- Editar datos del medico (incluye foto)
- Cambiar especialidad
- Asignar consultorio
- Activar / Desactivar medico

---

## 6. Gestion de Especialidades

CRUD completo de especialidades.

![especialidades](frontend/public/capturas/especialidades.png)

Modal de especialidad:

![modalEspecialidades](frontend/public/capturas/modalEspecialidades.png)

Funcionalidades:
- Listar especialidades con paginacion
- Buscar por nombre
- Registrar nueva especialidad
- Editar especialidad
- Activar / Desactivar especialidad

---

## 7. Gestion de Consultorios

CRUD completo de consultorios.

Listado de consultorios | Modal de registro / edicion
:---:|:---:
![consultorios](frontend/public/capturas/consultorios%20(1).png) | ![consultoriosModal](frontend/public/capturas/consultorios%20(2).png)

Funcionalidades:
- Listar consultorios con paginacion
- Buscar por nombre
- Filtrar por disponibilidad
- Registrar nuevo consultorio
- Editar consultorio
- Activar / Desactivar consultorio

---

## 8. Gestion de Citas

CRUD completo y operaciones de estado.

Listado de citas:

![citas](frontend/public/capturas/citas.png)

Detalle de cita:

![detalle](frontend/public/capturas/citaInfo.png)

Informacion adicional:

![detalle2](frontend/public/capturas/citaInfo2.png)

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

## 9. Gestion de Horarios

Administracion de horarios por medico.

![horarios](frontend/public/capturas/horarioMedico.png)

Funcionalidades:
- Listar horarios por medico
- Agregar horario (dia, hora inicio, hora fin, consultorio)
- Editar horario
- Activar / Desactivar horario

---

## 10. Historial Medico

Consulta del historial clinico completo de los pacientes, con descarga de PDF.

Listado de pacientes e historial:

![historial](frontend/public/capturas/historialMedico.png)

Modal con detalle de historial medico de cada paciente | PDF generado del historial medico
:---:|:---:
![modal](frontend/public/capturas/modalHistorialmEDICO.png) | ![pdf](frontend/public/capturas/pdfHistorialMedico.png)

Funcionalidades:
- Listar pacientes con busqueda por nombre o DNI
- Ver historial completo (citas + diagnosticos) en modal
- Detalle de cada consulta con diagnostico, receta e indicaciones
- Descargar PDF del historial medico
- PDF se abre en nueva pestana con nombre estandarizado
- Acceso por roles ADMIN / MEDICO / RECEPCIONISTA
- Filtra solo pacientes del medico cuando el rol es MEDICO

---

## 11. Diagnósticos

Gestión de diagnósticos y recetas médicas asociados a las citas.

Listado de citas pendientes y atendidas:

![diagnosticos](frontend/public/capturas/diagnosticos.png)

Modal de atención / edición | PDF de receta médica
:---:|:---:
![modal](frontend/public/capturas/modalDiafnosticoEditaroAgregar.png) | ![receta](frontend/public/capturas/receta-diagnostico.png)

Funcionalidades:
- Listar citas pendientes y atendidas
- Filtrar por estado: Pendientes, Atendidas, Todas
- Buscar por paciente, médico o DNI
- Atender paciente: registrar enfermedad, descripción, receta e indicaciones
- Editar diagnóstico existente
- Descargar receta médica en PDF
- Acceso por roles ADMIN / MEDICO
- Validación de campos obligatorios (enfermedad)

---

## 12. Pagos

Gestión de pagos con generación de ticket PDF.

Listado de pagos | Modal de pago / ticket PDF
:---:|:---:
![pagos](frontend/public/capturas/pagos%20(1).png) | ![pagoModal](frontend/public/capturas/pagos%20(2).png)

Funcionalidades:
- Listar pagos con paginación y ordenamiento
- Buscar por paciente o DNI
- Crear pago asociado a una cita
- Ver detalle del pago
- Descargar ticket PDF del pago
- Acceso por roles ADMIN / MEDICO / RECEPCIONISTA

---

## 13. Consultas

Bandeja de consultas enviadas desde la landing page, con gestión de respuestas vía email.

Listado de consultas:

![consultas](frontend/public/capturas/consultas%20(1).png)

Modal de detalle y respuesta:

![consultaModal](frontend/public/capturas/consultas%20(2).png)

Funcionalidades:
- Listar consultas recibidas con paginación y ordenamiento
- Indicador visual de no leído / leído / respondido
- Ver detalle de la consulta (marca automáticamente como leída)
- Responder consulta con envío de email al paciente
- Acceso por roles ADMIN / MEDICO / RECEPCIONISTA

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
| Consultorios CRUD | Completo |
| Citas CRUD | Completo (+ estados) |
| Horarios | Completo |
| Historial Medico | Completo (+ PDF) |
| Diagnosticos CRUD | Completo (+ Receta PDF) |
| Pagos CRUD | Completo (+ Ticket PDF) |
| Consultas | Completo (+ respuesta por email) |
| Seguridad | Roles ADMIN / MEDICO / RECEPCIONISTA |
| Backend API | Spring Boot + JPA + MySQL |
| Frontend | Angular + PrimeNG + Tailwind |
