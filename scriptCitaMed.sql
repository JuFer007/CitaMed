create database CitaMed;

use CitaMed;


-- ============================================================
-- SCRIPT DE INSERCIÓN DE DATOS - DocCenter
-- ============================================================

-- ========================
-- 1. USUARIOS (25)
-- ========================
INSERT INTO Usuarios (user_name, password, rol, activo) VALUES
('admin01', '$2a$10$hashAdmin01', 'ADMIN', true),
('recep01', '$2a$10$hashRecep01', 'RECEPCIONISTA', true),
('recep02', '$2a$10$hashRecep02', 'RECEPCIONISTA', true),
('med.garcia', '$2a$10$hashMed01', 'MEDICO', true),
('med.torres', '$2a$10$hashMed02', 'MEDICO', true),
('med.ramirez', '$2a$10$hashMed03', 'MEDICO', true),
('med.flores', '$2a$10$hashMed04', 'MEDICO', true),
('med.castro', '$2a$10$hashMed05', 'MEDICO', true),
('med.herrera', '$2a$10$hashMed06', 'MEDICO', true),
('med.vega', '$2a$10$hashMed07', 'MEDICO', true),
('med.mora', '$2a$10$hashMed08', 'MEDICO', true),
('med.rivas', '$2a$10$hashMed09', 'MEDICO', true),
('med.pinto', '$2a$10$hashMed10', 'MEDICO', true),
('enf.lopez', '$2a$10$hashEnf01', 'ENFERMERO', true),
('enf.perez', '$2a$10$hashEnf02', 'ENFERMERO', true),
('enf.ruiz', '$2a$10$hashEnf03', 'ENFERMERO', true),
('enf.diaz', '$2a$10$hashEnf04', 'ENFERMERO', true),
('enf.ortiz', '$2a$10$hashEnf05', 'ENFERMERO', true),
('limpieza.nunez', '$2a$10$hashLimp01', 'LIMPIEZA', true),
('limpieza.salinas', '$2a$10$hashLimp02', 'LIMPIEZA', true),
('limpieza.mendoza', '$2a$10$hashLimp03', 'LIMPIEZA', true),
('limpieza.fuentes', '$2a$10$hashLimp04', 'LIMPIEZA', true),
('admin02', '$2a$10$hashAdmin02', 'ADMIN', true),
('recep03', '$2a$10$hashRecep03', 'RECEPCIONISTA', false),
('med.aguilar', '$2a$10$hashMed11', 'MEDICO', true);

-- ========================
-- 2. AREAS (6)
-- ========================
INSERT INTO Areas (nombre, descripcion) VALUES
('Medicina General', 'Área de atención médica general y consultas de rutina'),
('Cardiología', 'Área especializada en enfermedades del corazón y sistema cardiovascular'),
('Pediatría', 'Área dedicada a la atención médica de niños y adolescentes'),
('Ginecología', 'Área especializada en salud femenina y obstetricia'),
('Traumatología', 'Área de atención de lesiones musculoesqueléticas y fracturas'),
('Neurología', 'Área especializada en enfermedades del sistema nervioso');

-- ========================
-- 3. CONSULTORIOS (12)
-- ========================
INSERT INTO Consultorios (numero, descripcion, disponible, area_id) VALUES
('C-101', 'Consultorio de Medicina General N°1', true, 1),
('C-102', 'Consultorio de Medicina General N°2', true, 1),
('C-103', 'Consultorio de Medicina General N°3', false, 1),
('C-201', 'Consultorio de Cardiología N°1', true, 2),
('C-202', 'Consultorio de Cardiología N°2', true, 2),
('C-301', 'Consultorio de Pediatría N°1', true, 3),
('C-302', 'Consultorio de Pediatría N°2', false, 3),
('C-401', 'Consultorio de Ginecología N°1', true, 4),
('C-402', 'Consultorio de Ginecología N°2', true, 4),
('C-501', 'Consultorio de Traumatología N°1', true, 5),
('C-601', 'Consultorio de Neurología N°1', true, 6),
('C-602', 'Consultorio de Neurología N°2', true, 6);

-- ========================
-- 4. EMPLEADOS (8)
-- ========================
INSERT INTO Empleados (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, cargo, salario, fecha_ingreso, activo, usuario_id) VALUES
('Carlos', 'Mendoza', 'Quispe', '45678901', '987654321', 'Av. Los Olivos 123, Chiclayo', 'carlos.mendoza@doccenter.com', '1985-03-15', 'MASCULINO', 'Administrador General', 4500.00, '2018-01-10', true, 1),
('María', 'López', 'Sánchez', '45678902', '987654322', 'Jr. Los Pinos 456, Chiclayo', 'maria.lopez@doccenter.com', '1990-07-22', 'FEMENINO', 'Recepcionista', 1800.00, '2020-03-01', true, 2),
('Ana', 'Pérez', 'Castillo', '45678903', '987654323', 'Calle Manco Cápac 789, Chiclayo', 'ana.perez@doccenter.com', '1992-11-05', 'FEMENINO', 'Recepcionista', 1800.00, '2021-06-15', true, 3),
('José', 'Ruiz', 'Vargas', '45678910', '987654330', 'Av. Salaverry 321, Chiclayo', 'jose.ruiz@doccenter.com', '1988-04-18', 'MASCULINO', 'Enfermero', 2200.00, '2019-08-01', true, 14),
('Lucía', 'Díaz', 'Flores', '45678911', '987654331', 'Jr. Amazonas 654, Chiclayo', 'lucia.diaz@doccenter.com', '1991-09-25', 'FEMENINO', 'Enfermera', 2200.00, '2020-01-15', true, 16),
('Roberto', 'Núñez', 'Paredes', '45678914', '987654334', 'Av. Grau 987, Chiclayo', 'roberto.nunez@doccenter.com', '1987-12-10', 'MASCULINO', 'Personal de Limpieza', 1500.00, '2018-05-01', true, 19),
('Elena', 'Mendoza', 'Cruz', '45678916', '987654336', 'Calle 7 de Enero 147, Chiclayo', 'elena.mendoza@doccenter.com', '1989-06-30', 'FEMENINO', 'Personal de Limpieza', 1500.00, '2019-11-01', true, 21),
('Pedro', 'Aguilar', 'Ríos', '45678918', '987654338', 'Av. Bolognesi 258, Chiclayo', 'pedro.aguilar@doccenter.com', '1983-02-14', 'MASCULINO', 'Administrador', 3800.00, '2017-03-20', true, 23);

