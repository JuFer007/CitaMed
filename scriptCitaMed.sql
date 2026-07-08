DROP DATABASE IF EXISTS CitaMed;
CREATE DATABASE CitaMed;
USE CitaMed;

-- ========================
-- 1. USUARIOS
-- ========================
INSERT INTO usuarios (user_name, password, rol, activo) VALUES
-- Admins
('admin01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('marcelo.alarcon',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('cristian.huaman',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('junior.zumaeta',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',          true),
('recep01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  true),
('recep02',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  true),
('recep03',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA',  false),
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
-- Paciente 1: cuenta de portal usando su correo electronico como usuario
('jc.quispe@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
-- Nuevo medico para cubrir Gastroenterologia (antes faltaba)
('med.silva',                  '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO',         true),
-- Pacientes 2 al 6: cuenta de portal usando su correo electronico como usuario
('maria.soto@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
('pedro.huaman@gmail.com',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
('lucia.paredes@gmail.com',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
('carlos.mendoza.p@gmail.com', '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
('ana.rojas@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE',       true),
-- Pacientes 7 al 30: cuenta de portal usando su correo electronico como usuario
('roberto.vargas@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('isabel.chavez@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('diego.fuentes@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('valentina.espinoza@gmail.com',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('andres.llontop@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('gabriela.zuloeta@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('ricardo.gonzales@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('sofia.chafloque@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('martin.idrogo@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('patricia.coronado@gmail.com',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('eduardo.saavedra@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('claudia.segura@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('marco.becerra@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('natalia.cubas@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('cesar.fernandez@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('milagros.puelles@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('jhon.guerrero@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('fiorella.tello@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true),
('alejandro.santisteban@gmail.com', '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);

-- ========================
-- 2. ESPECIALIDADES
-- ========================
INSERT INTO especialidades (nombre, descripcion, precio) VALUES
('Urología',                  'Diagnóstico y tratamiento del sistema urinario y reproductor masculino',                   120.00),
('Reumatología',              'Tratamiento de enfermedades articulares, musculares y autoinmunes',                      110.00),
('Medicina General',          'Atención médica primaria y consultas generales de salud',                                 80.00),
('Cardiología',               'Diagnóstico y tratamiento de enfermedades cardiovasculares',                             150.00),
('Pediatría',                 'Atención médica integral de niños desde el nacimiento hasta la adolescencia',             90.00),
('Ginecología y Obstetricia', 'Salud reproductiva femenina, embarazo y parto',                                         130.00),
('Traumatología y Ortopedia', 'Lesiones y enfermedades del aparato locomotor',                                         120.00),
('Neurología',                'Enfermedades del sistema nervioso central y periférico',                                140.00),
('Dermatología',              'Enfermedades de la piel, cabello y uñas',                                               100.00),
('Endocrinología',            'Trastornos hormonales y metabólicos',                                                   115.00),
('Gastroenterología',         'Enfermedades del sistema digestivo',                                                     125.00),
('Oftalmología',              'Enfermedades y cirugías del ojo',                                                        135.00);

-- ========================
-- 3. CONSULTORIOS
-- ========================
INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-101', 'Consultorio de Medicina General N°1',  true,  3, 3),
('C-102', 'Consultorio de Medicina General N°2',  true,  3, 3),
('C-103', 'Consultorio de Medicina General N°3',  false, 3, 3),
('C-201', 'Consultorio de Cardiología N°1',        true,  4, 3),
('C-202', 'Consultorio de Cardiología N°2',        true,  4, 3),
('C-301', 'Consultorio de Pediatría N°1',          true,  5, 3),
('C-302', 'Consultorio de Pediatría N°2',          false, 5, 3),
('C-401', 'Consultorio de Ginecología N°1',        true,  6, 3),
('C-402', 'Consultorio de Ginecología N°2',        true,  6, 3),
('C-501', 'Consultorio de Traumatología N°1',      true,  7, 3),
('C-601', 'Consultorio de Neurología N°1',         true,  8, 3),
('C-602', 'Consultorio de Neurología N°2',         true,  8, 3),
-- Consultorios agregados para cubrir especialidades que no tenian sala propia
('C-701', 'Consultorio de Urología N°1',           true,  1, 3),
('C-702', 'Consultorio de Urología N°2',           true,  1, 3),
('C-801', 'Consultorio de Reumatología N°1',       true,  2, 3),
('C-901', 'Consultorio de Dermatología N°1',       true,  9, 3),
('C-1001','Consultorio de Endocrinología N°1',     true,  10, 3),
('C-1101','Consultorio de Gastroenterología N°1',  true,  11, 3);

-- ========================
-- 4. EMPLEADOS
-- ========================
INSERT INTO empleados (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, salario, fecha_ingreso, activo, usuario_id) VALUES
-- Integrantes del equipo (ADMIN) apuntando a IDs 2, 3, 4
('MARCELO ARIEL',  'ALARCON',   'MANAY',    '74100001', '987000001', 'Av. Los Desarrolladores 101, Chiclayo', 'marcelo.alarcon@citamed.com',  '1999-05-15', 'MASCULINO', 4500.00, '2024-01-01', true,  2),
('CRISTIAN',       'HUAMAN',    'CRUZ',     '74100002', '987000002', 'Jr. Full Stack 202, Chiclayo',          'cristian.huaman@citamed.com',  '1999-08-22', 'MASCULINO', 4500.00, '2024-01-01', true,  3),
('JUNIOR FERNANDO','ZUMAETA',   'GOLAC',    '74100003', '987000003', 'Calle Backend 303, Chiclayo',           'junior.zumaeta@citamed.com',   '1999-11-10', 'MASCULINO', 4500.00, '2024-01-01', true,  4),
-- Admin genérico apuntando al ID 1
('CARLOS',         'MENDOZA',   'QUISPE',   '45678901', '987654321', 'Av. Los Olivos 123, Chiclayo',          'carlos.mendoza@citamed.com',   '1985-03-15', 'MASCULINO', 4200.00, '2018-01-10', true,  1),
-- Recepcionistas apuntando a IDs 5, 6 y 7 (antes faltaba el empleado del usuario 7)
('MARIA',          'LOPEZ',     'SANCHEZ',  '45678902', '987654322', 'Jr. Los Pinos 456, Chiclayo',           'maria.lopez@citamed.com',      '1990-07-22', 'FEMENINO',  1800.00, '2020-03-01', true,  5),
('ANA',            'PEREZ',     'CASTILLO', '45678903', '987654323', 'Calle Manco Capac 789, Chiclayo',       'ana.perez@citamed.com',        '1992-11-05', 'FEMENINO',  1800.00, '2021-06-15', true,  6),
('LUCIA',          'MEJIA',     'SOTO',     '45678904', '987654324', 'Jr. Progreso 890, Chiclayo',            'lucia.mejia@citamed.com',      '1994-02-19', 'FEMENINO',  1800.00, '2023-09-01', false, 7);

-- ========================
-- 5. MEDICOS
-- ========================
-- consultorio_id corregido para que coincida con la especialidad de cada medico
INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('LUIS',      'GARCIA',   'MONTOYA',  '12345601', '912345601', 'Av. Pardo 101, Chiclayo',            'luis.garcia@citamed.com',    '1978-05-12', 'MASCULINO', 'CMP-045231', true, '/uploads/medicos/medLuisGarcia.jpg',  1,  8,  13),
('ROSA',      'TORRES',   'GUTIERREZ','12345602', '912345602', 'Jr. Colon 202, Chiclayo',            'rosa.torres@citamed.com',    '1980-09-18', 'FEMENINO',  'CMP-038742', true, '/uploads/medicos/medRosaGutierrez.jpg',  2,  9,  15),
('MIGUEL',    'RAMIREZ',  'DELGADO',  '12345603', '912345603', 'Calle Real 303, Chiclayo',           'miguel.ramirez@citamed.com', '1975-03-27', 'MASCULINO', 'CMP-029183', true, '/uploads/medicos/medMiguelRamirez.jpg',  3,  10, 1),
('CARMEN',    'FLORES',   'ESPINOZA', '12345604', '912345604', 'Av. Venezuela 404, Chiclayo',        'carmen.flores@citamed.com',  '1982-07-08', 'FEMENINO',  'CMP-051647', true, '/uploads/medicos/medCarmenFlores.jpg',  4,  11, 4),
('JORGE',     'CASTRO',   'HUAMAN',   '12345605', '912345605', 'Jr. Tacna 505, Chiclayo',            'jorge.castro@citamed.com',   '1977-11-14', 'MASCULINO', 'CMP-034829', true, '/uploads/medicos/medJorgeCastro.jpg',  5,  12, 6),
('PATRICIA',  'HERRERA',  'LEON',     '12345606', '912345606', 'Av. Balta 606, Chiclayo',            'patricia.herrera@citamed.com','1983-01-30', 'FEMENINO',  'CMP-062318', true, '/uploads/medicos/medPatriciaHerrera.jpg',  6,  13, 8),
('FERNANDO',  'VEGA',     'QUISPE',   '12345607', '912345607', 'Calle Elias Aguirre 707, Chiclayo',  'fernando.vega@citamed.com',  '1979-06-22', 'MASCULINO', 'CMP-041095', true, '/uploads/medicos/medFernandoVega.jpg',  7,  14, 10),
('SANDRA',    'MORA',     'PAREDES',  '12345608', '912345608', 'Jr. Torres Paz 808, Chiclayo',       'sandra.mora@citamed.com',    '1981-10-03', 'FEMENINO',  'CMP-057382', true, '/uploads/medicos/medSandraMora.jpg',  8,  15, 11),
('HECTOR',    'RIVAS',    'CARDENAS', '12345609', '912345609', 'Av. Jose Balta 909, Chiclayo',       'hector.rivas@citamed.com',   '1976-04-17', 'MASCULINO', 'CMP-027461', true, '/uploads/medicos/medHectorRivas.jpg',  9,  16, 16),
('GABRIELA',  'PINTO',    'SALINAS',  '12345610', '912345610', 'Calle Siete de Enero 1010, Chiclayo','gabriela.pinto@citamed.com', '1984-08-25', 'FEMENINO',  'CMP-068754', true, '/uploads/medicos/medGabrielaPinto.jpg',  1,  17, 14),
-- Medicos agregados para cubrir Endocrinologia y Gastroenterologia (antes no existian)
('SILVIA',    'AGUILAR',  'REYES',    '12345611', '912345611', 'Av. Grau 1111, Chiclayo',            'silvia.aguilar@citamed.com', '1980-12-01', 'FEMENINO',  'CMP-071235', true, '/uploads/medicos/medSilviaAguilar.jpg', 10, 18, 17),
('RENZO',     'SILVA',    'CORDOVA',  '12345612', '912345612', 'Jr. Piura 1212, Chiclayo',           'renzo.silva@citamed.com',    '1979-03-09', 'MASCULINO', 'CMP-071899', true, '/uploads/medicos/medRenzoSilva.jpg',   11, 20, 18);

-- ========================
-- 6. HORARIOS MEDICOS
-- ========================
-- consultorio_id corregido para que coincida con el consultorio propio del medico
INSERT INTO horarios_medicos (dia, hora_inicio, hora_fin, activo, medico_id, consultorio_id) VALUES
('LUNES',     '08:00:00', '13:00:00', true,  1,  13),
('MIERCOLES', '08:00:00', '13:00:00', true,  1,  13),
('VIERNES',   '08:00:00', '12:00:00', true,  1,  13),
('MARTES',    '09:00:00', '14:00:00', true,  2,  15),
('JUEVES',    '09:00:00', '14:00:00', true,  2,  15),
('SABADO',    '08:00:00', '12:00:00', true,  2,  15),
('LUNES',     '14:00:00', '19:00:00', true,  3,  1),
('MIERCOLES', '14:00:00', '19:00:00', true,  3,  1),
('VIERNES',   '14:00:00', '19:00:00', true,  3,  1),
('MARTES',    '08:00:00', '13:00:00', true,  4,  4),
('JUEVES',    '08:00:00', '13:00:00', true,  4,  4),
('SABADO',    '09:00:00', '12:00:00', true,  4,  4),
('LUNES',     '08:00:00', '14:00:00', true,  5,  6),
('MIERCOLES', '08:00:00', '14:00:00', true,  5,  6),
('MARTES',    '15:00:00', '20:00:00', true,  6,  8),
('JUEVES',    '15:00:00', '20:00:00', true,  6,  8),
('VIERNES',   '15:00:00', '20:00:00', true,  6,  8),
('LUNES',     '09:00:00', '14:00:00', true,  7,  10),
('MIERCOLES', '09:00:00', '14:00:00', true,  8,  11),
('VIERNES',   '09:00:00', '13:00:00', true,  9,  16),
('LUNES',     '08:00:00', '13:00:00', true,  10, 14),
('MIERCOLES', '08:00:00', '13:00:00', true,  10, 14),
('VIERNES',   '08:00:00', '13:00:00', true,  10, 14),
-- Horarios de los medicos agregados
('MARTES',    '08:00:00', '13:00:00', true,  11, 17),
('JUEVES',    '08:00:00', '13:00:00', true,  11, 17),
('LUNES',     '15:00:00', '19:00:00', true,  12, 18),
('MIERCOLES', '15:00:00', '19:00:00', true,  12, 18);

-- ========================
-- 7. PACIENTES
-- ========================
INSERT INTO pacientes (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, grupo_sanguineo, activo) VALUES
('JUAN CARLOS',      'QUISPE',      'MAMANI',     '71234501', '951234501', 'Av. Agricultura 101, Chiclayo',      'jc.quispe@gmail.com',              '1990-02-14', 'MASCULINO', 'O_POSITIVO', TRUE),
('MARIA ELENA',      'SOTO',        'VARGAS',     '71234502', '951234502', 'Jr. Los Alamos 202, Chiclayo',       'maria.soto@gmail.com',             '1985-06-23', 'FEMENINO',  'A_POSITIVO', TRUE),
('PEDRO PABLO',      'HUAMAN',      'QUISPE',     '71234503', '951234503', 'Calle Los Fresnos 303, Chiclayo',    'pedro.huaman@gmail.com',           '1978-11-05', 'MASCULINO', 'B_POSITIVO', TRUE),
('LUCIA ROSA',       'PAREDES',     'TORRES',     '71234504', '951234504', 'Av. Fitzcarrald 404, Chiclayo',      'lucia.paredes@gmail.com',          '1995-03-18', 'FEMENINO',  'AB_POSITIVO', TRUE),
('CARLOS ALBERTO',   'MENDOZA',     'LOPEZ',      '71234505', '951234505', 'Jr. San Martin 505, Chiclayo',       'carlos.mendoza.p@gmail.com',       '1988-07-30', 'MASCULINO', 'O_NEGATIVO', TRUE),
('ANA SOFIA',        'ROJAS',       'CASTRO',     '71234506', '951234506', 'Calle Dos de Mayo 606, Chiclayo',    'ana.rojas@gmail.com',              '1992-09-12', 'FEMENINO',  'A_NEGATIVO', TRUE),
('ROBERTO JESUS',    'VARGAS',      'DIAZ',       '71234507', '951234507', 'Av. Saenz Pena 707, Chiclayo',       'roberto.vargas@gmail.com',         '1975-01-08', 'MASCULINO', 'B_NEGATIVO', TRUE),
('ISABEL CRISTINA',  'CHAVEZ',      'MORALES',    '71234508', '951234508', 'Jr. Ladislao Espinar 808, Chiclayo', 'isabel.chavez@gmail.com',          '1983-05-27', 'FEMENINO',  'O_POSITIVO', TRUE),
('DIEGO ALEJANDRO',  'FUENTES',     'HERRERA',    '71234509', '951234509', 'Calle Pedro Ruiz 909, Chiclayo',     'diego.fuentes@gmail.com',          '1998-12-03', 'MASCULINO', 'A_POSITIVO', TRUE),
('VALENTINA',        'ESPINOZA',    'NUNEZ',      '71234510', '951234510', 'Av. Leguia 1010, Chiclayo',          'valentina.espinoza@gmail.com',     '2001-04-16', 'FEMENINO',  'AB_NEGATIVO', TRUE),
('ANDRES FELIPE',    'LLONTOP',     'BANCES',     '71234511', '951234511', 'Jr. Colon 111, Chiclayo',            'andres.llontop@gmail.com',         '1970-08-21', 'MASCULINO', 'O_POSITIVO', TRUE),
('GABRIELA NOEMI',   'ZULOETA',     'CARRASCO',   '71234512', '951234512', 'Av. Pedro Ruiz 222, Chiclayo',       'gabriela.zuloeta@gmail.com',       '1987-10-09', 'FEMENINO',  'B_POSITIVO', TRUE),
('RICARDO MANUEL',   'GONZALES',    'MURO',       '71234513', '951234513', 'Calle Izaga 333, Chiclayo',          'ricardo.gonzales@gmail.com',       '1993-02-28', 'MASCULINO', 'A_POSITIVO', TRUE),
('SOFIA VALENTINA',  'CHAFLOQUE',   'SOLIS',      '71234514', '951234514', 'Jr. 8 de Octubre 444, Chiclayo',     'sofia.chafloque@gmail.com',        '2000-06-14', 'FEMENINO',  'O_POSITIVO', TRUE),
('MARTIN EDUARDO',   'IDROGO',      'BUSTAMANTE', '71234515', '951234515', 'Av. Los Incas 555, Chiclayo',        'martin.idrogo@gmail.com',          '1965-11-30', 'MASCULINO', 'A_NEGATIVO', TRUE),
('PATRICIA LUZ',     'CORONADO',    'DIAZ',       '71234516', '951234516', 'Calle Real 666, Chiclayo',           'patricia.coronado@gmail.com',      '1980-03-07', 'FEMENINO',  'B_POSITIVO', TRUE),
('EDUARDO JESUS',    'SAAVEDRA',    'NEYRA',      '71234517', '951234517', 'Jr. San Jose 777, Chiclayo',         'eduardo.saavedra@gmail.com',       '1972-07-19', 'MASCULINO', 'O_POSITIVO', TRUE),
('CLAUDIA BEATRIZ',  'SEGURA',      'MONTOYA',    '71234518', '951234518', 'Av. Chiclayo 888, Chiclayo',         'claudia.segura@gmail.com',         '1996-09-02', 'FEMENINO',  'AB_POSITIVO', TRUE),
('MARCO ANTONIO',    'BECERRA',     'VILLANUEVA', '71234519', '951234519', 'Calle Los Pinos 999, Chiclayo',      'marco.becerra@gmail.com',          '1982-01-25', 'MASCULINO', 'A_POSITIVO', TRUE),
('NATALIA ANDREA',   'CUBAS',       'MEJIA',      '71234520', '951234520', 'Jr. Victor Raul 1100, Chiclayo',     'natalia.cubas@gmail.com',          '1991-05-11', 'FEMENINO',  'O_NEGATIVO', TRUE),
('CESAR AUGUSTO',    'FERNANDEZ',   'PAZ',        '71234521', '951234521', 'Av. Chinchaysuyo 200, Chiclayo',     'cesar.fernandez@gmail.com',        '1969-03-15', 'MASCULINO', 'B_POSITIVO', TRUE),
('MILAGROS',         'PUELLES',     'ASALDE',     '71234522', '951234522', 'Jr. Los Jazmines 300, Chiclayo',     'milagros.puelles@gmail.com',       '1976-08-29', 'FEMENINO',  'O_POSITIVO', TRUE),
('JHON ALEXANDER',   'GUERRERO',    'RIVADENEYRA','71234523', '951234523', 'Calle Manco Capac 400, Chiclayo',    'jhon.guerrero@gmail.com',          '1999-12-17', 'MASCULINO', 'A_POSITIVO', TRUE),
('FIORELLA MILAGROS','TELLO',       'PERALES',    '71234524', '951234524', 'Av. Venezuela 500, Chiclayo',        'fiorella.tello@gmail.com',         '2003-04-05', 'FEMENINO',  'AB_POSITIVO', TRUE),
('ALEJANDRO LUIS',   'SANTISTEBAN', 'CABREJOS',   '71234525', '951234525', 'Jr. Elias Aguirre 600, Chiclayo',    'alejandro.santisteban@gmail.com',  '1960-06-20', 'MASCULINO', 'O_POSITIVO', TRUE);

-- Vincular pacientes 1 al 6 con su cuenta de portal (usuario = su correo electronico)
UPDATE pacientes SET usuario_id = 19 WHERE dni = '71234501'; -- JUAN CARLOS QUISPE MAMANI    -> jc.quispe@gmail.com
UPDATE pacientes SET usuario_id = 21 WHERE dni = '71234502'; -- MARIA ELENA SOTO VARGAS      -> maria.soto@gmail.com
UPDATE pacientes SET usuario_id = 22 WHERE dni = '71234503'; -- PEDRO PABLO HUAMAN QUISPE    -> pedro.huaman@gmail.com
UPDATE pacientes SET usuario_id = 23 WHERE dni = '71234504'; -- LUCIA ROSA PAREDES TORRES    -> lucia.paredes@gmail.com
UPDATE pacientes SET usuario_id = 24 WHERE dni = '71234505'; -- CARLOS ALBERTO MENDOZA LOPEZ -> carlos.mendoza.p@gmail.com
UPDATE pacientes SET usuario_id = 25 WHERE dni = '71234506'; -- ANA SOFIA ROJAS CASTRO       -> ana.rojas@gmail.com

-- Vincular pacientes 7 al 30 con su cuenta de portal (usuario = su correo electronico)
UPDATE pacientes SET usuario_id = 26 WHERE dni = '71234507'; -- ROBERTO JESUS VARGAS DIAZ           -> roberto.vargas@gmail.com
UPDATE pacientes SET usuario_id = 27 WHERE dni = '71234508'; -- ISABEL CRISTINA CHAVEZ MORALES      -> isabel.chavez@gmail.com
UPDATE pacientes SET usuario_id = 28 WHERE dni = '71234509'; -- DIEGO ALEJANDRO FUENTES HERRERA     -> diego.fuentes@gmail.com
UPDATE pacientes SET usuario_id = 29 WHERE dni = '71234510'; -- VALENTINA ESPINOZA NUNEZ            -> valentina.espinoza@gmail.com
UPDATE pacientes SET usuario_id = 30 WHERE dni = '71234511'; -- ANDRES FELIPE LLONTOP BANCES        -> andres.llontop@gmail.com
UPDATE pacientes SET usuario_id = 31 WHERE dni = '71234512'; -- GABRIELA NOEMI ZULOETA CARRASCO     -> gabriela.zuloeta@gmail.com
UPDATE pacientes SET usuario_id = 32 WHERE dni = '71234513'; -- RICARDO MANUEL GONZALES MURO        -> ricardo.gonzales@gmail.com
UPDATE pacientes SET usuario_id = 33 WHERE dni = '71234514'; -- SOFIA VALENTINA CHAFLOQUE SOLIS     -> sofia.chafloque@gmail.com
UPDATE pacientes SET usuario_id = 34 WHERE dni = '71234515'; -- MARTIN EDUARDO IDROGO BUSTAMANTE    -> martin.idrogo@gmail.com
UPDATE pacientes SET usuario_id = 35 WHERE dni = '71234516'; -- PATRICIA LUZ CORONADO DIAZ          -> patricia.coronado@gmail.com
UPDATE pacientes SET usuario_id = 36 WHERE dni = '71234517'; -- EDUARDO JESUS SAAVEDRA NEYRA        -> eduardo.saavedra@gmail.com
UPDATE pacientes SET usuario_id = 37 WHERE dni = '71234518'; -- CLAUDIA BEATRIZ SEGURA MONTOYA      -> claudia.segura@gmail.com
UPDATE pacientes SET usuario_id = 38 WHERE dni = '71234519'; -- MARCO ANTONIO BECERRA VILLANUEVA    -> marco.becerra@gmail.com
UPDATE pacientes SET usuario_id = 39 WHERE dni = '71234520'; -- NATALIA ANDREA CUBAS MEJIA          -> natalia.cubas@gmail.com
UPDATE pacientes SET usuario_id = 40 WHERE dni = '71234521'; -- CESAR AUGUSTO FERNANDEZ PAZ         -> cesar.fernandez@gmail.com
UPDATE pacientes SET usuario_id = 41 WHERE dni = '71234522'; -- MILAGROS PUELLES ASALDE             -> milagros.puelles@gmail.com
UPDATE pacientes SET usuario_id = 42 WHERE dni = '71234523'; -- JHON ALEXANDER GUERRERO RIVADENEYRA -> jhon.guerrero@gmail.com
UPDATE pacientes SET usuario_id = 43 WHERE dni = '71234524'; -- FIORELLA MILAGROS TELLO PERALES     -> fiorella.tello@gmail.com
UPDATE pacientes SET usuario_id = 44 WHERE dni = '71234525'; -- ALEJANDRO LUIS SANTISTEBAN CABREJOS -> alejandro.santisteban@gmail.com

-- ========================
-- 8. HISTORIALES MEDICOS
-- ========================
INSERT INTO historiales_medicos (paciente_id) VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),
(21),(22),(23),(24),(25),(26),(27),(28),(29),(30);

-- ========================
-- 9. CITAS
-- ========================
-- medico_id / consultorio_id corregidos para que coincidan con la especialidad
-- que exige el motivo_consulta (y con el diagnostico ya registrado abajo)
INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
(1,  8,  11, '2026-05-04 08:00:00', 'Dolor de cabeza persistente y mareos',      'ATENDIDA'), -- ID 1  -> Neurologia
(2,  4,  4,  '2026-05-05 09:00:00', 'Revision de presion arterial alta',         'ATENDIDA'), -- ID 2  -> Cardiologia
(3,  5,  6,  '2026-05-05 14:00:00', 'Control pediatrico de nino de 5 anos',       'ATENDIDA'), -- ID 3  -> Pediatria
(4,  6,  8,  '2026-05-06 08:00:00', 'Consulta por irregularidades menstruales',   'ATENDIDA'), -- ID 4  -> Ginecologia
(5,  7,  10, '2026-05-06 09:00:00', 'Dolor en rodilla derecha tras caida',        'ATENDIDA'), -- ID 5  -> Traumatologia
(6,  8,  11, '2026-05-07 15:00:00', 'Episodios de migrana frecuentes',            'ATENDIDA'), -- ID 6  -> Neurologia
(7,  3,  1,  '2026-05-07 08:00:00', 'Tos persistente y fiebre leve',              'ATENDIDA'), -- ID 7  -> Medicina General
(8,  4,  4,  '2026-05-08 09:00:00', 'Palpitaciones y cansancio',                  'ATENDIDA'), -- ID 8  -> Cardiologia
(1,  3,  1,  '2026-05-10 08:00:00', 'Control medico trimestral',                  'ATENDIDA'), -- ID 9  -> Medicina General
(9,  9,  16, '2026-05-11 09:00:00', 'Dermatitis en brazos',                       'ATENDIDA'), -- ID 10 -> Dermatologia
(5,  7,  10, '2026-05-11 08:00:00', 'Revision de fractura curada',                'ATENDIDA'), -- ID 11 -> Traumatologia
(15, 11, 17, '2026-05-11 09:00:00', 'Hipotiroidismo, control mensual',            'ATENDIDA'), -- ID 12 -> Endocrinologia
(10, 11, 17, '2026-05-12 09:00:00', 'Fatiga extrema y aumento de peso',           'ATENDIDA'), -- ID 13 -> Endocrinologia
(11, 12, 18, '2026-05-12 08:00:00', 'Nauseas y dolor abdominal',                  'ATENDIDA'), -- ID 14 -> Gastroenterologia
(12, 3,  1,  '2026-05-13 08:00:00', 'Chequeo medico general',                     'ATENDIDA'), -- ID 15 -> Medicina General
(13, 5,  6,  '2026-05-13 14:00:00', 'Fiebre alta en nino de 8 anos',              'ATENDIDA'), -- ID 16 -> Pediatria
(14, 6,  8,  '2026-05-14 08:00:00', 'Control de embarazo mes 5',                  'ATENDIDA'), -- ID 17 -> Ginecologia
(15, 7,  10, '2026-05-14 08:00:00', 'Dolor lumbar cronico',                       'ATENDIDA'), -- ID 18 -> Traumatologia
(22, 12, 18, '2026-05-14 08:00:00', 'Gastroenterologia, seguimiento',             'ATENDIDA'), -- ID 19 -> Gastroenterologia
(28, 4,  4,  '2026-05-15 09:00:00', 'Hipertension, ajuste de tratamiento',        'ATENDIDA'), -- ID 20 -> Cardiologia
(16, 3,  1,  '2026-05-18 08:00:00', 'Resfriado y dolor de garganta',              'ATENDIDA'), -- ID 21 -> Medicina General
(17, 8,  11, '2026-05-19 15:00:00', 'Entumecimiento en extremidades',             'ATENDIDA'), -- ID 22 -> Neurologia
(18, 4,  4,  '2026-05-19 09:00:00', 'Control post-operatorio cardiaco',           'ATENDIDA'), -- ID 23 -> Cardiologia
(19, 12, 18, '2026-05-20 08:00:00', 'Gastritis y acidez estomacal',               'ATENDIDA'), -- ID 24 -> Gastroenterologia
(20, 6,  8,  '2026-05-20 08:00:00', 'Revision de quiste ovarico',                 'ATENDIDA'), -- ID 25 -> Ginecologia
(21, 5,  6,  '2026-05-21 14:00:00', 'Vacunacion y control de crecimiento',        'ATENDIDA'), -- ID 26 -> Pediatria
(22, 12, 18, '2026-05-21 08:00:00', 'Colon irritable, control mensual',           'ATENDIDA'), -- ID 27 -> Gastroenterologia
(23, 7,  10, '2026-05-25 08:00:00', 'Esguince de tobillo izquierdo',              'ATENDIDA'), -- ID 28 -> Traumatologia
(24, 9,  16, '2026-05-26 09:00:00', 'Acne y problemas en piel grasa',             'ATENDIDA'), -- ID 29 -> Dermatologia
(25, 11, 17, '2026-05-26 09:00:00', 'Diabetes tipo 2, control trimestral',        'ATENDIDA'), -- ID 30 -> Endocrinologia
(10, 7,  10, '2026-05-27 09:00:00', 'Dolor de espalda y fatiga',                  'PROGRAMADA'), -- ID 31 -> Traumatologia
(6,  8,  11, '2026-05-27 15:00:00', 'Vertigo y perdida de equilibrio',            'PROGRAMADA'), -- ID 32 -> Neurologia
(28, 4,  4,  '2026-05-28 08:00:00', 'Presion alta y dolor de cabeza',             'PROGRAMADA'), -- ID 33 -> Cardiologia
(3,  5,  6,  '2026-05-29 14:00:00', 'Control de asma infantil',                   'PROGRAMADA'), -- ID 34 -> Pediatria
(2,  4,  4,  '2026-05-30 09:00:00', 'Arritmia, control semestral',                'PROGRAMADA'); -- ID 35 -> Cardiologia

-- ========================
-- 10. PAGOS
-- ========================
-- monto recalculado para coincidir con especialidades.precio del medico asignado
INSERT INTO pagos (cita_id, monto, metodo_pago, estado, fecha_pago) VALUES
(1,  140.00, 'EFECTIVO',      'PAGADO', '2026-05-04 08:05:00'),
(2,  150.00, 'TARJETA',       'PAGADO', '2026-05-05 09:10:00'),
(3,  90.00,  'EFECTIVO',      'PAGADO', '2026-05-05 14:05:00'),
(4,  130.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-06 08:05:00'),
(5,  120.00, 'EFECTIVO',      'PAGADO', '2026-05-06 09:05:00'),
(6,  140.00, 'TARJETA',       'PAGADO', '2026-05-07 15:05:00'),
(7,  80.00,  'EFECTIVO',      'PAGADO', '2026-05-07 08:05:00'),
(8,  150.00, 'EFECTIVO',      'PAGADO', '2026-05-08 09:05:00'),
(9,  80.00,  'TARJETA',       'PAGADO', '2026-05-10 08:05:00'),
(10, 100.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-11 09:05:00'),
(11, 120.00, 'EFECTIVO',      'PAGADO', '2026-05-11 08:05:00'),
(12, 115.00, 'EFECTIVO',      'PAGADO', '2026-05-11 09:05:00'),
(13, 115.00, 'TARJETA',       'PAGADO', '2026-05-12 09:05:00'),
(14, 125.00, 'EFECTIVO',      'PAGADO', '2026-05-12 08:05:00'),
(15, 80.00,  'TRANSFERENCIA', 'PAGADO', '2026-05-13 08:05:00'),
(16, 90.00,  'EFECTIVO',      'PAGADO', '2026-05-13 14:05:00'),
(17, 130.00, 'TARJETA',       'PAGADO', '2026-05-14 08:05:00'),
(18, 120.00, 'TARJETA',       'PAGADO', '2026-05-14 08:05:00'),
(19, 125.00, 'EFECTIVO',      'PAGADO', '2026-05-14 08:05:00'),
(20, 150.00, 'EFECTIVO',      'PAGADO', '2026-05-15 09:05:00'),
(21, 80.00,  'EFECTIVO',      'PAGADO', '2026-05-18 08:05:00'),
(22, 140.00, 'TARJETA',       'PAGADO', '2026-05-19 15:05:00'),
(23, 150.00, 'EFECTIVO',      'PAGADO', '2026-05-19 09:05:00'),
(24, 125.00, 'TARJETA',       'PAGADO', '2026-05-20 08:05:00'),
(25, 130.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-20 08:05:00'),
(26, 90.00,  'EFECTIVO',      'PAGADO', '2026-05-21 14:05:00'),
(27, 125.00, 'TARJETA',       'PAGADO', '2026-05-21 08:05:00'),
(28, 120.00, 'EFECTIVO',      'PAGADO', '2026-05-25 08:05:00'),
(29, 100.00, 'EFECTIVO',      'PAGADO', '2026-05-26 09:05:00'),
(30, 115.00, 'TARJETA',       'PAGADO', '2026-05-26 09:05:00');

-- ========================
-- 12. DIAGNOSTICOS
-- ========================
-- (contenido sin cambios: ya era coherente con el motivo_consulta;
--  ahora tambien es coherente con el medico asignado en la seccion 9)
INSERT INTO diagnosticos (enfermedad, descripcion, receta, indicaciones, cita_id) VALUES
('CEFALEA TENSIONAL',                  'Dolor de cabeza de tipo tensional sin signos neurologicos focales',              'Ibuprofeno 400mg / Paracetamol 500mg',                                                                   'Reposo relativo, evitar pantallas, hidratacion. Tomar cada 8 horas con alimentos.',      1),
('HIPERTENSION ARTERIAL',              'HTA grado 2, en control farmacologico con enalapril',                            'Enalapril 10mg cada 12h / Hidroclorotiazida 12.5mg',                                                     'Dieta hiposodica, ejercicio moderado diario. No suspender sin consultar.',                2),
('DESARROLLO NORMAL',                  'Nino sano, desarrollo psicomotor adecuado para 5 anos',                         'Vitamina D 1000UI / Vitamina C 500mg',                                                                  'Dieta equilibrada, actividad fisica regular. Suplemento vitaminico.',                     3),
('OLIGOMENORREA',                      'Ciclos menstruales irregulares, posible etiologia hormonal',                    'Anticonceptivo oral combinado 1 comprimido cada 24h / Acido folico 5mg',                                 'Tomar a la misma hora todos los dias. Control ecografico en 30 dias.',                   4),
('CONTUSION DE RODILLA',               'Traumatismo directo en cara anterior de rodilla derecha sin fractura',          'Naproxeno 500mg cada 12h / Tramadol 50mg cada 8h segun dolor',                                           'Reposo, hielo local 20 min cada 4h. Puede producir somnolencia.',                        5),
('MIGRANA CON AURA',                   'Migrana cronica episodica con fenomenos visuales previos',                      'Propranolol 40mg/dia / Sumatriptan 50mg en crisis',                                                      'No suspender bruscamente. Usar sumatriptan al inicio del dolor. Max 200mg/dia.',         6),
('INFECCION RESPIRATORIA AGUDA',       'Rinofaringitis aguda de probable etiologia viral',                              'Amoxicilina 500mg cada 8h / Ibuprofeno 400mg cada 8h',                                                   'Terminar tratamiento completo. Solo ibuprofeno si fiebre >38°. Hidratacion.',            7),
('TAQUICARDIA SINUSAL',                'Taquicardia sinusal por ansiedad, sin cardiopatia subyacente',                 'Atenolol 25mg/dia / Loratadina 10mg/dia',                                                               'Tomar con desayuno. Controlar frecuencia cardiaca. Reducir cafeina.',                    8),
('DERMATITIS ATOPICA',                 'Dermatitis atopica leve en pliegues de codos y rodillas',                      'Betametasona crema 0.05% topico / Emoliente corporal',                                                   'Aplicar capa fina en zona afectada. Emoliente tras el bano. No en cara.',                9),
('HIPOTIROIDISMO PRIMARIO',            'Hipotiroidismo diagnosticado por TSH elevada y T4 libre bajo',                 'Levotiroxina 50mcg en ayunas',                                                                           'Esperar 30 min antes de comer. No con calcio o hierro. Control TSH en 4 sem.',           10),
('GASTRITIS CRONICA',                  'Gastritis antral cronica no erosiva, H. pylori negativo',                      'Omeprazol 20mg antes del desayuno / Sucralfato 1g 3 veces al dia',                                       'Dieta blanda sin irritantes. Evitar AINES. Sucralfato 1h antes de comidas.',             11),
('SIN PATOLOGIA AGUDA',                'Examen fisico y laboratorios dentro de rangos normales',                       '',                                                                                                        'Continuar habitos saludables. Actividad fisica 150 min/semana. Control anual.',          12),
('AMIGDALITIS BACTERIANA',             'Amigdalitis pultacea compatible con Streptococcus pyogenes',                   'Amoxicilina 500mg cada 8h / Ibuprofeno pediatrico 200mg',                                                'Completar tratamiento 7 dias. Gargaras salinas. Calcular ibuprofeno por peso.',          13),
('EMBARAZO NORMAL SEMANA 22',          'Gestacion de bajo riesgo con feto en adecuadas condiciones',                   'Acido folico 1mg/dia / Sulfato ferroso 300mg',                                                           'Indispensable en embarazo. Tomar con vitamina C para absorcion de hierro.',              14),
('LUMBALGIA MECANICA',                 'Lumbalgia inespecifica de tipo mecanico sin irradiacion',                      'Ciclobenzaprina 5mg cada 8h / Diclofenaco 75mg cada 12h',                                                'Fisioterapia 3 veces/semana. Faja lumbar en actividad. Puede causar somnolencia.',       15),
('FARINGOAMIGDALITIS VIRAL',           'Infeccion viral de vias respiratorias altas sin complicaciones',              'Paracetamol 1g cada 8h',                                                                                 'Reposo relativo 48h. No sobrepasar 3g al dia. Hidratacion abundante.',                   16),
('NEUROPATIA PERIFERICA LEVE',         'Parestesias en manos sin deficit motor, origen a determinar',                 'Vitamina B1 100mg/dia / Vitamina B12 500mcg/dia',                                                        'Ejercicios de movilidad de manos. Tomar con el desayuno.',                               17),
('POST-OPERATORIO BYPASS',             'Buena evolucion post bypass aortocoronario de doble vaso',                    'Aspirina 100mg/dia / Clopidogrel 75mg/dia / Warfarina 5mg/dia',                                          'Antiagregante + anticoagulante. Control INR semanal. Consultar antes de procedimientos.',18),
('GASTRITIS POR AINES',                'Irritacion gastrica secundaria al uso cronico de ibuprofeno',                 'Omeprazol 20mg antes del desayuno',                                                                      'Suspender AINES. Dieta blanda. Evitar alcohol.',                                         19),
('QUISTE OVARICO FUNCIONAL',           'Quiste folicular en ovario derecho de 3cm, sin tabiques',                     'Anticonceptivo oral 1 comprimido cada 24h',                                                              'Tomar a la misma hora. Ecografia de control en 60 dias.',                                20),
('DESARROLLO NORMAL',                  'Crecimiento y desarrollo acordes a percentiles para la edad',                 'Sulfato ferroso 300mg/dia / Vitamina D 400UI/dia',                                                       'Vacuna triple viral aplicada. Suplemento para crecimiento.',                             21),
('SINDROME DE INTESTINO IRRITABLE',    'SII con predominio diarreico, criterios de Roma IV',                          'Loperamida 2mg segun necesidad / Probiotico Lactobacillus 1 capsula/dia',                                 'Dieta baja en FODMAP. Evitar lactosa y gluten. Refrigerar probiotico.',                   22),
('ESGUINCE DE TOBILLO GRADO II',       'Lesion ligamentosa parcial en tobillo izquierdo',                             'Ibuprofeno 400mg cada 8h / Diclofenaco gel 1% topico',                                                   'Inmovilizacion 10 dias. Hielo local. Aplicar gel con masaje suave.',                     23),
('ACNE VULGAR GRADO 2',                'Acne inflamatorio moderado en region facial y dorsal',                        'Tretinona crema 0.025% nocturna / Doxiciclina 100mg cada 12h',                                           'Protector solar diario. Doxiciclina con abundante agua. No acostarse inmediatamente.',   24),
('DIABETES MELLITUS TIPO 2',           'DM2 descompensada con HbA1c de 9.2%',                                        'Metformina 850mg cada 12h / Insulina Glargina 10 UI nocturna',                                           'Control glucemico diario. Dieta diabetica estricta. Rotar zonas de insulina.',           25),
('LUMBALGIA OCUPACIONAL',              'Dolor lumbar secundario a postura inadecuada y sobrecarga',                   'Ciclobenzaprina 5mg nocturna / Diclofenaco 75mg cada 12h',                                               'Fisioterapia lumbar. Cambio ergonomico en trabajo. Faja de soporte.',                    26),
('VERTIGO POSICIONAL BENIGNO',         'VPPB de canal posterior derecho, test de Dix-Hallpike positivo',              'Betahistina 16mg cada 8h / Dimenhidrinato 50mg cada 8h si vertigo',                                      'Maniobra de Epley realizada. Ejercicios vestibulares. Puede causar somnolencia.',        27),
('HIPERTENSION ARTERIAL NO CONTROLADA','HTA con cifras persistentemente elevadas pese a monoterapia',                 'Amlodipino 5mg/dia / Losartan 50mg/dia',                                                                 'Dieta hiposodica estricta. Control semanal de presion. No suspender.',                   28),
('ASMA BRONQUIAL',                     'Asma intermitente en nino con FEV1 80% del predicho',                          'Salbutamol inhalador 100mcg c/4-6h en crisis / Budesonida inhalada 200mcg',                              'Max 4 inhalaciones por crisis. Enjuagar boca tras cada uso.',                            29),
('FIBRILACION AURICULAR',              'FA paroxistica en seguimiento con anticoagulacion oral',                      'Warfarina 5mg/dia / Digoxina 0.125mg/dia',                                                               'Control INR mensual. Controlar pulso antes de digoxina. Evitar AINES.',                  30);

-- ========================
-- 13. CONSULTAS
-- ========================
-- (formulario publico de contacto, no referencia FKs de pacientes/medicos - sin cambios)
INSERT INTO consultas (nombre, email, mensaje, fecha_envio, leido, respondido, respuesta, fecha_respuesta, respondido_por) VALUES
('María García López', 'maria.garcia@gmail.com', 'Buenos días, quisiera saber si atienden pacientes diabéticos y qué especialista recomiendan para mi caso.', '2026-06-01 09:15:00', TRUE, TRUE, 'Buenos días María. Contamos con endocrinólogos especializados en diabetes. Puede agendar una cita con el Dr. Ricardo Mendoza en nuestras instalaciones. Gracias por contactarnos.', '2026-06-01 10:30:00', 'Dr. Ricardo Mendoza'),
('Carlos Paredes Ruiz', 'carlos.paredes@hotmail.com', 'Hola, estoy interesado en saber los precios de las consultas por especialidad. ¿Podrían enviarme un listado?', '2026-06-02 11:00:00', TRUE, TRUE, 'Hola Carlos. Puede ver los precios de cada especialidad directamente en nuestra página web en la sección Especialidades. Los costos van desde S/80 hasta S/150 según la especialidad. Saludos.', '2026-06-02 14:20:00', 'Admin'),
('Ana Torres Torres', 'ana.torres@yahoo.com', 'Necesito reprogramar la cita de mi mamá, ¿cuál es el procedimiento?', '2026-06-03 08:45:00', TRUE, TRUE, 'Hola Ana. Para reprogramar una cita puede llamarnos al +51 922 626 148 o acercarse a recepción. Necesitamos el nombre completo del paciente y la fecha/hora actual de la cita. Gracias.', '2026-06-03 09:15:00', 'Recepcionista'),
('Jorge Salazar Vega', 'jorge.salazar@gmail.com', 'Excelente atención recibida hoy con el Dr. López. Muy profesional y amable.', '2026-06-04 13:30:00', TRUE, TRUE, 'Agradecemos sus palabras Jorge. Nos alegra saber que tuvo una buena experiencia con el Dr. López. Lo esperamos nuevamente cuando lo necesite.', '2026-06-04 15:00:00', 'Admin'),
('Lucía Fernández Rojas', 'lucia.fr@outlook.com', '¿Cuentan con servicio de emergencias las 24 horas? Mi esposo tuvo un accidente y no sabemos si ir a su clínica.', '2026-06-05 19:45:00', TRUE, TRUE, 'Hola Lucía. Sí, contamos con atención de emergencias las 24 horas, los 7 días de la semana. Puede acercarse a nuestra sede en Av. Luis González 1182, Chiclayo. Esperamos que su esposo se recupere pronto.', '2026-06-05 20:30:00', 'Dr. Ricardo Mendoza'),
('Pedro Huamán Quispe', 'pedro.hq@gmail.com', 'Quisiera saber si aceptan pagos con tarjeta de crédito o solo efectivo.', '2026-06-06 10:10:00', TRUE, TRUE, 'Hola Pedro. Actualmente solo aceptamos pago en efectivo en nuestras instalaciones. El pago se realiza al momento de la cita. Gracias por su consulta.', '2026-06-06 11:00:00', 'Admin'),
('Rosa Castillo Morales', 'rosa.castillo@gmail.com', 'Hola, soy paciente nueva y quisiera saber qué documentos debo llevar para mi primera consulta.', '2026-06-07 09:00:00', TRUE, TRUE, 'Hola Rosa. Para su primera consulta debe traer su DNI original. Si tiene exámenes previos o historial médico, también puede traerlos para que el médico los revise. La esperamos.', '2026-06-07 10:15:00', 'Recepcionista'),
('Miguel Ángel Rivas', 'miguel.rivas@hotmail.com', '¿Tienen convenio con seguros médicos? Tengo el seguro de SaludTotal.', '2026-06-08 15:30:00', TRUE, FALSE, NULL, NULL, NULL),
('Diana Prado Linares', 'diana.prado@gmail.com', 'Tuve una consulta hace dos días y el doctor me recetó unos análisis. ¿Los resultados se los puedo enviar por correo?', '2026-06-09 11:45:00', TRUE, TRUE, 'Hola Diana. Puede traer los resultados personalmente a nuestra sede o si prefiere enviarlos al correo contacto@citamed.pe indicando su nombre completo y fecha de la cita. Saludos.', '2026-06-09 14:00:00', 'Admin'),
('Fernando Bellido Chunga', 'fernando.bellido@gmail.com', 'Excelente servicio. El Dr. Herrera me explicó todo con mucha paciencia. Muy recomendado.', '2026-06-10 12:20:00', TRUE, TRUE, 'Muchas gracias Fernando. Nos alegra mucho su comentario. El Dr. Herrera es uno de nuestros mejores especialistas. Lo esperamos en su próximo control.', '2026-06-10 13:45:00', 'Admin'),
('Elena Cueva Sánchez', 'elena.cueva@yahoo.com', 'Quisiera saber si hay estacionamiento para pacientes en la clínica.', '2026-06-11 16:00:00', TRUE, TRUE, 'Hola Elena. Sí, contamos con estacionamiento gratuito para nuestros pacientes en la parte posterior de la clínica. Capacidad para 20 vehículos. Gracias por consultarnos.', '2026-06-11 17:00:00', 'Recepcionista'),
('Luis Alberto Neira', 'luis.neira@gmail.com', 'Llamé varias veces hoy y nadie contestó. Necesito cancelar mi cita del viernes.', '2026-06-12 09:30:00', TRUE, TRUE, 'Hola Luis, lamentamos el inconveniente. Hemos tenido problemas con la línea telefónica. Para cancelar su cita puede responder este correo indicando su nombre completo y la fecha/hora de la cita. Nosotros la procesaremos.', '2026-06-12 10:00:00', 'Admin'),
('Carmen Mendoza Padilla', 'carmen.mp@hotmail.com', '¿Realizan exámenes de sangre en la misma clínica o tengo que ir a un laboratorio externo?', '2026-06-13 08:15:00', TRUE, FALSE, NULL, NULL, NULL),
('Humberto Tello Carpio', 'humberto.tello@gmail.com', 'Quiero agradecer al Dr. Salazar por la atención que le dio a mi hijo. Salió muy contento de su consulta.', '2026-06-14 17:50:00', TRUE, TRUE, 'Qué alegría leer esto Humberto. El Dr. Salazar es excelente con los niños. Transmitiremos su agradecimiento. Saludos a su hijo.', '2026-06-14 18:30:00', 'Admin'),
('Patricia Sialer Pisfil', 'patricia.sialer@gmail.com', 'Buenas tardes, quisiera información sobre el programa de atención para adultos mayores. ¿Tienen descuentos?', '2026-06-15 14:00:00', TRUE, FALSE, NULL, NULL, NULL),
('Raúl Ramírez López', 'raul.ramirez@outlook.com', 'Hace una semana me atendí con la Dra. Méndez y necesito una copia de mi receta porque la perdí.', '2026-06-16 10:00:00', TRUE, TRUE, 'Hola Raúl. Puede acercarse a recepción en nuestras instalaciones y solicitaremos una copia de su receta. Necesitará su DNI para identificarse. Atenderemos su solicitud a la brevedad.', '2026-06-16 11:30:00', 'Recepcionista'),
('Silvia Vílchez Guerrero', 'silvia.vilchez@gmail.com', '¿Cuál es el horario de atención los sábados?', '2026-06-17 09:45:00', TRUE, TRUE, 'Hola Silvia. Atendemos los sábados de 8:00 AM a 8:00 PM igual que entre semana. Solo los domingos no tenemos atención regular, solo emergencias. Gracias.', '2026-06-17 10:15:00', 'Admin'),
('Alberto Chávez Montenegro', 'alberto.chavez@gmail.com', 'Recibí un correo de confirmación de cita pero no reconozco haberla agendado. Favor de cancelarla.', '2026-06-18 11:20:00', TRUE, FALSE, NULL, NULL, NULL),
('Gloria Sánchez de Díaz', 'gloria.sanchez@yahoo.com', 'Mi cita es mañana pero tengo fiebre. ¿Debo ir igual o la reprogramo?', '2026-06-19 07:30:00', TRUE, TRUE, 'Hola Gloria. Le recomendamos no asistir si tiene fiebre para evitar contagios. Puede llamarnos para reprogramar sin costo. Cuídese mucho.', '2026-06-19 08:00:00', 'Admin'),
('Óscar Ponce Torres', 'oscar.ponce@gmail.com', '¿Tienen servicio de teleconsulta o todas las citas son presenciales?', '2026-06-20 16:45:00', TRUE, FALSE, NULL, NULL, NULL),
('Liliana Arriola Zevallos', 'liliana.arriola@hotmail.com', 'Quisiera saber si el Dr. Gamarra atiende también en la tarde o solo en la mañana.', '2026-06-21 12:30:00', TRUE, TRUE, 'Hola Liliana. El Dr. Gamarra atiende en horario de mañana de 8:00 AM a 1:00 PM y también los lunes y jueves en la tarde de 3:00 PM a 6:00 PM. Puede ver sus horarios disponibles al agendar su cita.', '2026-06-21 13:15:00', 'Admin'),
('Javier Montero Silva', 'javier.montero@gmail.com', 'Compré unos medicamentos recomendados por el Dr. Campos pero me están causando malestar estomacal. ¿Debo suspenderlos?', '2026-06-22 10:00:00', TRUE, TRUE, 'Hola Javier. Le recomendamos no suspender el tratamiento sin consultar al médico. Puede llamar a la clínica para dejarle un mensaje al Dr. Campos y que él le indique qué hacer. Esperamos que mejore.', '2026-06-22 11:00:00', 'Recepcionista'),
('Ruth Córdova Ramos', 'ruth.cordova@gmail.com', 'La página web es muy clara y fácil de usar. Me encantó el diseño. Felicitaciones.', '2026-06-23 14:00:00', TRUE, TRUE, 'Muchas gracias Ruth. Nos alegra que le guste nuestro sitio web. Trabajamos constantemente para mejorar la experiencia de nuestros pacientes. Saludos.', '2026-06-23 15:00:00', 'Admin'),
('David Rentería Vílchez', 'david.renteria@gmail.com', '¿Cuánto tiempo de anticipación debo llegar antes de mi cita?', '2026-06-24 08:00:00', TRUE, TRUE, 'Hola David. Le recomendamos llegar 15 minutos antes de su cita para realizar el registro en recepción y estar listo para su atención. Gracias.', '2026-06-24 08:30:00', 'Recepcionista'),
('Sandra Cubas Purizaca', 'sandra.cubas@yahoo.com', 'Hola, quiero saber si el Dr. Aliaga atiende a niños o solo adultos.', '2026-06-25 10:30:00', FALSE, FALSE, NULL, NULL, NULL),
('Manuel Vásquez Torres', 'manuel.vasquez@gmail.com', 'Excelente atención en general. La clínica está muy bien equipada y el personal es muy amable.', '2026-06-26 13:00:00', FALSE, FALSE, NULL, NULL, NULL),
('Brenda Rojas Sánchez', 'brenda.rojas@hotmail.com', 'Tuve un problema con el pago de mi cita. Me cobraron de más. ¿Pueden revisar mi caso?', '2026-06-27 09:15:00', FALSE, FALSE, NULL, NULL, NULL),
('Aldo Seminario Cruz', 'aldo.seminario@gmail.com', '¿Puede un familiar recoger mis resultados de exámenes o solo yo como paciente?', '2026-06-28 11:40:00', FALSE, FALSE, NULL, NULL, NULL),
('Katherine Jiménez Rivas', 'kathy.jimenez@gmail.com', 'Me gustaría saber si tienen nutricionista. Quiero iniciar un plan de alimentación saludable.', '2026-06-29 15:20:00', FALSE, FALSE, NULL, NULL, NULL),
('Ronald Flores Baca', 'ronald.flores@outlook.com', 'Estoy muy agradecido con la Dra. Merino por su dedicación y profesionalismo. Es una excelente médica.', '2026-06-30 17:00:00', FALSE, FALSE, NULL, NULL, NULL);
