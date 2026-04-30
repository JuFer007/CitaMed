CREATE DATABASE IF NOT EXISTS CitaMed;
USE CitaMed;

-- ========================
-- 1. USUARIOS
-- ========================
INSERT INTO Usuarios (user_name, password, rol, activo) VALUES
-- Admins
('admin01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('marcelo.alarcon',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('cristian.huaman',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('junior.zumaeta',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
-- Recepcionistas
('recep01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  true),
('recep02',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  true),
('recep03',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  false),
-- Médicos
('med.garcia',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.torres',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.ramirez',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.flores',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.castro',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.herrera',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.vega',           '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.mora',           '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.rivas',          '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.pinto',          '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
('med.aguilar',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
-- Enfermeros
('enf.lopez',          '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ENFERMERO',      true),
('enf.perez',          '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ENFERMERO',      true),
('enf.ruiz',           '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ENFERMERO',      true),
('enf.diaz',           '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ENFERMERO',      true),
('enf.ortiz',          '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ENFERMERO',      true);

-- ========================
-- 2. ESPECIALIDADES
-- ========================
INSERT INTO Especialidades (nombre, descripcion) VALUES
('Medicina General',          'Atención médica primaria y consultas generales de salud'),
('Cardiología',               'Diagnóstico y tratamiento de enfermedades cardiovasculares'),
('Pediatría',                 'Atención médica integral de niños desde el nacimiento hasta la adolescencia'),
('Ginecología y Obstetricia', 'Salud reproductiva femenina, embarazo y parto'),
('Traumatología y Ortopedia', 'Lesiones y enfermedades del aparato locomotor'),
('Neurología',                'Enfermedades del sistema nervioso central y periférico'),
('Dermatología',              'Enfermedades de la piel, cabello y uñas'),
('Endocrinología',            'Trastornos hormonales y metabólicos'),
('Gastroenterología',         'Enfermedades del sistema digestivo'),
('Oftalmología',              'Enfermedades y cirugías del ojo');

-- ========================
-- 3. CONSULTORIOS 
-- ========================
INSERT INTO Consultorios (numero, descripcion, disponible, especialidad_id) VALUES
('C-101', 'Consultorio de Medicina General N°1',  true,  1),
('C-102', 'Consultorio de Medicina General N°2',  true,  1),
('C-103', 'Consultorio de Medicina General N°3',  false, 1),
('C-201', 'Consultorio de Cardiología N°1',        true,  2),
('C-202', 'Consultorio de Cardiología N°2',        true,  2),
('C-301', 'Consultorio de Pediatría N°1',          true,  3),
('C-302', 'Consultorio de Pediatría N°2',          false, 3),
('C-401', 'Consultorio de Ginecología N°1',        true,  4),
('C-402', 'Consultorio de Ginecología N°2',        true,  4),
('C-501', 'Consultorio de Traumatología N°1',      true,  5),
('C-601', 'Consultorio de Neurología N°1',         true,  6),
('C-602', 'Consultorio de Neurología N°2',         true,  6);

-- ========================
-- 4. EMPLEADOS
-- ========================
-- ========================
-- 4. EMPLEADOS (CORREGIDO)
-- ========================
INSERT INTO Empleados (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, salario, fecha_ingreso, activo, usuario_id) VALUES
-- Integrantes del equipo (ADMIN) apuntando a IDs 2, 3, 4
('MARCELO ARIEL',  'ALARCON',   'MANAY',    '74100001', '987000001', 'Av. Los Desarrolladores 101, Chiclayo', 'marcelo.alarcon@citamed.com',  '1999-05-15', 'MASCULINO', 4500.00, '2024-01-01', true,  2),
('CRISTIAN',       'HUAMAN',    'CRUZ',     '74100002', '987000002', 'Jr. Full Stack 202, Chiclayo',          'cristian.huaman@citamed.com',  '1999-08-22', 'MASCULINO', 4500.00, '2024-01-01', true,  3),
('JUNIOR FERNANDO','ZUMAETA',   'GOLAC',    '74100003', '987000003', 'Calle Backend 303, Chiclayo',           'junior.zumaeta@citamed.com',   '1999-11-10', 'MASCULINO', 4500.00, '2024-01-01', true,  4),
-- Admin genérico apuntando al ID 1
('CARLOS',         'MENDOZA',   'QUISPE',   '45678901', '987654321', 'Av. Los Olivos 123, Chiclayo',          'carlos.mendoza@citamed.com',   '1985-03-15', 'MASCULINO', 4200.00, '2018-01-10', true,  1),
-- Recepcionistas apuntando a IDs 5 y 6
('MARIA',          'LOPEZ',     'SANCHEZ',  '45678902', '987654322', 'Jr. Los Pinos 456, Chiclayo',           'maria.lopez@citamed.com',      '1990-07-22', 'FEMENINO',  1800.00, '2020-03-01', true,  5),
('ANA',            'PEREZ',     'CASTILLO', '45678903', '987654323', 'Calle Manco Capac 789, Chiclayo',       'ana.perez@citamed.com',        '1992-11-05', 'FEMENINO',  1800.00, '2021-06-15', true,  6),
-- Enfermeros (IDs ajustados: 19, 20, 21...)
('JOSE',           'RUIZ',      'VARGAS',   '45678910', '987654330', 'Av. Salaverry 321, Chiclayo',           'jose.ruiz@citamed.com',        '1988-04-18', 'MASCULINO', 2200.00, '2019-08-01', true,  19),
('LUCIA',          'DIAZ',      'FLORES',   '45678911', '987654331', 'Jr. Amazonas 654, Chiclayo',            'lucia.diaz@citamed.com',       '1991-09-25', 'FEMENINO',  2200.00, '2020-01-15', true,  20),
-- Personal adicional (les asigné los IDs 21, 22 y 23 que son los que sobran de enfermeros)
('ROBERTO',        'NUNEZ',     'PAREDES',  '45678914', '987654334', 'Av. Grau 987, Chiclayo',                'roberto.nunez@citamed.com',    '1987-12-10', 'MASCULINO', 1500.00, '2018-05-01', true,  21),
('ELENA',          'MENDOZA',   'CRUZ',     '45678916', '987654336', 'Calle 7 de Enero 147, Chiclayo',        'elena.mendoza@citamed.com',    '1989-06-30', 'FEMENINO',  1500.00, '2019-11-01', true,  22),
('PEDRO',          'AGUILAR',   'RIOS',     '45678918', '987654338', 'Av. Bolognesi 258, Chiclayo',           'pedro.aguilar@citamed.com',    '1983-02-14', 'MASCULINO', 3800.00, '2017-03-20', true,  23);
-- ========================
-- 5. MEDICOS
-- ========================
INSERT INTO Medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, especialidad_id, usuario_id) VALUES
('LUIS',      'GARCIA',   'MONTOYA',  '12345601', '912345601', 'Av. Pardo 101, Chiclayo',            'luis.garcia@citamed.com',    '1978-05-12', 'MASCULINO', 'CMP-045231', true,  1,  8),
('ROSA',      'TORRES',   'GUTIERREZ','12345602', '912345602', 'Jr. Colon 202, Chiclayo',            'rosa.torres@citamed.com',    '1980-09-18', 'FEMENINO',  'CMP-038742', true,  2,  9),
('MIGUEL',    'RAMIREZ',  'DELGADO',  '12345603', '912345603', 'Calle Real 303, Chiclayo',           'miguel.ramirez@citamed.com', '1975-03-27', 'MASCULINO', 'CMP-029183', true,  3,  10),
('CARMEN',    'FLORES',   'ESPINOZA', '12345604', '912345604', 'Av. Venezuela 404, Chiclayo',        'carmen.flores@citamed.com',  '1982-07-08', 'FEMENINO',  'CMP-051647', true,  4,  11),
('JORGE',     'CASTRO',   'HUAMAN',   '12345605', '912345605', 'Jr. Tacna 505, Chiclayo',            'jorge.castro@citamed.com',   '1977-11-14', 'MASCULINO', 'CMP-034829', true,  5,  12),
('PATRICIA',  'HERRERA',  'LEON',     '12345606', '912345606', 'Av. Balta 606, Chiclayo',            'patricia.herrera@citamed.com','1983-01-30', 'FEMENINO',  'CMP-062318', true,  6,  13),
('FERNANDO',  'VEGA',     'QUISPE',   '12345607', '912345607', 'Calle Elias Aguirre 707, Chiclayo',  'fernando.vega@citamed.com',  '1979-06-22', 'MASCULINO', 'CMP-041095', true,  7,  14),
('SANDRA',    'MORA',     'PAREDES',  '12345608', '912345608', 'Jr. Torres Paz 808, Chiclayo',       'sandra.mora@citamed.com',    '1981-10-03', 'FEMENINO',  'CMP-057382', true,  8,  15),
('HECTOR',    'RIVAS',    'CARDENAS', '12345609', '912345609', 'Av. Jose Balta 909, Chiclayo',       'hector.rivas@citamed.com',   '1976-04-17', 'MASCULINO', 'CMP-027461', true,  9,  16),
('GABRIELA',  'PINTO',    'SALINAS',  '12345610', '912345610', 'Calle Siete de Enero 1010, Chiclayo','gabriela.pinto@citamed.com', '1984-08-25', 'FEMENINO',  'CMP-068754', true,  1,  17);
-- ========================
-- 6. HORARIOS MEDICOS (CORREGIDO - agregado médico 10)
-- ========================
INSERT INTO horarios_medicos (dia, hora_inicio, hora_fin, activo, medico_id, consultorio_id) VALUES
('LUNES',     '08:00:00', '13:00:00', true,  1,  1),
('MIERCOLES', '08:00:00', '13:00:00', true,  1,  1),
('VIERNES',   '08:00:00', '12:00:00', true,  1,  2),
('MARTES',    '09:00:00', '14:00:00', true,  2,  4),
('JUEVES',    '09:00:00', '14:00:00', true,  2,  4),
('SABADO',    '08:00:00', '12:00:00', true,  2,  5),
('LUNES',     '14:00:00', '19:00:00', true,  3,  6),
('MIERCOLES', '14:00:00', '19:00:00', true,  3,  6),
('VIERNES',   '14:00:00', '19:00:00', true,  3,  6),
('MARTES',    '08:00:00', '13:00:00', true,  4,  8),
('JUEVES',    '08:00:00', '13:00:00', true,  4,  8),
('SABADO',    '09:00:00', '12:00:00', true,  4,  9),
('LUNES',     '08:00:00', '14:00:00', true,  5,  10),
('MIERCOLES', '08:00:00', '14:00:00', true,  5,  10),
('MARTES',    '15:00:00', '20:00:00', true,  6,  11),
('JUEVES',    '15:00:00', '20:00:00', true,  6,  11),
('VIERNES',   '15:00:00', '20:00:00', true,  6,  12),
('LUNES',     '09:00:00', '14:00:00', true,  7,  2),
('MIERCOLES', '09:00:00', '14:00:00', true,  8,  2),
('VIERNES',   '09:00:00', '13:00:00', true,  9,  1),
-- ✅ AGREGADO: médico 10 (Gabriela Pinto - Medicina General)
('LUNES',     '08:00:00', '13:00:00', true,  10, 1),
('MIERCOLES', '08:00:00', '13:00:00', true,  10, 2),
('VIERNES',   '08:00:00', '13:00:00', true,  10, 2);
-- ========================
-- 7. PACIENTES
-- ========================
INSERT INTO Pacientes (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, grupo_sanguineo) VALUES
('JUAN CARLOS',      'QUISPE',      'MAMANI',     '71234501', '951234501', 'Av. Agricultura 101, Chiclayo',      'jc.quispe@gmail.com',              '1990-02-14', 'MASCULINO', 'O_POSITIVO'),
('MARIA ELENA',      'SOTO',        'VARGAS',     '71234502', '951234502', 'Jr. Los Alamos 202, Chiclayo',       'maria.soto@gmail.com',             '1985-06-23', 'FEMENINO',  'A_POSITIVO'),
('PEDRO PABLO',      'HUAMAN',      'QUISPE',     '71234503', '951234503', 'Calle Los Fresnos 303, Chiclayo',    'pedro.huaman@gmail.com',           '1978-11-05', 'MASCULINO', 'B_POSITIVO'),
('LUCIA ROSA',       'PAREDES',     'TORRES',     '71234504', '951234504', 'Av. Fitzcarrald 404, Chiclayo',      'lucia.paredes@gmail.com',          '1995-03-18', 'FEMENINO',  'AB_POSITIVO'),
('CARLOS ALBERTO',   'MENDOZA',     'LOPEZ',      '71234505', '951234505', 'Jr. San Martin 505, Chiclayo',       'carlos.mendoza.p@gmail.com',       '1988-07-30', 'MASCULINO', 'O_NEGATIVO'),
('ANA SOFIA',        'ROJAS',       'CASTRO',     '71234506', '951234506', 'Calle Dos de Mayo 606, Chiclayo',    'ana.rojas@gmail.com',              '1992-09-12', 'FEMENINO',  'A_NEGATIVO'),
('ROBERTO JESUS',    'VARGAS',      'DIAZ',       '71234507', '951234507', 'Av. Saenz Pena 707, Chiclayo',       'roberto.vargas@gmail.com',         '1975-01-08', 'MASCULINO', 'B_NEGATIVO'),
('ISABEL CRISTINA',  'CHAVEZ',      'MORALES',    '71234508', '951234508', 'Jr. Ladislao Espinar 808, Chiclayo', 'isabel.chavez@gmail.com',          '1983-05-27', 'FEMENINO',  'O_POSITIVO'),
('DIEGO ALEJANDRO',  'FUENTES',     'HERRERA',    '71234509', '951234509', 'Calle Pedro Ruiz 909, Chiclayo',     'diego.fuentes@gmail.com',          '1998-12-03', 'MASCULINO', 'A_POSITIVO'),
('VALENTINA',        'ESPINOZA',    'NUNEZ',      '71234510', '951234510', 'Av. Leguia 1010, Chiclayo',          'valentina.espinoza@gmail.com',     '2001-04-16', 'FEMENINO',  'AB_NEGATIVO'),
('ANDRES FELIPE',    'LLONTOP',     'BANCES',     '71234511', '951234511', 'Jr. Colon 111, Chiclayo',            'andres.llontop@gmail.com',         '1970-08-21', 'MASCULINO', 'O_POSITIVO'),
('GABRIELA NOEMI',   'ZULOETA',     'CARRASCO',   '71234512', '951234512', 'Av. Pedro Ruiz 222, Chiclayo',       'gabriela.zuloeta@gmail.com',       '1987-10-09', 'FEMENINO',  'B_POSITIVO'),
('RICARDO MANUEL',   'GONZALES',    'MURO',       '71234513', '951234513', 'Calle Izaga 333, Chiclayo',          'ricardo.gonzales@gmail.com',       '1993-02-28', 'MASCULINO', 'A_POSITIVO'),
('SOFIA VALENTINA',  'CHAFLOQUE',   'SOLIS',      '71234514', '951234514', 'Jr. 8 de Octubre 444, Chiclayo',     'sofia.chafloque@gmail.com',        '2000-06-14', 'FEMENINO',  'O_POSITIVO'),
('MARTIN EDUARDO',   'IDROGO',      'BUSTAMANTE', '71234515', '951234515', 'Av. Los Incas 555, Chiclayo',        'martin.idrogo@gmail.com',          '1965-11-30', 'MASCULINO', 'A_NEGATIVO'),
('PATRICIA LUZ',     'CORONADO',    'DIAZ',       '71234516', '951234516', 'Calle Real 666, Chiclayo',           'patricia.coronado@gmail.com',      '1980-03-07', 'FEMENINO',  'B_POSITIVO'),
('EDUARDO JESUS',    'SAAVEDRA',    'NEYRA',      '71234517', '951234517', 'Jr. San Jose 777, Chiclayo',         'eduardo.saavedra@gmail.com',       '1972-07-19', 'MASCULINO', 'O_POSITIVO'),
('CLAUDIA BEATRIZ',  'SEGURA',      'MONTOYA',    '71234518', '951234518', 'Av. Chiclayo 888, Chiclayo',         'claudia.segura@gmail.com',         '1996-09-02', 'FEMENINO',  'AB_POSITIVO'),
('MARCO ANTONIO',    'BECERRA',     'VILLANUEVA', '71234519', '951234519', 'Calle Los Pinos 999, Chiclayo',      'marco.becerra@gmail.com',          '1982-01-25', 'MASCULINO', 'A_POSITIVO'),
('NATALIA ANDREA',   'CUBAS',       'MEJIA',      '71234520', '951234520', 'Jr. Victor Raul 1100, Chiclayo',     'natalia.cubas@gmail.com',          '1991-05-11', 'FEMENINO',  'O_NEGATIVO'),
('CESAR AUGUSTO',    'FERNANDEZ',   'PAZ',        '71234521', '951234521', 'Av. Chinchaysuyo 200, Chiclayo',     'cesar.fernandez@gmail.com',        '1969-03-15', 'MASCULINO', 'B_POSITIVO'),
('MILAGROS',         'PUELLES',     'ASALDE',     '71234522', '951234522', 'Jr. Los Jazmines 300, Chiclayo',     'milagros.puelles@gmail.com',       '1976-08-29', 'FEMENINO',  'O_POSITIVO'),
('JHON ALEXANDER',   'GUERRERO',    'RIVADENEYRA','71234523', '951234523', 'Calle Manco Capac 400, Chiclayo',    'jhon.guerrero@gmail.com',          '1999-12-17', 'MASCULINO', 'A_POSITIVO'),
('FIORELLA MILAGROS','TELLO',       'PERALES',    '71234524', '951234524', 'Av. Venezuela 500, Chiclayo',        'fiorella.tello@gmail.com',         '2003-04-05', 'FEMENINO',  'AB_POSITIVO'),
('ALEJANDRO LUIS',   'SANTISTEBAN', 'CABREJOS',   '71234525', '951234525', 'Jr. Elias Aguirre 600, Chiclayo',    'alejandro.santisteban@gmail.com',  '1960-06-20', 'MASCULINO', 'O_POSITIVO'),
('YESICA PAMELA',    'FALLA',       'MEDINA',     '71234526', '951234526', 'Calle Torres Paz 700, Chiclayo',     'yesica.falla@gmail.com',           '1994-10-08', 'FEMENINO',  'B_NEGATIVO'),
('HUGO ARMANDO',     'BRAVO',       'ALCANTARA',  '71234527', '951234527', 'Av. Grecia 800, Chiclayo',           'hugo.bravo@gmail.com',             '1973-02-14', 'MASCULINO', 'A_NEGATIVO'),
('ROSARIO ESPERANZA','ALIAGA',      'DELGADO',    '71234528', '951234528', 'Jr. Balta 900, Chiclayo',            'rosario.aliaga@gmail.com',         '1968-07-03', 'FEMENINO',  'O_POSITIVO'),
('KEVIN SEBASTIAN',  'HEREDIA',     'CAMPOS',     '71234529', '951234529', 'Calle Junin 1000, Chiclayo',         'kevin.heredia@gmail.com',          '2005-11-28', 'MASCULINO', 'B_POSITIVO'),
('LORENA PATRICIA',  'VALDERRAMA',  'CHIRINOS',   '71234530', '951234530', 'Av. Mariscal Castilla 1100, Chiclayo','lorena.valderrama@gmail.com',     '1986-04-22', 'FEMENINO',  'A_POSITIVO');

-- ========================
-- 8. HISTORIALES MEDICOS
-- ========================
INSERT INTO Historiales_Medicos (paciente_id) VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),
(21),(22),(23),(24),(25),(26),(27),(28),(29),(30);

-- ========================
-- 9. CITAS
-- ========================
INSERT INTO Citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
(1,  1,  1,  '2025-08-04 08:00:00', 'Dolor de cabeza persistente y mareos',      'ATENDIDA'),
(2,  2,  4,  '2025-08-05 09:00:00', 'Revision de presion arterial alta',          'ATENDIDA'),
(3,  3,  6,  '2025-08-05 14:00:00', 'Control pediatrico de nino de 5 anos',       'ATENDIDA'),
(4,  4,  8,  '2025-08-06 08:00:00', 'Consulta por irregularidades menstruales',   'ATENDIDA'),
(5,  5,  10, '2025-08-06 09:00:00', 'Dolor en rodilla derecha tras caida',        'ATENDIDA'),
(6,  6,  11, '2025-08-07 15:00:00', 'Episodios de migrana frecuentes',            'ATENDIDA'),
(7,  1,  1,  '2025-08-07 08:00:00', 'Tos persistente y fiebre leve',              'ATENDIDA'),
(8,  2,  4,  '2025-08-08 09:00:00', 'Palpitaciones y cansancio',                  'ATENDIDA'),
(9,  7,  2,  '2025-08-11 09:00:00', 'Dermatitis en brazos',                       'ATENDIDA'),
(10, 8,  2,  '2025-08-12 09:00:00', 'Fatiga extrema y aumento de peso',           'ATENDIDA'),
(11, 9,  1,  '2025-08-12 08:00:00', 'Nauseas y dolor abdominal',                  'ATENDIDA'),
(12, 1,  2,  '2025-08-13 08:00:00', 'Chequeo medico general',                     'ATENDIDA'),
(13, 3,  6,  '2025-08-13 14:00:00', 'Fiebre alta en nino de 8 anos',              'ATENDIDA'),
(14, 4,  9,  '2025-08-14 08:00:00', 'Control de embarazo mes 5',                  'ATENDIDA'),
(15, 5,  10, '2025-08-14 08:00:00', 'Dolor lumbar cronico',                       'ATENDIDA'),
(16, 10, 1,  '2025-08-18 08:00:00', 'Resfriado y dolor de garganta',              'ATENDIDA'),
(17, 6,  11, '2025-08-19 15:00:00', 'Entumecimiento en extremidades',             'ATENDIDA'),
(18, 2,  5,  '2025-08-19 09:00:00', 'Control post-operatorio cardiaco',           'ATENDIDA'),
(19, 1,  1,  '2025-08-20 08:00:00', 'Gastritis y acidez estomacal',               'ATENDIDA'),
(20, 4,  8,  '2025-08-20 08:00:00', 'Revision de quiste ovarico',                 'ATENDIDA'),
(21, 3,  6,  '2025-08-21 14:00:00', 'Vacunacion y control de crecimiento',        'ATENDIDA'),
(22, 9,  1,  '2025-08-21 08:00:00', 'Colon irritable, control mensual',           'ATENDIDA'),
(23, 5,  10, '2025-08-25 08:00:00', 'Esguince de tobillo izquierdo',              'ATENDIDA'),
(24, 7,  2,  '2025-08-26 09:00:00', 'Acne y problemas en piel grasa',             'ATENDIDA'),
(25, 8,  2,  '2025-08-26 09:00:00', 'Diabetes tipo 2, control trimestral',        'ATENDIDA'),
(26, 10, 2,  '2025-08-27 09:00:00', 'Dolor de espalda y fatiga',                  'ATENDIDA'),
(27, 6,  12, '2025-08-27 15:00:00', 'Vertigo y perdida de equilibrio',            'ATENDIDA'),
(28, 1,  1,  '2025-08-28 08:00:00', 'Presion alta y dolor de cabeza',             'ATENDIDA'),
(29, 3,  6,  '2025-09-01 14:00:00', 'Control de asma infantil',                   'ATENDIDA'),
(30, 2,  4,  '2025-09-02 09:00:00', 'Arritmia, control semestral',                'ATENDIDA'),
(1,  10, 1,  '2026-06-10 08:00:00', 'Control medico trimestral',                  'PROGRAMADA'),
(5,  5,  10, '2026-06-11 08:00:00', 'Revision de fractura curada',                'PROGRAMADA'),
(15, 8,  2,  '2026-06-11 09:00:00', 'Hipotiroidismo, control mensual',            'PROGRAMADA'),
(22, 9,  1,  '2026-06-14 08:00:00', 'Gastroenterologia, seguimiento',             'PROGRAMADA'),
(28, 2,  4,  '2026-06-15 09:00:00', 'Hipertension, ajuste de tratamiento',        'PROGRAMADA');

-- ========================
-- 10. PAGOS
-- ========================
INSERT INTO Pagos (cita_id, monto, metodo_pago, estado, fecha_pago) VALUES
(1,  80.00,  'EFECTIVO',      'PAGADO', '2025-08-04 08:05:00'),
(2,  120.00, 'TARJETA',       'PAGADO', '2025-08-05 09:10:00'),
(3,  90.00,  'EFECTIVO',      'PAGADO', '2025-08-05 14:05:00'),
(4,  100.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-06 08:05:00'),
(5,  110.00, 'EFECTIVO',      'PAGADO', '2025-08-06 09:05:00'),
(6,  130.00, 'TARJETA',       'PAGADO', '2025-08-07 15:05:00'),
(7,  80.00,  'EFECTIVO',      'PAGADO', '2025-08-07 08:05:00'),
(8,  120.00, 'EFECTIVO',      'PAGADO', '2025-08-08 09:05:00'),
(9,  100.00, 'TARJETA',       'PAGADO', '2025-08-11 09:05:00'),
(10, 150.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-12 09:05:00'),
(11, 90.00,  'EFECTIVO',      'PAGADO', '2025-08-12 08:05:00'),
(12, 80.00,  'EFECTIVO',      'PAGADO', '2025-08-13 08:05:00'),
(13, 90.00,  'TARJETA',       'PAGADO', '2025-08-13 14:05:00'),
(14, 100.00, 'EFECTIVO',      'PAGADO', '2025-08-14 08:05:00'),
(15, 110.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-14 08:05:00'),
(16, 80.00,  'EFECTIVO',      'PAGADO', '2025-08-18 08:05:00'),
(17, 130.00, 'TARJETA',       'PAGADO', '2025-08-19 15:05:00'),
(18, 120.00, 'TARJETA',       'PAGADO', '2025-08-19 09:05:00'),
(19, 80.00,  'EFECTIVO',      'PAGADO', '2025-08-20 08:05:00'),
(20, 100.00, 'EFECTIVO',      'PAGADO', '2025-08-20 08:05:00'),
(21, 90.00,  'EFECTIVO',      'PAGADO', '2025-08-21 14:05:00'),
(22, 90.00,  'TARJETA',       'PAGADO', '2025-08-21 08:05:00'),
(23, 110.00, 'EFECTIVO',      'PAGADO', '2025-08-25 08:05:00'),
(24, 100.00, 'TARJETA',       'PAGADO', '2025-08-26 09:05:00'),
(25, 150.00, 'TRANSFERENCIA', 'PAGADO', '2025-08-26 09:05:00'),
(26, 80.00,  'EFECTIVO',      'PAGADO', '2025-08-27 09:05:00'),
(27, 130.00, 'TARJETA',       'PAGADO', '2025-08-27 15:05:00'),
(28, 80.00,  'EFECTIVO',      'PAGADO', '2025-08-28 08:05:00'),
(29, 90.00,  'EFECTIVO',      'PAGADO', '2025-09-01 14:05:00'),
(30, 120.00, 'TARJETA',       'PAGADO', '2025-09-02 09:05:00');

-- ========================
-- 11. CONSULTAS MEDICAS
-- ========================
INSERT INTO Consultas_Medicas (cita_id, historial_id, observaciones, peso, talla, presion_arterial, temperatura) VALUES
(1,  1,  'Paciente refiere cefalea tensional. Se indica descanso y analgesicos.',         75.5, 1.72, '120/80', '36.5'),
(2,  2,  'Hipertension en seguimiento. Se ajusta dosis de antihipertensivo.',             82.0, 1.65, '150/95', '36.8'),
(3,  3,  'Nino con desarrollo adecuado para su edad. Vacunacion al dia.',                 18.5, 1.10, '90/60',  '36.4'),
(4,  4,  'Ciclos irregulares. Se solicita ecografia pelvica.',                            58.0, 1.63, '110/70', '36.6'),
(5,  5,  'Contusion en rodilla derecha. Se indica reposo y antiinflamatorio.',            88.0, 1.78, '125/82', '36.7'),
(6,  6,  'Migrana cronica con aura. Se inicia profilaxis con propranolol.',               62.0, 1.68, '118/76', '36.5'),
(7,  7,  'Infeccion respiratoria alta. Antibioticoterapia por 7 dias.',                  70.0, 1.70, '118/78', '37.8'),
(8,  8,  'Palpitaciones por estres. ECG normal. Se recomienda reducir cafeina.',          65.0, 1.60, '130/85', '36.6'),
(9,  9,  'Dermatitis atopica leve. Se indica crema con corticoide topico.',               55.0, 1.55, '112/72', '36.5'),
(10, 10, 'Hipotiroidismo. Se inicia levotiroxina. Control en 4 semanas.',                 78.0, 1.62, '115/75', '36.4'),
(11, 11, 'Gastritis cronica. Se pauta omeprazol y dieta blanda.',                         90.0, 1.80, '128/84', '36.6'),
(12, 12, 'Chequeo general sin hallazgos patologicos. Examenes de rutina normales.',       68.0, 1.75, '115/75', '36.5'),
(13, 13, 'Amigdalitis bacteriana. Amoxicilina 500mg cada 8h por 7 dias.',                 25.0, 1.30, '95/60',  '38.5'),
(14, 14, 'Embarazo semana 22. Feto en presentacion cefalica. Todo normal.',               66.0, 1.61, '108/68', '36.5'),
(15, 15, 'Lumbalgia mecanica. Fisioterapia y relajantes musculares.',                     95.0, 1.82, '130/88', '36.6'),
(16, 16, 'Faringoamigdalitis viral. Tratamiento sintomatico, reposo e hidratacion.',     55.0, 1.58, '108/70', '37.5'),
(17, 17, 'Neuropatia periferica leve en manos. Vitaminas B y control en 1 mes.',         72.0, 1.74, '120/80', '36.5'),
(18, 18, 'Post-operatorio de bypass coronario. Evolucion favorable. Sin complicaciones.',80.0, 1.69, '125/82', '36.7'),
(19, 19, 'Gastritis aguda por AINES. Se retira ibuprofeno y se inicia omeprazol.',       77.0, 1.76, '122/80', '36.6'),
(20, 20, 'Quiste ovarico funcional de 3cm. Control ecografico en 2 meses.',              60.0, 1.64, '110/70', '36.5'),
(21, 21, 'Control de crecimiento dentro de percentiles normales. Vacuna triple viral.',  32.0, 1.42, '95/62',  '36.4'),
(22, 22, 'SII con predominio diarreico. Se ajusta dieta y se agrega probioticos.',       68.0, 1.65, '118/76', '36.5'),
(23, 23, 'Esguince grado II tobillo izquierdo. Inmovilizacion 10 dias.',                 72.0, 1.71, '120/80', '36.6'),
(24, 24, 'Acne grado 2. Tretinona topica nocturna y antibiotico oral.',                  58.0, 1.60, '110/72', '36.5'),
(25, 25, 'Diabetes tipo 2 descompensada. Ajuste de metformina e insulina basal.',        85.0, 1.66, '138/90', '36.7'),
(26, 26, 'Lumbalgia por sobrecarga laboral. Fisioterapia y cambio ergonomico.',           62.0, 1.59, '115/75', '36.5'),
(27, 27, 'Vertigo posicional benigno. Maniobra de Epley. Mejoria inmediata.',            88.0, 1.80, '122/80', '36.6'),
(28, 28, 'HTA no controlada. Se anade amlodipino al tratamiento actual.',                74.0, 1.70, '158/100','36.8'),
(29, 29, 'Asma bronquial en nino. Salbutamol de rescate y budesonida inhalada.',         29.0, 1.35, '92/60',  '36.5'),
(30, 30, 'Arritmia supraventricular estable. Se mantiene anticoagulacion.',              78.0, 1.67, '128/82', '36.6');

-- ========================
-- 12. DIAGNOSTICOS
-- ========================
INSERT INTO Diagnosticos (enfermedad, descripcion, consulta_id) VALUES
('CEFALEA TENSIONAL',                  'Dolor de cabeza de tipo tensional sin signos neurologicos focales',              1),
('HIPERTENSION ARTERIAL',              'HTA grado 2, en control farmacologico con enalapril',                            2),
('DESARROLLO NORMAL',                  'Nino sano, desarrollo psicomotor adecuado para 5 anos',                          3),
('OLIGOMENORREA',                      'Ciclos menstruales irregulares, posible etiologia hormonal',                     4),
('CONTUSION DE RODILLA',               'Traumatismo directo en cara anterior de rodilla derecha sin fractura',           5),
('MIGRANA CON AURA',                   'Migrana cronica episodica con fenomenos visuales previos',                       6),
('INFECCION RESPIRATORIA AGUDA',       'Rinofaringitis aguda de probable etiologia viral',                               7),
('TAQUICARDIA SINUSAL',                'Taquicardia sinusal por ansiedad, sin cardiopatia subyacente',                   8),
('DERMATITIS ATOPICA',                 'Dermatitis atopica leve en pliegues de codos y rodillas',                        9),
('HIPOTIROIDISMO PRIMARIO',            'Hipotiroidismo diagnosticado por TSH elevada y T4 libre bajo',                   10),
('GASTRITIS CRONICA',                  'Gastritis antral cronica no erosiva, H. pylori negativo',                        11),
('SIN PATOLOGIA AGUDA',                'Examen fisico y laboratorios dentro de rangos normales',                         12),
('AMIGDALITIS BACTERIANA',             'Amigdalitis pultacea compatible con Streptococcus pyogenes',                     13),
('EMBARAZO NORMAL SEMANA 22',          'Gestacion de bajo riesgo con feto en adecuadas condiciones',                     14),
('LUMBALGIA MECANICA',                 'Lumbalgia inespecifica de tipo mecanico sin irradiacion',                        15),
('FARINGOAMIGDALITIS VIRAL',           'Infeccion viral de vias respiratorias altas sin complicaciones',                 16),
('NEUROPATIA PERIFERICA LEVE',         'Parestesias en manos sin deficit motor, origen a determinar',                   17),
('POST-OPERATORIO BYPASS',             'Buena evolucion post bypass aortocoronario de doble vaso',                       18),
('GASTRITIS POR AINES',                'Irritacion gastrica secundaria al uso cronico de ibuprofeno',                    19),
('QUISTE OVARICO FUNCIONAL',           'Quiste folicular en ovario derecho de 3cm, sin tabiques',                        20),
('DESARROLLO NORMAL',                  'Crecimiento y desarrollo acordes a percentiles para la edad',                   21),
('SINDROME DE INTESTINO IRRITABLE',    'SII con predominio diarreico, criterios de Roma IV',                             22),
('ESGUINCE DE TOBILLO GRADO II',       'Lesion ligamentosa parcial en tobillo izquierdo',                                23),
('ACNE VULGAR GRADO 2',                'Acne inflamatorio moderado en region facial y dorsal',                           24),
('DIABETES MELLITUS TIPO 2',           'DM2 descompensada con HbA1c de 9.2%',                                           25),
('LUMBALGIA OCUPACIONAL',              'Dolor lumbar secundario a postura inadecuada y sobrecarga',                      26),
('VERTIGO POSICIONAL BENIGNO',         'VPPB de canal posterior derecho, test de Dix-Hallpike positivo',                 27),
('HIPERTENSION ARTERIAL NO CONTROLADA','HTA con cifras persistentemente elevadas pese a monoterapia',                   28),
('ASMA BRONQUIAL',                     'Asma intermitente en nino con FEV1 80% del predicho',                            29),
('FIBRILACION AURICULAR',              'FA paroxistica en seguimiento con anticoagulacion oral',                         30),
('RINITIS ALERGICA',                   'Rinitis perenne con sensibilizacion a acaros del polvo',                         7),
('BRONCOESPASMO LEVE',                 'Episodio de broncoespasmo asociado a infeccion viral',                           7),
('DISLIPIDEMIA',                       'Hipercolesterolemia mixta, LDL 180 mg/dL, control con dieta',                   2),
('OBESIDAD GRADO I',                   'IMC 31.2, se indica plan nutricional y actividad fisica',                        11),
('ANEMIA FERROPENICA LEVE',            'Hemoglobina 10.8 g/dL con microcitosis, se inicia sulfato ferroso',              26);

-- ========================
-- 13. TRATAMIENTOS (CORREGIDO - agregados consultas 21-30)
-- ========================
INSERT INTO Tratamientos (indicaciones, duracion_dias, consulta_id) VALUES
('Reposo relativo, evitar pantallas, hidratacion adecuada. Analgesicos segun necesidad.',                7,   1),
('Ajuste de antihipertensivo. Dieta hiposodica, ejercicio moderado diario.',                             30,  2),
('Dieta equilibrada para la edad. Actividad fisica regular. Suplemento vitaminico.',                     30,  3),
('Anticonceptivo oral para regularizar ciclos. Control ecografico en 30 dias.',                          60,  4),
('Reposo de la articulacion. Hielo local 20 minutos cada 4 horas. Antiinflamatorio.',                    10,  5),
('Propranolol 40mg/dia como profilaxis. Sumatriptan para crisis agudas.',                                90,  6),
('Antibioticoterapia oral. Reposo, hidratacion y antipiretico si temperatura mayor 38.5.',               7,   7),
('Reduccion de cafeina y tabaco. Tecnicas de relajacion. Betabloqueante si persiste.',                   14,  8),
('Hidratante emoliente 2 veces al dia. Corticoide topico leve en lesiones activas.',                     21,  9),
('Levotiroxina 50mcg en ayunas. Control de TSH en 4 semanas.',                                           90,  10),
('Omeprazol 20mg antes del desayuno. Dieta blanda sin irritantes. Sin AINES.',                           30,  11),
('Continuar habitos saludables. Actividad fisica 150 min/semana. Control anual.',                        365, 12),
('Amoxicilina 500mg cada 8h. Ibuprofeno para el dolor y la fiebre. Gargaras salinas.',                   7,   13),
('Acido folico y hierro como suplementos. Dieta rica en calcio. Reposo relativo.',                       30,  14),
('Fisioterapia 3 veces por semana. Relajante muscular nocturno. Faja lumbar en actividad.',              21,  15),
('Reposo relativo 48h. Paracetamol 1g cada 8h. Vitamina C. Hidratacion.',                               5,   16),
('Vitaminas del grupo B (B1, B6, B12). Ejercicios de movilidad de manos.',                               60,  17),
('Aspirina 100mg/dia. Control cardiologico mensual. Rehabilitacion cardiaca.',                           180, 18),
('Suspender AINES. Omeprazol 20mg/dia. Dieta blanda. Evitar alcohol.',                                  30,  19),
('Ecografia de control en 60 dias. Anticonceptivos orales para reduccion de quiste.',                    60,  20),
-- ✅ AGREGADOS: consultas 21-30
('Vacuna triple viral aplicada. Suplemento de hierro y vitamina D para crecimiento.',                    30,  21),
('Dieta baja en FODMAP. Probioticos diarios. Evitar lactosa y gluten.',                                  60,  22),
('Inmovilizacion con vendaje funcional. Reposo 10 dias. Hielo local.',                                   10,  23),
('Tretinona topica nocturna. Antibiotico oral. Protector solar diario.',                                 60,  24),
('Ajuste de metformina e insulina basal. Dieta diabetica estricta. Control glucemico diario.',           90,  25),
('Fisioterapia lumbar. Cambio ergonomico en puesto de trabajo. Faja de soporte.',                        30,  26),
('Maniobra de Epley realizada. Ejercicios vestibulares en casa. Control en 2 semanas.',                  14,  27),
('Amlodipino agregado al tratamiento. Dieta hiposodica estricta. Control en 15 dias.',                   30,  28),
('Salbutamol de rescate. Budesonida inhalada de mantenimiento. Evitar desencadenantes.',                 90,  29),
('Mantener anticoagulacion con warfarina. Control de INR mensual. Evitar AINES.',                       180, 30);

-- ========================
-- 14. MEDICAMENTOS (CORREGIDO - agregados para tratamientos 21-30)
-- ========================
INSERT INTO Medicamentos (nombre, dosis, frecuencia, duracion_dias, instrucciones, tratamiento_id) VALUES
('IBUPROFENO',                   '400mg',             'Cada 8 horas',                      5,   'Tomar con alimentos. No usar si tiene gastritis.',                         1),
('PARACETAMOL',                  '500mg',             'Cada 6 horas si dolor',             7,   'No exceder 4 tomas al dia.',                                               1),
('ENALAPRIL',                    '10mg',              'Cada 12 horas',                     30,  'Tomar a la misma hora. No suspender sin consultar.',                        2),
('HIDROCLOROTIAZIDA',            '12.5mg',            'Una vez al dia en la manana',       30,  'Vigilar potasio. Hidratarse bien.',                                         2),
('VITAMINA D',                   '1000UI',            'Una vez al dia',                    30,  'Tomar con el desayuno.',                                                    3),
('VITAMINA C',                   '500mg',             'Una vez al dia',                    30,  'Puede tomarse con o sin alimentos.',                                        3),
('ANTICONCEPTIVO ORAL COMBINADO','1 comprimido',      'Cada 24 horas',                     21,  'Tomar a la misma hora todos los dias.',                                     4),
('ACIDO FOLICO',                 '5mg',               'Una vez al dia',                    30,  'Tomar en ayunas o con alimentos.',                                          4),
('NAPROXENO',                    '500mg',             'Cada 12 horas',                     7,   'Tomar con alimentos. Evitar en ulcera peptica.',                            5),
('TRAMADOL',                     '50mg',              'Cada 8 horas segun dolor',          5,   'Puede producir somnolencia. No manejar.',                                   5),
('PROPRANOLOL',                  '40mg',              'Una vez al dia',                    90,  'No suspender bruscamente. Controlar pulso.',                                6),
('SUMATRIPTAN',                  '50mg',              'En crisis, maximo 2/dia',           30,  'Usar al inicio del dolor. No exceder 200mg/dia.',                           6),
('AMOXICILINA',                  '500mg',             'Cada 8 horas',                      7,   'Terminar el tratamiento completo. Con o sin comida.',                       7),
('IBUPROFENO',                   '400mg',             'Cada 8 horas',                      5,   'Solo si fiebre mayor 38. Con alimentos.',                                   7),
('ATENOLOL',                     '25mg',              'Una vez al dia',                    14,  'Tomar con el desayuno. Controlar frecuencia cardiaca.',                     8),
('LORATADINA',                   '10mg',              'Una vez al dia',                    14,  'Puede causar leve somnolencia.',                                            8),
('BETAMETASONA CREMA',           '0.05%',             'Dos veces al dia topico',           21,  'Aplicar capa fina en la zona afectada. No en cara.',                        9),
('EMOLIENTE CORPORAL',           'Cantidad necesaria','Dos veces al dia',                  21,  'Aplicar en todo el cuerpo tras el bano.',                                   9),
('LEVOTIROXINA',                 '50mcg',             'Una vez al dia en ayunas',          90,  'Esperar 30 min antes de comer. No con calcio o hierro.',                    10),
('OMEPRAZOL',                    '20mg',              'Una vez al dia antes del desayuno', 30,  'Tomar 30 minutos antes del desayuno.',                                      11),
('SUCRALFATO',                   '1g',                'Tres veces al dia antes de comidas',30,  'Tomar 1 hora antes de las comidas principales.',                            11),
('AMOXICILINA',                  '500mg',             'Cada 8 horas',                      7,   'Completar tratamiento aunque mejore.',                                      13),
('IBUPROFENO PEDIATRICO',        '200mg',             'Cada 6-8 horas segun peso',         5,   'Calcular dosis por peso: 10mg/kg.',                                         13),
('ACIDO FOLICO',                 '1mg',               'Una vez al dia',                    30,  'Indispensable en el embarazo. Tomar a diario.',                             14),
('SULFATO FERROSO',              '300mg',             'Dos veces al dia',                  30,  'Tomar con vitamina C para mejor absorcion.',                                14),
('CICLOBENZAPRINA',              '5mg',               'Cada 8 horas',                      10,  'Puede causar somnolencia. No manejar.',                                     15),
('DICLOFENACO',                  '75mg',              'Cada 12 horas',                     7,   'Tomar con alimentos. Solo 7 dias maximo.',                                  15),
('PARACETAMOL',                  '1g',                'Cada 8 horas',                      5,   'No sobrepasar 3g al dia.',                                                  16),
('VITAMINA B1 TIAMINA',          '100mg',             'Una vez al dia',                    60,  'Tomar con el desayuno.',                                                    17),
('VITAMINA B12',                 '500mcg',            'Una vez al dia',                    60,  'Puede tomarse con o sin alimentos.',                                        17),
('ASPIRINA',                     '100mg',             'Una vez al dia',                    180, 'Tomar con el desayuno. No suspender sin consultar.',                        18),
('CLOPIDOGREL',                  '75mg',              'Una vez al dia',                    180, 'Antiagregante. Consultar antes de procedimientos.',                         18),
('OMEPRAZOL',                    '20mg',              'Una vez al dia',                    30,  'Tomar 30 min antes del desayuno para gastritis.',                           19),
('ANTICONCEPTIVO ORAL',          '1 comprimido',      'Una vez al dia',                    60,  'Tomar a la misma hora sin olvidar dosis.',                                  20),
('METFORMINA',                   '850mg',             'Dos veces al dia con las comidas',  90,  'No suspender. Control glucemico en ayunas semanal.',                        6),
('INSULINA GLARGINA',            '10 UI',             'Una vez al dia por la noche',       90,  'Aplicar subcutanea en abdomen rotando zonas.',                              6),
('SALBUTAMOL INHALADOR',         '100mcg',            'Cada 4-6 horas en crisis',          30,  'Maximo 4 inhalaciones por crisis. Enjuagar boca.',                          7),
('BUDESONIDA INHALADA',          '200mcg',            'Dos inhalaciones cada 12 horas',    90,  'Enjuagar la boca tras cada uso.',                                           7),
('AMLODIPINO',                   '5mg',               'Una vez al dia',                    30,  'Tomar a la misma hora. No suspender abruptamente.',                         2),
('WARFARINA',                    '5mg',               'Una vez al dia',                    180, 'Control de INR semanal al inicio. Evitar cambios en dieta con vitamina K.', 18),
-- ✅ AGREGADOS: medicamentos para tratamientos 21-30
('SULFATO FERROSO',              '300mg',             'Una vez al dia',                    30,  'Tomar con jugo de naranja para mejor absorcion.',                           21),
('VITAMINA D',                   '400UI',             'Una vez al dia',                    30,  'Tomar con el desayuno.',                                                    21),
('LOPERAMIDA',                   '2mg',               'Segun necesidad, max 3/dia',        30,  'Solo si diarrea intensa. No usar mas de 2 dias seguidos.',                  22),
('PROBIOTICO LACTOBACILLUS',     '1 capsula',         'Una vez al dia',                    60,  'Tomar con el desayuno. Refrigerar.',                                        22),
('IBUPROFENO',                   '400mg',             'Cada 8 horas',                      7,   'Tomar con alimentos. Solo durante inmovilizacion.',                         23),
('DICLOFENACO GEL',              '1%',                'Tres veces al dia topico',          10,  'Aplicar sobre la zona afectada con masaje suave.',                          23),
('TRETINONA CREMA',              '0.025%',            'Una vez al dia nocturna',           60,  'Aplicar capa fina. Evitar exposicion solar.',                               24),
('DOXICICLINA',                  '100mg',             'Cada 12 horas',                     30,  'Tomar con abundante agua. No acostarse inmediatamente.',                    24),
('METFORMINA',                   '850mg',             'Dos veces al dia con las comidas',  90,  'Controlar glucosa en ayunas semanalmente.',                                 25),
('INSULINA GLARGINA',            '10 UI',             'Una vez al dia por la noche',       90,  'Rotar zonas de aplicacion. Refrigerar.',                                    25),
('CICLOBENZAPRINA',              '5mg',               'Una vez al dia nocturna',           14,  'Tomar antes de dormir. Puede causar somnolencia.',                          26),
('DICLOFENACO',                  '75mg',              'Cada 12 horas',                     7,   'Tomar con alimentos. Maximo 7 dias.',                                       26),
('BETAHISTINA',                  '16mg',              'Cada 8 horas',                      14,  'Tomar con las comidas para reducir nauseas.',                               27),
('DIMENHIDRINATO',               '50mg',              'Cada 8 horas si vertigo',           7,   'Puede causar somnolencia. No manejar.',                                     27),
('AMLODIPINO',                   '5mg',               'Una vez al dia',                    30,  'Tomar a la misma hora. No suspender abruptamente.',                         28),
('LOSARTAN',                     '50mg',              'Una vez al dia',                    30,  'Controlar presion cada semana. No suspender sin consultar.',                 28),
('SALBUTAMOL INHALADOR',         '100mcg',            'Cada 4-6 horas en crisis',          90,  'Maximo 4 inhalaciones por crisis. Enjuagar boca.',                          29),
('BUDESONIDA INHALADA',          '200mcg',            'Dos inhalaciones cada 12 horas',    90,  'Enjuagar la boca tras cada uso para evitar candidiasis.',                   29),
('WARFARINA',                    '5mg',               'Una vez al dia',                    180, 'Control de INR mensual. Evitar cambios bruscos en dieta.',                  30),
('DIGOXINA',                     '0.125mg',           'Una vez al dia',                    180, 'Controlar pulso antes de tomar. Consultar si menor de 60 lpm.',             30);