-- ========================
-- 5. ESPECIALIDADES (10)
-- ========================
INSERT INTO Especialidades (nombre, descripcion) VALUES
('Medicina General', 'Atención médica primaria y consultas generales de salud'),
('Cardiología', 'Diagnóstico y tratamiento de enfermedades cardiovasculares'),
('Pediatría', 'Atención médica integral de niños desde el nacimiento hasta la adolescencia'),
('Ginecología y Obstetricia', 'Salud reproductiva femenina, embarazo y parto'),
('Traumatología y Ortopedia', 'Lesiones y enfermedades del aparato locomotor'),
('Neurología', 'Enfermedades del sistema nervioso central y periférico'),
('Dermatología', 'Enfermedades de la piel, cabello y uñas'),
('Endocrinología', 'Trastornos hormonales y metabólicos'),
('Gastroenterología', 'Enfermedades del sistema digestivo'),
('Oftalmología', 'Enfermedades y cirugías del ojo');

-- ========================
-- 6. MEDICOS (10)
-- ========================
INSERT INTO Medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, especialidad_id, usuario_id) VALUES
('Luis', 'García', 'Montoya', '12345601', '912345601', 'Av. Pardo 101, Chiclayo', 'luis.garcia@doccenter.com', '1978-05-12', 'MASCULINO', 'CMP-045231', true, 1, 4),
('Rosa', 'Torres', 'Gutiérrez', '12345602', '912345602', 'Jr. Colón 202, Chiclayo', 'rosa.torres@doccenter.com', '1980-09-18', 'FEMENINO', 'CMP-038742', true, 2, 5),
('Miguel', 'Ramírez', 'Delgado', '12345603', '912345603', 'Calle Real 303, Chiclayo', 'miguel.ramirez@doccenter.com', '1975-03-27', 'MASCULINO', 'CMP-029183', true, 3, 6),
('Carmen', 'Flores', 'Espinoza', '12345604', '912345604', 'Av. Venezuela 404, Chiclayo', 'carmen.flores@doccenter.com', '1982-07-08', 'FEMENINO', 'CMP-051647', true, 4, 7),
('Jorge', 'Castro', 'Huamán', '12345605', '912345605', 'Jr. Tacna 505, Chiclayo', 'jorge.castro@doccenter.com', '1977-11-14', 'MASCULINO', 'CMP-034829', true, 5, 8),
('Patricia', 'Herrera', 'León', '12345606', '912345606', 'Av. Balta 606, Chiclayo', 'patricia.herrera@doccenter.com', '1983-01-30', 'FEMENINO', 'CMP-062318', true, 6, 9),
('Fernando', 'Vega', 'Quispe', '12345607', '912345607', 'Calle Elías Aguirre 707, Chiclayo', 'fernando.vega@doccenter.com', '1979-06-22', 'MASCULINO', 'CMP-041095', true, 7, 10),
('Sandra', 'Mora', 'Paredes', '12345608', '912345608', 'Jr. Torres Paz 808, Chiclayo', 'sandra.mora@doccenter.com', '1981-10-03', 'FEMENINO', 'CMP-057382', true, 8, 11),
('Héctor', 'Rivas', 'Cárdenas', '12345609', '912345609', 'Av. José Balta 909, Chiclayo', 'hector.rivas@doccenter.com', '1976-04-17', 'MASCULINO', 'CMP-027461', true, 9, 12),
('Gabriela', 'Pinto', 'Salinas', '12345610', '912345610', 'Calle Siete de Enero 1010, Chiclayo', 'gabriela.pinto@doccenter.com', '1984-08-25', 'FEMENINO', 'CMP-068754', true, 1, 13);

-- ========================
-- 7. HORARIOS MEDICOS (20)
-- ========================
INSERT INTO Horarios_Medicos (dia, hora_inicio, hora_fin, activo, medico_id, consultorio_id) VALUES
('LUNES',    '08:00', '13:00', true, 1, 1),
('MIERCOLES','08:00', '13:00', true, 1, 1),
('VIERNES',  '08:00', '12:00', true, 1, 2),
('MARTES',   '09:00', '14:00', true, 2, 4),
('JUEVES',   '09:00', '14:00', true, 2, 4),
('SABADO',   '08:00', '12:00', true, 2, 5),
('LUNES',    '14:00', '19:00', true, 3, 6),
('MIERCOLES','14:00', '19:00', true, 3, 6),
('VIERNES',  '14:00', '19:00', true, 3, 6),
('MARTES',   '08:00', '13:00', true, 4, 8),
('JUEVES',   '08:00', '13:00', true, 4, 8),
('SABADO',   '09:00', '12:00', true, 4, 9),
('LUNES',    '08:00', '14:00', true, 5, 10),
('MIERCOLES','08:00', '14:00', true, 5, 10),
('MARTES',   '15:00', '20:00', true, 6, 11),
('JUEVES',   '15:00', '20:00', true, 6, 11),
('VIERNES',  '15:00', '20:00', true, 6, 12),
('LUNES',    '09:00', '14:00', true, 7, 2),
('MIERCOLES','09:00', '14:00', true, 8, 2),
('VIERNES',  '09:00', '13:00', true, 9, 1);

-- ========================
-- 8. PACIENTES (30)
-- ========================
INSERT INTO Pacientes (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, grupo_sanguineo) VALUES
('Juan Carlos', 'Quispe', 'Mamani', '71234501', '951234501', 'Av. Agricultura 101, Chiclayo', 'jc.quispe@gmail.com', '1990-02-14', 'MASCULINO', 'O_POSITIVO'),
('María Elena', 'Soto', 'Vargas', '71234502', '951234502', 'Jr. Los Álamos 202, Chiclayo', 'maria.soto@gmail.com', '1985-06-23', 'FEMENINO', 'A_POSITIVO'),
('Pedro Pablo', 'Huamán', 'Quispe', '71234503', '951234503', 'Calle Los Fresnos 303, Chiclayo', 'pedro.huaman@gmail.com', '1978-11-05', 'MASCULINO', 'B_POSITIVO'),
('Lucía Rosa', 'Paredes', 'Torres', '71234504', '951234504', 'Av. Fitzcarrald 404, Chiclayo', 'lucia.paredes@gmail.com', '1995-03-18', 'FEMENINO', 'AB_POSITIVO'),
('Carlos Alberto', 'Mendoza', 'López', '71234505', '951234505', 'Jr. San Martín 505, Chiclayo', 'carlos.mendoza.p@gmail.com', '1988-07-30', 'MASCULINO', 'O_NEGATIVO'),
('Ana Sofía', 'Rojas', 'Castro', '71234506', '951234506', 'Calle Dos de Mayo 606, Chiclayo', 'ana.rojas@gmail.com', '1992-09-12', 'FEMENINO', 'A_NEGATIVO'),
('Roberto Jesús', 'Vargas', 'Díaz', '71234507', '951234507', 'Av. Sáenz Peña 707, Chiclayo', 'roberto.vargas@gmail.com', '1975-01-08', 'MASCULINO', 'B_NEGATIVO'),
('Isabel Cristina', 'Chávez', 'Morales', '71234508', '951234508', 'Jr. Ladislao Espinar 808, Chiclayo', 'isabel.chavez@gmail.com', '1983-05-27', 'FEMENINO', 'O_POSITIVO'),
('Diego Alejandro', 'Fuentes', 'Herrera', '71234509', '951234509', 'Calle Pedro Ruiz 909, Chiclayo', 'diego.fuentes@gmail.com', '1998-12-03', 'MASCULINO', 'A_POSITIVO'),
('Valentina', 'Espinoza', 'Núñez', '71234510', '951234510', 'Av. Leguía 1010, Chiclayo', 'valentina.espinoza@gmail.com', '2001-04-16', 'FEMENINO', 'AB_NEGATIVO'),
('Andrés Felipe', 'Llontop', 'Bances', '71234511', '951234511', 'Jr. Colón 111, Chiclayo', 'andres.llontop@gmail.com', '1970-08-21', 'MASCULINO', 'O_POSITIVO'),
('Gabriela Noemí', 'Zuloeta', 'Carrasco', '71234512', '951234512', 'Av. Pedro Ruiz 222, Chiclayo', 'gabriela.zuloeta@gmail.com', '1987-10-09', 'FEMENINO', 'B_POSITIVO'),
('Ricardo Manuel', 'Gonzáles', 'Muro', '71234513', '951234513', 'Calle Izaga 333, Chiclayo', 'ricardo.gonzales@gmail.com', '1993-02-28', 'MASCULINO', 'A_POSITIVO'),
('Sofía Valentina', 'Chafloque', 'Solís', '71234514', '951234514', 'Jr. 8 de Octubre 444, Chiclayo', 'sofia.chafloque@gmail.com', '2000-06-14', 'FEMENINO', 'O_POSITIVO'),
('Martín Eduardo', 'Idrogo', 'Bustamante', '71234515', '951234515', 'Av. Los Incas 555, Chiclayo', 'martin.idrogo@gmail.com', '1965-11-30', 'MASCULINO', 'A_NEGATIVO'),
('Patricia Luz', 'Coronado', 'Díaz', '71234516', '951234516', 'Calle Real 666, Chiclayo', 'patricia.coronado@gmail.com', '1980-03-07', 'FEMENINO', 'B_POSITIVO'),
('Eduardo Jesús', 'Saavedra', 'Neyra', '71234517', '951234517', 'Jr. San José 777, Chiclayo', 'eduardo.saavedra@gmail.com', '1972-07-19', 'MASCULINO', 'O_POSITIVO'),
('Claudia Beatriz', 'Segura', 'Montoya', '71234518', '951234518', 'Av. Chiclayo 888, Chiclayo', 'claudia.segura@gmail.com', '1996-09-02', 'FEMENINO', 'AB_POSITIVO'),
('Marco Antonio', 'Becerra', 'Villanueva', '71234519', '951234519', 'Calle Los Pinos 999, Chiclayo', 'marco.becerra@gmail.com', '1982-01-25', 'MASCULINO', 'A_POSITIVO'),
('Natalia Andrea', 'Cubas', 'Mejía', '71234520', '951234520', 'Jr. Victor Raúl 1100, Chiclayo', 'natalia.cubas@gmail.com', '1991-05-11', 'FEMENINO', 'O_NEGATIVO'),
('César Augusto', 'Fernández', 'Paz', '71234521', '951234521', 'Av. Chinchaysuyo 200, Chiclayo', 'cesar.fernandez@gmail.com', '1969-03-15', 'MASCULINO', 'B_POSITIVO'),
('Milagros del Carmen', 'Puelles', 'Asalde', '71234522', '951234522', 'Jr. Los Jazmines 300, Chiclayo', 'milagros.puelles@gmail.com', '1976-08-29', 'FEMENINO', 'O_POSITIVO'),
('Jhon Alexander', 'Guerrero', 'Rivadeneyra', '71234523', '951234523', 'Calle Manco Cápac 400, Chiclayo', 'jhon.guerrero@gmail.com', '1999-12-17', 'MASCULINO', 'A_POSITIVO'),
('Fiorella Milagros', 'Tello', 'Perales', '71234524', '951234524', 'Av. Venezuela 500, Chiclayo', 'fiorella.tello@gmail.com', '2003-04-05', 'FEMENINO', 'AB_POSITIVO'),
('Alejandro Luis', 'Santisteban', 'Cabrejos', '71234525', '951234525', 'Jr. Elías Aguirre 600, Chiclayo', 'alejandro.santisteban@gmail.com', '1960-06-20', 'MASCULINO', 'O_POSITIVO'),
('Yesica Pamela', 'Falla', 'Medina', '71234526', '951234526', 'Calle Torres Paz 700, Chiclayo', 'yesica.falla@gmail.com', '1994-10-08', 'FEMENINO', 'B_NEGATIVO'),
('Hugo Armando', 'Bravo', 'Alcántara', '71234527', '951234527', 'Av. Grecia 800, Chiclayo', 'hugo.bravo@gmail.com', '1973-02-14', 'MASCULINO', 'A_NEGATIVO'),
('Rosario Esperanza', 'Aliaga', 'Delgado', '71234528', '951234528', 'Jr. Balta 900, Chiclayo', 'rosario.aliaga@gmail.com', '1968-07-03', 'FEMENINO', 'O_POSITIVO'),
('Kevin Sebastián', 'Heredia', 'Campos', '71234529', '951234529', 'Calle Junín 1000, Chiclayo', 'kevin.heredia@gmail.com', '2005-11-28', 'MASCULINO', 'B_POSITIVO'),
('Lorena Patricia', 'Valderrama', 'Chirinos', '71234530', '951234530', 'Av. Mariscal Castilla 1100, Chiclayo', 'lorena.valderrama@gmail.com', '1986-04-22', 'FEMENINO', 'A_POSITIVO');

-- ========================
-- 9. HISTORIALES MEDICOS (30)
-- ========================
INSERT INTO Historiales_Medicos (paciente_id) VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),
(21),(22),(23),(24),(25),(26),(27),(28),(29),(30);

-- ========================
-- 10. CITAS (35)
-- ========================
INSERT INTO Citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
(1, 1, 1, '2025-08-04 08:00:00', 'Dolor de cabeza persistente y mareos', 'ATENDIDA'),
(2, 2, 4, '2025-08-05 09:00:00', 'Revisión de presión arterial alta', 'ATENDIDA'),
(3, 3, 6, '2025-08-05 14:00:00', 'Control pediátrico de niño de 5 años', 'ATENDIDA'),
(4, 4, 8, '2025-08-06 08:00:00', 'Consulta por irregularidades menstruales', 'ATENDIDA'),
(5, 5, 10, '2025-08-06 09:00:00', 'Dolor en rodilla derecha tras caída', 'ATENDIDA'),
(6, 6, 11, '2025-08-07 15:00:00', 'Episodios de migraña frecuentes', 'ATENDIDA'),
(7, 1, 1, '2025-08-07 08:00:00', 'Tos persistente y fiebre leve', 'ATENDIDA'),
(8, 2, 4, '2025-08-08 09:00:00', 'Palpitaciones y cansancio', 'ATENDIDA'),
(9, 7, 2, '2025-08-11 09:00:00', 'Dermatitis en brazos', 'ATENDIDA'),
(10, 8, 2, '2025-08-12 09:00:00', 'Fatiga extrema y aumento de peso', 'ATENDIDA'),
(11, 9, 1, '2025-08-12 08:00:00', 'Náuseas y dolor abdominal', 'ATENDIDA'),
(12, 1, 2, '2025-08-13 08:00:00', 'Chequeo médico general', 'ATENDIDA'),
(13, 3, 6, '2025-08-13 14:00:00', 'Fiebre alta en niño de 8 años', 'ATENDIDA'),
(14, 4, 9, '2025-08-14 08:00:00', 'Control de embarazo mes 5', 'ATENDIDA'),
(15, 5, 10, '2025-08-14 08:00:00', 'Dolor lumbar crónico', 'ATENDIDA'),
(16, 10, 1, '2025-08-18 08:00:00', 'Resfriado y dolor de garganta', 'ATENDIDA'),
(17, 6, 11, '2025-08-19 15:00:00', 'Entumecimiento en extremidades', 'ATENDIDA'),
(18, 2, 5, '2025-08-19 09:00:00', 'Control post-operatorio cardíaco', 'ATENDIDA'),
(19, 1, 1, '2025-08-20 08:00:00', 'Gastritis y acidez estomacal', 'ATENDIDA'),
(20, 4, 8, '2025-08-20 08:00:00', 'Revisión de quiste ovárico', 'ATENDIDA'),
(21, 3, 6, '2025-08-21 14:00:00', 'Vacunación y control de crecimiento', 'ATENDIDA'),
(22, 9, 1, '2025-08-21 08:00:00', 'Colon irritable, control mensual', 'ATENDIDA'),
(23, 5, 10, '2025-08-25 08:00:00', 'Esguince de tobillo izquierdo', 'ATENDIDA'),
(24, 7, 2, '2025-08-26 09:00:00', 'Acné y problemas en piel grasa', 'ATENDIDA'),
(25, 8, 2, '2025-08-26 09:00:00', 'Diabetes tipo 2, control trimestral', 'ATENDIDA'),
(26, 10, 2, '2025-08-27 09:00:00', 'Dolor de espalda y fatiga', 'ATENDIDA'),
(27, 6, 12, '2025-08-27 15:00:00', 'Vértigo y pérdida de equilibrio', 'ATENDIDA'),
(28, 1, 1, '2025-08-28 08:00:00', 'Presión alta y dolor de cabeza', 'ATENDIDA'),
(29, 3, 6, '2025-09-01 14:00:00', 'Control de asma infantil', 'ATENDIDA'),
(30, 2, 4, '2025-09-02 09:00:00', 'Arritmia, control semestral', 'ATENDIDA'),
(1, 10, 1, '2026-04-10 08:00:00', 'Control médico trimestral', 'PROGRAMADA'),
(5, 5, 10, '2026-04-11 08:00:00', 'Revisión de fractura curada', 'PROGRAMADA'),
(15, 8, 2, '2026-04-11 09:00:00', 'Hipotiroidismo, control mensual', 'PROGRAMADA'),
(22, 9, 1, '2026-04-14 08:00:00', 'Gastroenterología, seguimiento', 'PROGRAMADA'),
(28, 2, 4, '2026-04-15 09:00:00', 'Hipertensión, ajuste de tratamiento', 'PROGRAMADA');

-- ========================
-- 11. PAGOS (30)
-- ========================
INSERT INTO Pagos (cita_id, monto, metodo_pago, estado, fecha_pago) VALUES
(1, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-04 08:05:00'),
(2, 120.00, 'TARJETA', 'PAGADO', '2025-08-05 09:10:00'),
(3, 90.00, 'EFECTIVO', 'PAGADO', '2025-08-05 14:05:00'),
(4, 100.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-06 08:05:00'),
(5, 110.00, 'EFECTIVO', 'PAGADO', '2025-08-06 09:05:00'),
(6, 130.00, 'TARJETA', 'PAGADO', '2025-08-07 15:05:00'),
(7, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-07 08:05:00'),
(8, 120.00, 'EFECTIVO', 'PAGADO', '2025-08-08 09:05:00'),
(9, 100.00, 'TARJETA', 'PAGADO', '2025-08-11 09:05:00'),
(10, 150.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-12 09:05:00'),
(11, 90.00, 'EFECTIVO', 'PAGADO', '2025-08-12 08:05:00'),
(12, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-13 08:05:00'),
(13, 90.00, 'TARJETA', 'PAGADO', '2025-08-13 14:05:00'),
(14, 100.00, 'EFECTIVO', 'PAGADO', '2025-08-14 08:05:00'),
(15, 110.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-14 08:05:00'),
(16, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-18 08:05:00'),
(17, 130.00, 'TARJETA', 'PAGADO', '2025-08-19 15:05:00'),
(18, 120.00, 'TARJETA', 'PAGADO', '2025-08-19 09:05:00'),
(19, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-20 08:05:00'),
(20, 100.00, 'EFECTIVO', 'PAGADO', '2025-08-20 08:05:00'),
(21, 90.00, 'EFECTIVO', 'PAGADO', '2025-08-21 14:05:00'),
(22, 90.00, 'TARJETA', 'PAGADO', '2025-08-21 08:05:00'),
(23, 110.00, 'EFECTIVO', 'PAGADO', '2025-08-25 08:05:00'),
(24, 100.00, 'TARJETA', 'PAGADO', '2025-08-26 09:05:00'),
(25, 150.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-26 09:05:00'),
(26, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-27 09:05:00'),
(27, 130.00, 'TARJETA', 'PAGADO', '2025-08-27 15:05:00'),
(28, 80.00, 'EFECTIVO', 'PAGADO', '2025-08-28 08:05:00'),
(29, 90.00, 'EFECTIVO', 'PAGADO', '2025-09-01 14:05:00'),
(30, 120.00, 'TARJETA', 'PAGADO', '2025-09-02 09:05:00');

-- ========================
-- 12. CONSULTAS MEDICAS (30)
-- ========================
INSERT INTO Consultas_Medicas (cita_id, historial_id, observaciones, peso, talla, presion_arterial, temperatura) VALUES
(1, 1, 'Paciente refiere cefalea tensional. Se indica descanso y analgésicos.', 75.5, 1.72, '120/80', '36.5'),
(2, 2, 'Hipertensión en seguimiento. Se ajusta dosis de antihipertensivo.', 82.0, 1.65, '150/95', '36.8'),
(3, 3, 'Niño con desarrollo adecuado para su edad. Vacunación al día.', 18.5, 1.10, '90/60', '36.4'),
(4, 4, 'Ciclos irregulares. Se solicita ecografía pélvica.', 58.0, 1.63, '110/70', '36.6'),
(5, 5, 'Contusión en rodilla derecha. Se indica reposo y antiinflamatorio.', 88.0, 1.78, '125/82', '36.7'),
(6, 6, 'Migraña crónica con aura. Se inicia profilaxis con propranolol.', 62.0, 1.68, '118/76', '36.5'),
(7, 7, 'Infección respiratoria alta. Antibioticoterapia por 7 días.', 70.0, 1.70, '118/78', '37.8'),
(8, 8, 'Palpitaciones por estrés. ECG normal. Se recomienda reducir cafeína.', 65.0, 1.60, '130/85', '36.6'),
(9, 9, 'Dermatitis atópica leve. Se indica crema con corticoide tópico.', 55.0, 1.55, '112/72', '36.5'),
(10, 10, 'Hipotiroidismo. Se inicia levotiroxina. Control en 4 semanas.', 78.0, 1.62, '115/75', '36.4'),
(11, 11, 'Gastritis crónica. Se pauta omeprazol y dieta blanda.', 90.0, 1.80, '128/84', '36.6'),
(12, 12, 'Chequeo general sin hallazgos patológicos. Exámenes de rutina normales.', 68.0, 1.75, '115/75', '36.5'),
(13, 13, 'Amigdalitis bacteriana. Amoxicilina 500mg cada 8h por 7 días.', 25.0, 1.30, '95/60', '38.5'),
(14, 14, 'Embarazo semana 22. Feto en presentación cefálica. Todo normal.', 66.0, 1.61, '108/68', '36.5'),
(15, 15, 'Lumbalgia mecánica. Fisioterapia y relajantes musculares.', 95.0, 1.82, '130/88', '36.6'),
(16, 16, 'Faringoamigdalitis viral. Tratamiento sintomático, reposo e hidratación.', 55.0, 1.58, '108/70', '37.5'),
(17, 17, 'Neuropatía periférica leve en manos. Vitaminas B y control en 1 mes.', 72.0, 1.74, '120/80', '36.5'),
(18, 18, 'Post-operatorio de bypass coronario. Evolución favorable. Sin complicaciones.', 80.0, 1.69, '125/82', '36.7'),
(19, 19, 'Gastritis aguda por AINES. Se retira ibuprofeno y se inicia omeprazol.', 77.0, 1.76, '122/80', '36.6'),
(20, 20, 'Quiste ovárico funcional de 3cm. Control ecográfico en 2 meses.', 60.0, 1.64, '110/70', '36.5'),
(21, 21, 'Control de crecimiento dentro de percentiles normales. Vacuna triple viral.', 32.0, 1.42, '95/62', '36.4'),
(22, 22, 'SII con predominio diarreico. Se ajusta dieta y se agrega probióticos.', 68.0, 1.65, '118/76', '36.5'),
(23, 23, 'Esguince grado II tobillo izquierdo. Inmovilización 10 días.', 72.0, 1.71, '120/80', '36.6'),
(24, 24, 'Acné grado 2. Tretinoína tópica nocturna y antibiótico oral.', 58.0, 1.60, '110/72', '36.5'),
(25, 25, 'Diabetes tipo 2 descompensada. Ajuste de metformina e insulina basal.', 85.0, 1.66, '138/90', '36.7'),
(26, 26, 'Lumbalgia por sobrecarga laboral. Fisioterapia y cambio ergonómico.', 62.0, 1.59, '115/75', '36.5'),
(27, 27, 'Vértigo posicional benigno. Maniobra de Epley. Mejoría inmediata.', 88.0, 1.80, '122/80', '36.6'),
(28, 28, 'HTA no controlada. Se añade amlodipino al tratamiento actual.', 74.0, 1.70, '158/100', '36.8'),
(29, 29, 'Asma bronquial en niño. Salbutamol de rescate y budesonida inhalada.', 29.0, 1.35, '92/60', '36.5'),
(30, 30, 'Arritmia supraventricular estable. Se mantiene anticoagulación.', 78.0, 1.67, '128/82', '36.6');

-- ========================
-- 13. DIAGNOSTICOS (35)
-- ========================
INSERT INTO Diagnosticos (enfermedad, descripcion, consulta_id) VALUES
('Cefalea tensional', 'Dolor de cabeza de tipo tensional sin signos neurológicos focales', 1),
('Hipertensión arterial', 'HTA grado 2, en control farmacológico con enalapril', 2),
('Desarrollo normal', 'Niño sano, desarrollo psicomotor adecuado para 5 años', 3),
('Oligomenorrea', 'Ciclos menstruales irregulares, posible etiología hormonal', 4),
('Contusión de rodilla', 'Traumatismo directo en cara anterior de rodilla derecha sin fractura', 5),
('Migraña con aura', 'Migraña crónica episódica con fenómenos visuales previos', 6),
('Infección respiratoria aguda', 'Rinofaringitis aguda de probable etiología viral', 7),
('Taquicardia sinusal', 'Taquicardia sinusal por ansiedad, sin cardiopatía subyacente', 8),
('Dermatitis atópica', 'Dermatitis atópica leve en pliegues de codos y rodillas', 9),
('Hipotiroidismo primario', 'Hipotiroidismo diagnosticado por TSH elevada y T4 libre bajo', 10),
('Gastritis crónica', 'Gastritis antral crónica no erosiva, H. pylori negativo', 11),
('Sin patología aguda', 'Examen físico y laboratorios dentro de rangos normales', 12),
('Amigdalitis bacteriana', 'Amigdalitis pultácea compatible con Streptococcus pyogenes', 13),
('Embarazo normal semana 22', 'Gestación de bajo riesgo con feto en adecuadas condiciones', 14),
('Lumbalgia mecánica', 'Lumbalgia inespecífica de tipo mecánico sin irradiación', 15),
('Faringoamigdalitis viral', 'Infección viral de vías respiratorias altas sin complicaciones', 16),
('Neuropatía periférica leve', 'Parestesias en manos sin déficit motor, origen a determinar', 17),
('Post-operatorio bypass', 'Buena evolución post bypass aortocoronario de doble vaso', 18),
('Gastritis por AINES', 'Irritación gástrica secundaria al uso crónico de ibuprofeno', 19),
('Quiste ovárico funcional', 'Quiste folicular en ovario derecho de 3cm, sin tabiques', 20),
('Desarrollo normal', 'Crecimiento y desarrollo acordes a percentiles para la edad', 21),
('Síndrome de intestino irritable', 'SII con predominio diarreico, criterios de Roma IV', 22),
('Esguince de tobillo grado II', 'Lesión ligamentosa parcial en tobillo izquierdo', 23),
('Acné vulgar grado 2', 'Acné inflamatorio moderado en región facial y dorsal', 24),
('Diabetes mellitus tipo 2', 'DM2 descompensada con HbA1c de 9.2%', 25),
('Lumbalgia ocupacional', 'Dolor lumbar secundario a postura inadecuada y sobrecarga', 26),
('Vértigo posicional benigno', 'VPPB de canal posterior derecho, test de Dix-Hallpike positivo', 27),
('Hipertensión arterial no controlada', 'HTA con cifras persistentemente elevadas pese a monoterapia', 28),
('Asma bronquial', 'Asma intermitente en niño con FEV1 80% del predicho', 29),
('Fibrilación auricular', 'FA paroxística en seguimiento con anticoagulación oral', 30),
('Rinitis alérgica', 'Rinitis perenne con sensibilización a ácaros del polvo', 7),
('Broncoespasmo leve', 'Episodio de broncoespasmo asociado a infección viral', 7),
('Dislipidemia', 'Hipercolesterolemia mixta, LDL 180 mg/dL, control con dieta', 2),
('Obesidad grado I', 'IMC 31.2, se indica plan nutricional y actividad física', 11),
('Anemia ferropénica leve', 'Hemoglobina 10.8 g/dL con microcitosis, se inicia sulfato ferroso', 26);

-- ========================
-- 14. TRATAMIENTOS (20)
-- ========================
INSERT INTO Tratamientos (indicaciones, duracion_dias, consulta_id) VALUES
('Reposo relativo, evitar pantallas, hidratación adecuada. Analgésicos según necesidad.', 7, 1),
('Ajuste de antihipertensivo. Dieta hiposódica, ejercicio moderado diario.', 30, 2),
('Dieta equilibrada para la edad. Actividad física regular. Suplemento vitamínico.', 30, 3),
('Anticonceptivo oral para regularizar ciclos. Control ecográfico en 30 días.', 60, 4),
('Reposo de la articulación. Hielo local 20 minutos cada 4 horas. Antiinflamatorio.', 10, 5),
('Propranolol 40mg/día como profilaxis. Sumatriptán para crisis agudas.', 90, 6),
('Antibioticoterapia oral. Reposo, hidratación y antipirético si temperatura >38.5°C.', 7, 7),
('Reducción de cafeína y tabaco. Técnicas de relajación. Betabloqueante si persiste.', 14, 8),
('Hidratante emoliente 2 veces al día. Corticoide tópico leve en lesiones activas.', 21, 9),
('Levotiroxina 50mcg en ayunas. Control de TSH en 4 semanas.', 90, 10),
('Omeprazol 20mg antes del desayuno. Dieta blanda sin irritantes. Sin AINES.', 30, 11),
('Continuar hábitos saludables. Actividad física 150 min/semana. Control anual.', 365, 12),
('Amoxicilina 500mg cada 8h. Ibuprofeno para el dolor y la fiebre. Gárgaras salinas.', 7, 13),
('Ácido fólico y hierro como suplementos. Dieta rica en calcio. Reposo relativo.', 30, 14),
('Fisioterapia 3 veces por semana. Relajante muscular nocturno. Faja lumbar en actividad.', 21, 15),
('Reposo relativo 48h. Paracetamol 1g cada 8h. Vitamina C. Hidratación.', 5, 16),
('Vitaminas del grupo B (B1, B6, B12). Ejercicios de movilidad de manos.', 60, 17),
('Aspirina 100mg/día. Control cardiológico mensual. Rehabilitación cardíaca.', 180, 18),
('Suspender AINES. Omeprazol 20mg/día. Dieta blanda. Evitar alcohol.', 30, 19),
('Ecografía de control en 60 días. Anticonceptivos orales para reducción de quiste.', 60, 20);

-- ========================
-- 15. MEDICAMENTOS (40)
-- ========================
INSERT INTO Medicamentos (nombre, dosis, frecuencia, duracion_dias, instrucciones, tratamiento_id) VALUES
('Ibuprofeno', '400mg', 'Cada 8 horas', 5, 'Tomar con alimentos. No usar si tiene gastritis.', 1),
('Paracetamol', '500mg', 'Cada 6 horas si dolor', 7, 'No exceder 4 tomas al día.', 1),
('Enalapril', '10mg', 'Cada 12 horas', 30, 'Tomar a la misma hora. No suspender sin consultar.', 2),
('Hidroclorotiazida', '12.5mg', 'Una vez al día en la mañana', 30, 'Vigilar potasio. Hidratarse bien.', 2),
('Vitamina D', '1000UI', 'Una vez al día', 30, 'Tomar con el desayuno.', 3),
('Vitamina C', '500mg', 'Una vez al día', 30, 'Puede tomarse con o sin alimentos.', 3),
('Anticonceptivo oral combinado', '1 comprimido', 'Cada 24 horas', 21, 'Tomar a la misma hora todos los días.', 4),
('Ácido fólico', '5mg', 'Una vez al día', 30, 'Tomar en ayunas o con alimentos.', 4),
('Naproxeno', '500mg', 'Cada 12 horas', 7, 'Tomar con alimentos. Evitar en úlcera péptica.', 5),
('Tramadol', '50mg', 'Cada 8 horas según dolor', 5, 'Puede producir somnolencia. No manejar.', 5),
('Propranolol', '40mg', 'Una vez al día', 90, 'No suspender bruscamente. Controlar pulso.', 6),
('Sumatriptán', '50mg', 'En crisis, máximo 2/día', 30, 'Usar al inicio del dolor. No exceder 200mg/día.', 6),
('Amoxicilina', '500mg', 'Cada 8 horas', 7, 'Terminar el tratamiento completo. Con o sin comida.', 7),
('Ibuprofeno', '400mg', 'Cada 8 horas', 5, 'Solo si fiebre >38°C. Con alimentos.', 7),
('Atenolol', '25mg', 'Una vez al día', 14, 'Tomar con el desayuno. Controlar frecuencia cardíaca.', 8),
('Loratadina', '10mg', 'Una vez al día', 14, 'Puede causar leve somnolencia.', 8),
('Betametasona crema', '0.05%', 'Dos veces al día tópico', 21, 'Aplicar capa fina en la zona afectada. No en cara.', 9),
('Emoliente corporal', 'Cantidad necesaria', 'Dos veces al día', 21, 'Aplicar en todo el cuerpo tras el baño.', 9),
('Levotiroxina', '50mcg', 'Una vez al día en ayunas', 90, 'Esperar 30 min antes de comer. No con calcio o hierro.', 10),
('Omeprazol', '20mg', 'Una vez al día antes del desayuno', 30, 'Tomar 30 minutos antes del desayuno.', 11),
('Sucralfato', '1g', 'Tres veces al día antes de comidas', 30, 'Tomar 1 hora antes de las comidas principales.', 11),
('Amoxicilina', '500mg', 'Cada 8 horas', 7, 'Completar tratamiento aunque mejore.', 13),
('Ibuprofeno pediátrico', '200mg', 'Cada 6-8 horas según peso', 5, 'Calcular dosis por peso: 10mg/kg.', 13),
('Ácido fólico', '1mg', 'Una vez al día', 30, 'Indispensable en el embarazo. Tomar a diario.', 14),
('Sulfato ferroso', '300mg', 'Dos veces al día', 30, 'Tomar con vitamina C para mejor absorción.', 14),
('Ciclobenzaprina', '5mg', 'Cada 8 horas', 10, 'Puede causar somnolencia. No manejar.', 15),
('Diclofenaco', '75mg', 'Cada 12 horas', 7, 'Tomar con alimentos. Solo 7 días máximo.', 15),
('Paracetamol', '1g', 'Cada 8 horas', 5, 'No sobrepasar 3g al día.', 16),
('Vitamina B1 (Tiamina)', '100mg', 'Una vez al día', 60, 'Tomar con el desayuno.', 17),
('Vitamina B12', '500mcg', 'Una vez al día', 60, 'Puede tomarse con o sin alimentos.', 17),
('Aspirina', '100mg', 'Una vez al día', 180, 'Tomar con el desayuno. No suspender sin consultar.', 18),
('Clopidogrel', '75mg', 'Una vez al día', 180, 'Antiagregante. Consultar antes de procedimientos.', 18),
('Omeprazol', '20mg', 'Una vez al día', 30, 'Tomar 30 min antes del desayuno para gastritis.', 19),
('Anticonceptivo oral', '1 comprimido', 'Una vez al día', 60, 'Tomar a la misma hora sin olvidar dosis.', 20),
('Metformina', '850mg', 'Dos veces al día con las comidas', 90, 'No suspender. Control glucémico en ayunas semanal.', 6),
('Insulina glargina', '10 UI', 'Una vez al día por la noche', 90, 'Aplicar subcutánea en abdomen rotando zonas.', 6),
('Salbutamol inhalador', '100mcg', 'Cada 4-6 horas en crisis', 30, 'Máximo 4 inhalaciones por crisis. Enjuagar boca.', 7),
('Budesonida inhalada', '200mcg', 'Dos inhalaciones cada 12 horas', 90, 'Enjuagar la boca tras cada uso.', 7),
('Amlodipino', '5mg', 'Una vez al día', 30, 'Tomar a la misma hora. No suspender abruptamente.', 2),
('Warfarina', '5mg', 'Una vez al día', 180, 'Control de INR semanal al inicio. Evitar cambios en dieta con vitamina K.', 18);
