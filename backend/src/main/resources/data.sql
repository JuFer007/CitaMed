
-- ============================================================
-- 1. USUARIOS
-- ============================================================
INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('admin01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',         true);
SET @u_admin = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('marcelo.alarcon',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',         true);
SET @u_marcelo = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('cristian.huaman',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',         true);
SET @u_cristian = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('junior.zumaeta',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'ADMIN',         true);
SET @u_junior = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('recep01',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA', true);
SET @u_recep01 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('recep02',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA', true);
SET @u_recep02 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('recep03',            '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'RECEPCIONISTA', false);
SET @u_recep03 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.garcia',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_garcia = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.torres',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_torres = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.ramirez',   '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_ramirez = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.flores',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_flores = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.castro',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_castro = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.herrera',   '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_herrera = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.vega',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_vega = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.mora',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_mora = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.rivas',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_rivas = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.pinto',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_pinto = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.aguilar',   '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_aguilar = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('jc.quispe@gmail.com', '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac01 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('med.silva',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'MEDICO', true);
SET @u_med_silva = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('maria.soto@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac02 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('pedro.huaman@gmail.com',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac03 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('lucia.paredes@gmail.com',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac04 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('carlos.mendoza.p@gmail.com', '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac05 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('ana.rojas@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac06 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('roberto.vargas@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac07 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('isabel.chavez@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac08 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('diego.fuentes@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac09 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('valentina.espinoza@gmail.com',    '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac10 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('andres.llontop@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac11 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('gabriela.zuloeta@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac12 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('ricardo.gonzales@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac13 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('sofia.chafloque@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac14 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('martin.idrogo@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac15 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('patricia.coronado@gmail.com',     '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac16 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('eduardo.saavedra@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac17 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('claudia.segura@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac18 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('marco.becerra@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac19 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('natalia.cubas@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac20 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('cesar.fernandez@gmail.com',       '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac21 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('milagros.puelles@gmail.com',      '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac22 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('jhon.guerrero@gmail.com',         '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac23 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('fiorella.tello@gmail.com',        '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac24 = LAST_INSERT_ID();

INSERT INTO usuarios (user_name, password, rol, activo) VALUES
('alejandro.santisteban@gmail.com', '$2a$10$800I/8HGwCkjx0iApGb/kevpKOnZ1jp2UjTTWCg9CKaUNKdFhoywa', 'PACIENTE', true);
SET @u_pac25 = LAST_INSERT_ID();

-- ============================================================
-- 2. ESPECIALIDADES
-- ============================================================
INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Urología',                  'Diagnóstico y tratamiento del sistema urinario y reproductor masculino', 120.00);
SET @esp_urologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Reumatología',              'Tratamiento de enfermedades articulares, musculares y autoinmunes', 110.00);
SET @esp_reumatologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Medicina General',          'Atención médica primaria y consultas generales de salud', 80.00);
SET @esp_med_gral = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Cardiología',               'Diagnóstico y tratamiento de enfermedades cardiovasculares', 150.00);
SET @esp_cardiologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Pediatría',                 'Atención médica integral de niños desde el nacimiento hasta la adolescencia', 90.00);
SET @esp_pediatria = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Ginecología y Obstetricia', 'Salud reproductiva femenina, embarazo y parto', 130.00);
SET @esp_ginecologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Traumatología y Ortopedia', 'Lesiones y enfermedades del aparato locomotor', 120.00);
SET @esp_traumatologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Neurología',                'Enfermedades del sistema nervioso central y periférico', 140.00);
SET @esp_neurologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Dermatología',              'Enfermedades de la piel, cabello y uñas', 100.00);
SET @esp_dermatologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Endocrinología',            'Trastornos hormonales y metabólicos', 115.00);
SET @esp_endocrinologia = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Gastroenterología',         'Enfermedades del sistema digestivo', 125.00);
SET @esp_gastro = LAST_INSERT_ID();

INSERT INTO especialidades (nombre, descripcion, precio) VALUES ('Oftalmología',              'Enfermedades y cirugías del ojo', 135.00);
SET @esp_oftalmologia = LAST_INSERT_ID();

-- ============================================================
-- 3. CONSULTORIOS
-- ============================================================
INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-101', 'Consultorio de Medicina General N°1',  true, @esp_med_gral, 3);
SET @cons_med_gral_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-102', 'Consultorio de Medicina General N°2',  true,  @esp_med_gral, 3);
SET @cons_med_gral_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-103', 'Consultorio de Medicina General N°3',  false, @esp_med_gral, 3);
SET @cons_med_gral_3 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-201', 'Consultorio de Cardiología N°1',        true,  @esp_cardiologia, 3);
SET @cons_cardio_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-202', 'Consultorio de Cardiología N°2',        true,  @esp_cardiologia, 3);
SET @cons_cardio_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-301', 'Consultorio de Pediatría N°1',          true,  @esp_pediatria, 3);
SET @cons_pediatria_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-302', 'Consultorio de Pediatría N°2',          false, @esp_pediatria, 3);
SET @cons_pediatria_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-401', 'Consultorio de Ginecología N°1',        true,  @esp_ginecologia, 3);
SET @cons_gineco_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-402', 'Consultorio de Ginecología N°2',        true,  @esp_ginecologia, 3);
SET @cons_gineco_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-501', 'Consultorio de Traumatología N°1',      true,  @esp_traumatologia, 3);
SET @cons_traumato_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-601', 'Consultorio de Neurología N°1',         true,  @esp_neurologia, 3);
SET @cons_neuro_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-602', 'Consultorio de Neurología N°2',         true,  @esp_neurologia, 3);
SET @cons_neuro_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-701', 'Consultorio de Urología N°1',           true,  @esp_urologia, 3);
SET @cons_urologia_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-702', 'Consultorio de Urología N°2',           true,  @esp_urologia, 3);
SET @cons_urologia_2 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-801', 'Consultorio de Reumatología N°1',       true,  @esp_reumatologia, 3);
SET @cons_reuma_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-901', 'Consultorio de Dermatología N°1',       true,  @esp_dermatologia, 3);
SET @cons_derma_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-1001','Consultorio de Endocrinología N°1',     true,  @esp_endocrinologia, 3);
SET @cons_endocrino_1 = LAST_INSERT_ID();

INSERT INTO consultorios (numero, descripcion, disponible, especialidad_id, cupo_maximo) VALUES
('C-1101','Consultorio de Gastroenterología N°1',  true,  @esp_gastro, 3);
SET @cons_gastro_1 = LAST_INSERT_ID();

-- ============================================================
-- 4. EMPLEADOS
-- ============================================================
INSERT INTO empleados (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, salario, fecha_ingreso, activo, usuario_id) VALUES
('MARCELO ARIEL',  'ALARCON',   'MANAY',    '74100001', '987000001', 'Av. Los Desarrolladores 101, Chiclayo', 'marcelo.alarcon@citamed.com',  '1999-05-15', 'MASCULINO', 4500.00, '2024-01-01', true,  @u_marcelo),
('CRISTIAN',       'HUAMAN',    'CRUZ',     '74100002', '987000002', 'Jr. Full Stack 202, Chiclayo',          'cristian.huaman@citamed.com',  '1999-08-22', 'MASCULINO', 4500.00, '2024-01-01', true,  @u_cristian),
('JUNIOR FERNANDO','ZUMAETA',   'GOLAC',    '74100003', '987000003', 'Calle Backend 303, Chiclayo',           'junior.zumaeta@citamed.com',   '1999-11-10', 'MASCULINO', 4500.00, '2024-01-01', true,  @u_junior),
('CARLOS',         'MENDOZA',   'QUISPE',   '45678901', '987654321', 'Av. Los Olivos 123, Chiclayo',          'carlos.mendoza@citamed.com',   '1985-03-15', 'MASCULINO', 4200.00, '2018-01-10', true,  @u_admin),
('MARIA',          'LOPEZ',     'SANCHEZ',  '45678902', '987654322', 'Jr. Los Pinos 456, Chiclayo',           'maria.lopez@citamed.com',      '1990-07-22', 'FEMENINO',  1800.00, '2020-03-01', true,  @u_recep01),
('ANA',            'PEREZ',     'CASTILLO', '45678903', '987654323', 'Calle Manco Capac 789, Chiclayo',       'ana.perez@citamed.com',        '1992-11-05', 'FEMENINO',  1800.00, '2021-06-15', true,  @u_recep02),
('LUCIA',          'MEJIA',     'SOTO',     '45678904', '987654324', 'Jr. Progreso 890, Chiclayo',            'lucia.mejia@citamed.com',      '1994-02-19', 'FEMENINO',  1800.00, '2023-09-01', false, @u_recep03);

-- ============================================================
-- 5. MEDICOS
-- ============================================================
INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('LUIS',      'GARCIA',   'MONTOYA',  '12345601', '912345601', 'Av. Pardo 101, Chiclayo',            'luis.garcia@citamed.com',    '1978-05-12', 'MASCULINO', 'CMP-045231', true, '/uploads/medicos/medLuisGarcia.jpg',  @esp_urologia,         @u_med_garcia,  @cons_urologia_1);
SET @med_garcia_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('ROSA',      'TORRES',   'GUTIERREZ','12345602', '912345602', 'Jr. Colon 202, Chiclayo',            'rosa.torres@citamed.com',    '1980-09-18', 'FEMENINO',  'CMP-038742', true, '/uploads/medicos/medRosaGutierrez.jpg',  @esp_reumatologia,      @u_med_torres,  @cons_reuma_1);
SET @med_torres_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('MIGUEL',    'RAMIREZ',  'DELGADO',  '12345603', '912345603', 'Calle Real 303, Chiclayo',           'miguel.ramirez@citamed.com', '1975-03-27', 'MASCULINO', 'CMP-029183', true, '/uploads/medicos/medMiguelRamirez.jpg',  @esp_med_gral,          @u_med_ramirez, @cons_med_gral_1);
SET @med_ramirez_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('CARMEN',    'FLORES',   'ESPINOZA', '12345604', '912345604', 'Av. Venezuela 404, Chiclayo',        'carmen.flores@citamed.com',  '1982-07-08', 'FEMENINO',  'CMP-051647', true, '/uploads/medicos/medCarmenFlores.jpg',  @esp_cardiologia,       @u_med_flores,  @cons_cardio_1);
SET @med_flores_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('JORGE',     'CASTRO',   'HUAMAN',   '12345605', '912345605', 'Jr. Tacna 505, Chiclayo',            'jorge.castro@citamed.com',   '1977-11-14', 'MASCULINO', 'CMP-034829', true, '/uploads/medicos/medJorgeCastro.jpg',  @esp_pediatria,         @u_med_castro,  @cons_pediatria_1);
SET @med_castro_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('PATRICIA',  'HERRERA',  'LEON',     '12345606', '912345606', 'Av. Balta 606, Chiclayo',            'patricia.herrera@citamed.com','1983-01-30', 'FEMENINO',  'CMP-062318', true, '/uploads/medicos/medPatriciaHerrera.jpg',  @esp_ginecologia,       @u_med_herrera, @cons_gineco_1);
SET @med_herrera_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('FERNANDO',  'VEGA',     'QUISPE',   '12345607', '912345607', 'Calle Elias Aguirre 707, Chiclayo',  'fernando.vega@citamed.com',  '1979-06-22', 'MASCULINO', 'CMP-041095', true, '/uploads/medicos/medFernandoVega.jpg',  @esp_traumatologia,     @u_med_vega,    @cons_traumato_1);
SET @med_vega_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('SANDRA',    'MORA',     'PAREDES',  '12345608', '912345608', 'Jr. Torres Paz 808, Chiclayo',       'sandra.mora@citamed.com',    '1981-10-03', 'FEMENINO',  'CMP-057382', true, '/uploads/medicos/medSandraMora.jpg',  @esp_neurologia,        @u_med_mora,    @cons_neuro_1);
SET @med_mora_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('HECTOR',    'RIVAS',    'CARDENAS', '12345609', '912345609', 'Av. Jose Balta 909, Chiclayo',       'hector.rivas@citamed.com',   '1976-04-17', 'MASCULINO', 'CMP-027461', true, '/uploads/medicos/medHectorRivas.jpg',  @esp_dermatologia,      @u_med_rivas,   @cons_derma_1);
SET @med_rivas_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('GABRIELA',  'PINTO',    'SALINAS',  '12345610', '912345610', 'Calle Siete de Enero 1010, Chiclayo','gabriela.pinto@citamed.com', '1984-08-25', 'FEMENINO',  'CMP-068754', true, '/uploads/medicos/medGabrielaPinto.jpg',  @esp_urologia,          @u_med_pinto,   @cons_urologia_2);
SET @med_pinto_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('SILVIA',    'AGUILAR',  'REYES',    '12345611', '912345611', 'Av. Grau 1111, Chiclayo',            'silvia.aguilar@citamed.com', '1980-12-01', 'FEMENINO',  'CMP-071235', true, '/uploads/medicos/medSilviaAguilar.jpg',  @esp_endocrinologia,    @u_med_aguilar, @cons_endocrino_1);
SET @med_aguilar_id = LAST_INSERT_ID();

INSERT INTO medicos (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, numero_colegiatura, activo, foto_url, especialidad_id, usuario_id, consultorio_id) VALUES
('RENZO',     'SILVA',    'CORDOVA',  '12345612', '912345612', 'Jr. Piura 1212, Chiclayo',           'renzo.silva@citamed.com',    '1979-03-09', 'MASCULINO', 'CMP-071899', true, '/uploads/medicos/medRenzoSilva.jpg',    @esp_gastro,            @u_med_silva,   @cons_gastro_1);
SET @med_silva_id = LAST_INSERT_ID();

-- ============================================================
-- 6. HORARIOS MEDICOS
-- ============================================================
INSERT INTO horarios_medicos (dia, hora_inicio, hora_fin, activo, medico_id, consultorio_id) VALUES
('LUNES',     '08:00:00', '13:00:00', true, @med_garcia_id,  @cons_urologia_1),
('MIERCOLES', '08:00:00', '13:00:00', true, @med_garcia_id,  @cons_urologia_1),
('VIERNES',   '08:00:00', '12:00:00', true, @med_garcia_id,  @cons_urologia_1),
('MARTES',    '09:00:00', '14:00:00', true, @med_torres_id,  @cons_reuma_1),
('JUEVES',    '09:00:00', '14:00:00', true, @med_torres_id,  @cons_reuma_1),
-- ('SABADO',    '08:00:00', '12:00:00', true, @med_torres_id,  @cons_reuma_1),
('LUNES',     '14:00:00', '19:00:00', true, @med_ramirez_id, @cons_med_gral_1),
('MIERCOLES', '14:00:00', '19:00:00', true, @med_ramirez_id, @cons_med_gral_1),
('VIERNES',   '14:00:00', '19:00:00', true, @med_ramirez_id, @cons_med_gral_1),
('MARTES',    '08:00:00', '13:00:00', true, @med_flores_id,  @cons_cardio_1),
('JUEVES',    '08:00:00', '13:00:00', true, @med_flores_id,  @cons_cardio_1),
-- ('SABADO',    '09:00:00', '12:00:00', true, @med_flores_id,  @cons_cardio_1),
('LUNES',     '08:00:00', '14:00:00', true, @med_castro_id,  @cons_pediatria_1),
('MIERCOLES', '08:00:00', '14:00:00', true, @med_castro_id,  @cons_pediatria_1),
('MARTES',    '15:00:00', '20:00:00', true, @med_herrera_id, @cons_gineco_1),
('JUEVES',    '15:00:00', '20:00:00', true, @med_herrera_id, @cons_gineco_1),
('VIERNES',   '15:00:00', '20:00:00', true, @med_herrera_id, @cons_gineco_1),
('LUNES',     '09:00:00', '14:00:00', true, @med_vega_id,    @cons_traumato_1),
('MIERCOLES', '09:00:00', '14:00:00', true, @med_mora_id,    @cons_neuro_1),
('VIERNES',   '09:00:00', '13:00:00', true, @med_rivas_id,   @cons_derma_1),
('LUNES',     '08:00:00', '13:00:00', true, @med_pinto_id,   @cons_urologia_2),
('MIERCOLES', '08:00:00', '13:00:00', true, @med_pinto_id,   @cons_urologia_2),
('VIERNES',   '08:00:00', '13:00:00', true, @med_pinto_id,   @cons_urologia_2),
('MARTES',    '08:00:00', '13:00:00', true, @med_aguilar_id, @cons_endocrino_1),
('JUEVES',    '08:00:00', '13:00:00', true, @med_aguilar_id, @cons_endocrino_1),
('LUNES',     '15:00:00', '19:00:00', true, @med_silva_id,   @cons_gastro_1),
('MIERCOLES', '15:00:00', '19:00:00', true, @med_silva_id,   @cons_gastro_1);

-- ============================================================
-- 7. PACIENTES
-- ============================================================
INSERT INTO pacientes (nombre, apellido_paterno, apellido_materno, dni, telefono, direccion, email, fecha_nacimiento, genero, grupo_sanguineo, activo, usuario_id) VALUES
('JUAN CARLOS',      'QUISPE',      'MAMANI',     '71234501', '951234501', 'Av. Agricultura 101, Chiclayo',      'jc.quispe@gmail.com',              '1990-02-14', 'MASCULINO', 'O_POSITIVO', TRUE, @u_pac01),
('MARIA ELENA',      'SOTO',        'VARGAS',     '71234502', '951234502', 'Jr. Los Alamos 202, Chiclayo',       'maria.soto@gmail.com',             '1985-06-23', 'FEMENINO',  'A_POSITIVO', TRUE, @u_pac02),
('PEDRO PABLO',      'HUAMAN',      'QUISPE',     '71234503', '951234503', 'Calle Los Fresnos 303, Chiclayo',    'pedro.huaman@gmail.com',           '1978-11-05', 'MASCULINO', 'B_POSITIVO', TRUE, @u_pac03),
('LUCIA ROSA',       'PAREDES',     'TORRES',     '71234504', '951234504', 'Av. Fitzcarrald 404, Chiclayo',      'lucia.paredes@gmail.com',          '1995-03-18', 'FEMENINO',  'AB_POSITIVO', TRUE, @u_pac04),
('CARLOS ALBERTO',   'MENDOZA',     'LOPEZ',      '71234505', '951234505', 'Jr. San Martin 505, Chiclayo',       'carlos.mendoza.p@gmail.com',       '1988-07-30', 'MASCULINO', 'O_NEGATIVO', TRUE, @u_pac05),
('ANA SOFIA',        'ROJAS',       'CASTRO',     '71234506', '951234506', 'Calle Dos de Mayo 606, Chiclayo',    'ana.rojas@gmail.com',              '1992-09-12', 'FEMENINO',  'A_NEGATIVO', TRUE, @u_pac06),
('ROBERTO JESUS',    'VARGAS',      'DIAZ',       '71234507', '951234507', 'Av. Saenz Pena 707, Chiclayo',       'roberto.vargas@gmail.com',         '1975-01-08', 'MASCULINO', 'B_NEGATIVO', TRUE, @u_pac07),
('ISABEL CRISTINA',  'CHAVEZ',      'MORALES',    '71234508', '951234508', 'Jr. Ladislao Espinar 808, Chiclayo', 'isabel.chavez@gmail.com',          '1983-05-27', 'FEMENINO',  'O_POSITIVO', TRUE, @u_pac08),
('DIEGO ALEJANDRO',  'FUENTES',     'HERRERA',    '71234509', '951234509', 'Calle Pedro Ruiz 909, Chiclayo',     'diego.fuentes@gmail.com',          '1998-12-03', 'MASCULINO', 'A_POSITIVO', TRUE, @u_pac09),
('VALENTINA',        'ESPINOZA',    'NUNEZ',      '71234510', '951234510', 'Av. Leguia 1010, Chiclayo',          'valentina.espinoza@gmail.com',     '2001-04-16', 'FEMENINO',  'AB_NEGATIVO', TRUE, @u_pac10),
('ANDRES FELIPE',    'LLONTOP',     'BANCES',     '71234511', '951234511', 'Jr. Colon 111, Chiclayo',            'andres.llontop@gmail.com',         '1970-08-21', 'MASCULINO', 'O_POSITIVO', TRUE, @u_pac11),
('GABRIELA NOEMI',   'ZULOETA',     'CARRASCO',   '71234512', '951234512', 'Av. Pedro Ruiz 222, Chiclayo',       'gabriela.zuloeta@gmail.com',       '1987-10-09', 'FEMENINO',  'B_POSITIVO', TRUE, @u_pac12),
('RICARDO MANUEL',   'GONZALES',    'MURO',       '71234513', '951234513', 'Calle Izaga 333, Chiclayo',          'ricardo.gonzales@gmail.com',       '1993-02-28', 'MASCULINO', 'A_POSITIVO', TRUE, @u_pac13),
('SOFIA VALENTINA',  'CHAFLOQUE',   'SOLIS',      '71234514', '951234514', 'Jr. 8 de Octubre 444, Chiclayo',     'sofia.chafloque@gmail.com',        '2000-06-14', 'FEMENINO',  'O_POSITIVO', TRUE, @u_pac14),
('MARTIN EDUARDO',   'IDROGO',      'BUSTAMANTE', '71234515', '951234515', 'Av. Los Incas 555, Chiclayo',        'martin.idrogo@gmail.com',          '1965-11-30', 'MASCULINO', 'A_NEGATIVO', TRUE, @u_pac15),
('PATRICIA LUZ',     'CORONADO',    'DIAZ',       '71234516', '951234516', 'Calle Real 666, Chiclayo',           'patricia.coronado@gmail.com',      '1980-03-07', 'FEMENINO',  'B_POSITIVO', TRUE, @u_pac16),
('EDUARDO JESUS',    'SAAVEDRA',    'NEYRA',      '71234517', '951234517', 'Jr. San Jose 777, Chiclayo',         'eduardo.saavedra@gmail.com',       '1972-07-19', 'MASCULINO', 'O_POSITIVO', TRUE, @u_pac17),
('CLAUDIA BEATRIZ',  'SEGURA',      'MONTOYA',    '71234518', '951234518', 'Av. Chiclayo 888, Chiclayo',         'claudia.segura@gmail.com',         '1996-09-02', 'FEMENINO',  'AB_POSITIVO', TRUE, @u_pac18),
('MARCO ANTONIO',    'BECERRA',     'VILLANUEVA', '71234519', '951234519', 'Calle Los Pinos 999, Chiclayo',      'marco.becerra@gmail.com',          '1982-01-25', 'MASCULINO', 'A_POSITIVO', TRUE, @u_pac19),
('NATALIA ANDREA',   'CUBAS',       'MEJIA',      '71234520', '951234520', 'Jr. Victor Raul 1100, Chiclayo',     'natalia.cubas@gmail.com',          '1991-05-11', 'FEMENINO',  'O_NEGATIVO', TRUE, @u_pac20),
('CESAR AUGUSTO',    'FERNANDEZ',   'PAZ',        '71234521', '951234521', 'Av. Chinchaysuyo 200, Chiclayo',     'cesar.fernandez@gmail.com',        '1969-03-15', 'MASCULINO', 'B_POSITIVO', TRUE, @u_pac21),
('MILAGROS',         'PUELLES',     'ASALDE',     '71234522', '951234522', 'Jr. Los Jazmines 300, Chiclayo',     'milagros.puelles@gmail.com',       '1976-08-29', 'FEMENINO',  'O_POSITIVO', TRUE, @u_pac22),
('JHON ALEXANDER',   'GUERRERO',    'RIVADENEYRA','71234523', '951234523', 'Calle Manco Capac 400, Chiclayo',    'jhon.guerrero@gmail.com',          '1999-12-17', 'MASCULINO', 'A_POSITIVO', TRUE, @u_pac23),
('FIORELLA MILAGROS','TELLO',       'PERALES',    '71234524', '951234524', 'Av. Venezuela 500, Chiclayo',        'fiorella.tello@gmail.com',         '2003-04-05', 'FEMENINO',  'AB_POSITIVO', TRUE, @u_pac24),
('ALEJANDRO LUIS',   'SANTISTEBAN', 'CABREJOS',   '71234525', '951234525', 'Jr. Elias Aguirre 600, Chiclayo',    'alejandro.santisteban@gmail.com',  '1960-06-20', 'MASCULINO', 'O_POSITIVO', TRUE, @u_pac25);

-- ============================================================
-- 8. HISTORIALES MEDICOS
-- ============================================================
INSERT INTO historiales_medicos (paciente_id) SELECT id FROM pacientes;

-- ============================================================
-- 9. CITAS
-- ============================================================
INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234501'), @med_mora_id,    @cons_neuro_1,      '2026-05-06 09:00:00', 'Dolor de cabeza persistente y mareos',      'ATENDIDA');
SET @cita1 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234502'), @med_flores_id,  @cons_cardio_1,     '2026-05-05 08:00:00', 'Revision de presion arterial alta',         'ATENDIDA');
SET @cita2 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234503'), @med_castro_id,  @cons_pediatria_1,  '2026-05-04 08:00:00', 'Control pediatrico de nino de 5 anos',       'ATENDIDA');
SET @cita3 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234504'), @med_herrera_id, @cons_gineco_1,     '2026-05-05 15:00:00', 'Consulta por irregularidades menstruales',   'ATENDIDA');
SET @cita4 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234505'), @med_vega_id,    @cons_traumato_1,   '2026-05-04 09:00:00', 'Dolor en rodilla derecha tras caida',        'ATENDIDA');
SET @cita5 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234506'), @med_mora_id,    @cons_neuro_1,      '2026-05-13 09:00:00', 'Episodios de migrana frecuentes',            'ATENDIDA');
SET @cita6 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234507'), @med_ramirez_id, @cons_med_gral_1,   '2026-05-04 14:00:00', 'Tos persistente y fiebre leve',              'ATENDIDA');
SET @cita7 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234508'), @med_flores_id,  @cons_cardio_1,     '2026-05-07 08:00:00', 'Palpitaciones y cansancio',                  'ATENDIDA');
SET @cita8 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234501'), @med_ramirez_id, @cons_med_gral_1,   '2026-05-06 14:00:00', 'Control medico trimestral',                  'ATENDIDA');
SET @cita9 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234509'), @med_rivas_id,   @cons_derma_1,      '2026-05-08 09:00:00', 'Dermatitis en brazos',                       'ATENDIDA');
SET @cita10 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234505'), @med_vega_id,    @cons_traumato_1,   '2026-05-11 09:00:00', 'Revision de fractura curada',                'ATENDIDA');
SET @cita11 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234515'), @med_aguilar_id, @cons_endocrino_1,  '2026-05-05 08:00:00', 'Hipotiroidismo, control mensual',            'ATENDIDA');
SET @cita12 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234510'), @med_aguilar_id, @cons_endocrino_1,  '2026-05-07 08:00:00', 'Fatiga extrema y aumento de peso',           'ATENDIDA');
SET @cita13 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234511'), @med_silva_id,   @cons_gastro_1,     '2026-05-04 15:00:00', 'Nauseas y dolor abdominal',                  'ATENDIDA');
SET @cita14 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234512'), @med_ramirez_id, @cons_med_gral_1,   '2026-05-08 14:00:00', 'Chequeo medico general',                     'ATENDIDA');
SET @cita15 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234513'), @med_castro_id,  @cons_pediatria_1,  '2026-05-06 08:00:00', 'Fiebre alta en nino de 8 anos',              'ATENDIDA');
SET @cita16 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234514'), @med_herrera_id, @cons_gineco_1,     '2026-05-07 15:00:00', 'Control de embarazo mes 5',                  'ATENDIDA');
SET @cita17 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234515'), @med_vega_id,    @cons_traumato_1,   '2026-05-18 09:00:00', 'Dolor lumbar cronico',                       'ATENDIDA');
SET @cita18 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234522'), @med_silva_id,   @cons_gastro_1,     '2026-05-06 15:00:00', 'Gastroenterologia, seguimiento',             'ATENDIDA');
SET @cita19 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234508'), @med_flores_id,  @cons_cardio_1,     '2026-05-09 09:00:00', 'Hipertension, ajuste de tratamiento',        'ATENDIDA');
SET @cita20 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234516'), @med_ramirez_id, @cons_med_gral_1,   '2026-05-11 14:00:00', 'Resfriado y dolor de garganta',              'ATENDIDA');
SET @cita21 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234517'), @med_mora_id,    @cons_neuro_1,      '2026-05-20 09:00:00', 'Entumecimiento en extremidades',             'ATENDIDA');
SET @cita22 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234518'), @med_flores_id,  @cons_cardio_1,     '2026-05-12 08:00:00', 'Control post-operatorio cardiaco',           'ATENDIDA');
SET @cita23 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234519'), @med_silva_id,   @cons_gastro_1,     '2026-05-11 15:00:00', 'Gastritis y acidez estomacal',               'ATENDIDA');
SET @cita24 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234520'), @med_herrera_id, @cons_gineco_1,     '2026-05-08 15:00:00', 'Revision de quiste ovarico',                 'ATENDIDA');
SET @cita25 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234521'), @med_castro_id,  @cons_pediatria_1,  '2026-05-11 08:00:00', 'Vacunacion y control de crecimiento',        'ATENDIDA');
SET @cita26 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234522'), @med_silva_id,   @cons_gastro_1,     '2026-05-13 15:00:00', 'Colon irritable, control mensual',           'ATENDIDA');
SET @cita27 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234523'), @med_vega_id,    @cons_traumato_1,   '2026-05-25 09:00:00', 'Esguince de tobillo izquierdo',              'ATENDIDA');
SET @cita28 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234524'), @med_rivas_id,   @cons_derma_1,      '2026-05-15 09:00:00', 'Acne y problemas en piel grasa',             'ATENDIDA');
SET @cita29 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234525'), @med_aguilar_id, @cons_endocrino_1,  '2026-05-12 08:00:00', 'Diabetes tipo 2, control trimestral',        'ATENDIDA');
SET @cita30 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234510'), @med_vega_id,    @cons_traumato_1,   '2026-06-01 09:00:00', 'Dolor de espalda y fatiga',                  'PROGRAMADA');
SET @cita31 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234506'), @med_mora_id,    @cons_neuro_1,      '2026-05-27 09:00:00', 'Vertigo y perdida de equilibrio',            'PROGRAMADA');
SET @cita32 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234501'), @med_flores_id,  @cons_cardio_1,     '2026-06-02 08:00:00', 'Presion alta y dolor de cabeza',             'PROGRAMADA');
SET @cita33 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234503'), @med_castro_id,  @cons_pediatria_1,  '2026-06-01 08:00:00', 'Control de asma infantil',                   'PROGRAMADA');
SET @cita34 = LAST_INSERT_ID();

INSERT INTO citas (paciente_id, medico_id, consultorio_id, fecha_hora, motivo_consulta, estado) VALUES
((SELECT id FROM pacientes WHERE dni = '71234502'), @med_flores_id,  @cons_cardio_1,     '2026-05-28 08:00:00', 'Arritmia, control semestral',                'PROGRAMADA');
SET @cita35 = LAST_INSERT_ID();

-- ============================================================
-- 10. PAGOS
-- ============================================================
INSERT INTO pagos (cita_id, monto, metodo_pago, estado, fecha_pago) VALUES
(@cita1,  140.00, 'EFECTIVO',      'PAGADO', '2026-05-06 09:05:00'),
(@cita2,  150.00, 'TARJETA',       'PAGADO', '2026-05-05 08:05:00'),
(@cita3,  90.00,  'EFECTIVO',      'PAGADO', '2026-05-04 08:05:00'),
(@cita4,  130.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-05 15:05:00'),
(@cita5,  120.00, 'EFECTIVO',      'PAGADO', '2026-05-04 09:05:00'),
(@cita6,  140.00, 'TARJETA',       'PAGADO', '2026-05-13 09:05:00'),
(@cita7,  80.00,  'EFECTIVO',      'PAGADO', '2026-05-04 14:05:00'),
(@cita8,  150.00, 'EFECTIVO',      'PAGADO', '2026-05-07 08:05:00'),
(@cita9,  80.00,  'TARJETA',       'PAGADO', '2026-05-06 14:05:00'),
(@cita10, 100.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-08 09:05:00'),
(@cita11, 120.00, 'EFECTIVO',      'PAGADO', '2026-05-11 09:05:00'),
(@cita12, 115.00, 'EFECTIVO',      'PAGADO', '2026-05-05 08:05:00'),
(@cita13, 115.00, 'TARJETA',       'PAGADO', '2026-05-07 08:05:00'),
(@cita14, 125.00, 'EFECTIVO',      'PAGADO', '2026-05-04 15:05:00'),
(@cita15, 80.00,  'TRANSFERENCIA', 'PAGADO', '2026-05-08 14:05:00'),
(@cita16, 90.00,  'EFECTIVO',      'PAGADO', '2026-05-06 08:05:00'),
(@cita17, 130.00, 'TARJETA',       'PAGADO', '2026-05-07 15:05:00'),
(@cita18, 120.00, 'TARJETA',       'PAGADO', '2026-05-18 09:05:00'),
(@cita19, 125.00, 'EFECTIVO',      'PAGADO', '2026-05-06 15:05:00'),
(@cita20, 150.00, 'EFECTIVO',      'PAGADO', '2026-05-09 09:05:00'),
(@cita21, 80.00,  'EFECTIVO',      'PAGADO', '2026-05-11 14:05:00'),
(@cita22, 140.00, 'TARJETA',       'PAGADO', '2026-05-20 09:05:00'),
(@cita23, 150.00, 'EFECTIVO',      'PAGADO', '2026-05-12 08:05:00'),
(@cita24, 125.00, 'TARJETA',       'PAGADO', '2026-05-11 15:05:00'),
(@cita25, 130.00, 'TRANSFERENCIA', 'PAGADO', '2026-05-08 15:05:00'),
(@cita26, 90.00,  'EFECTIVO',      'PAGADO', '2026-05-11 08:05:00'),
(@cita27, 125.00, 'TARJETA',       'PAGADO', '2026-05-13 15:05:00'),
(@cita28, 120.00, 'EFECTIVO',      'PAGADO', '2026-05-25 09:05:00'),
(@cita29, 100.00, 'EFECTIVO',      'PAGADO', '2026-05-15 09:05:00'),
(@cita30, 115.00, 'TARJETA',       'PAGADO', '2026-05-12 08:05:00'),
-- Citas PROGRAMADAS (31-35): pago pendiente, aun no se realiza el cobro
(@cita31, 120.00, NULL, 'PENDIENTE', NULL),
(@cita32, 140.00, NULL, 'PENDIENTE', NULL),
(@cita33, 150.00, NULL, 'PENDIENTE', NULL),
(@cita34, 90.00,  NULL, 'PENDIENTE', NULL),
(@cita35, 150.00, NULL, 'PENDIENTE', NULL);

-- ============================================================
-- 11. DIAGNOSTICOS
-- ============================================================
INSERT INTO diagnosticos (enfermedad, descripcion, receta, indicaciones, cita_id) VALUES
('CEFALEA TENSIONAL',                  'Dolor de cabeza de tipo tensional sin signos neurologicos focales',              'Ibuprofeno 400mg / Paracetamol 500mg',                                                                   'Reposo relativo, evitar pantallas, hidratacion. Tomar cada 8 horas con alimentos.',      @cita1),
('HIPERTENSION ARTERIAL',              'HTA grado 2, en control farmacologico con enalapril',                            'Enalapril 10mg cada 12h / Hidroclorotiazida 12.5mg',                                                     'Dieta hiposodica, ejercicio moderado diario. No suspender sin consultar.',                @cita2),
('DESARROLLO NORMAL',                  'Nino sano, desarrollo psicomotor adecuado para 5 anos',                         'Vitamina D 1000UI / Vitamina C 500mg',                                                                  'Dieta equilibrada, actividad fisica regular. Suplemento vitaminico.',                     @cita3),
('OLIGOMENORREA',                      'Ciclos menstruales irregulares, posible etiologia hormonal',                    'Anticonceptivo oral combinado 1 comprimido cada 24h / Acido folico 5mg',                                 'Tomar a la misma hora todos los dias. Control ecografico en 30 dias.',                   @cita4),
('CONTUSION DE RODILLA',               'Traumatismo directo en cara anterior de rodilla derecha sin fractura',          'Naproxeno 500mg cada 12h / Tramadol 50mg cada 8h segun dolor',                                           'Reposo, hielo local 20 min cada 4h. Puede producir somnolencia.',                        @cita5),
('MIGRANA CON AURA',                   'Migrana cronica episodica con fenomenos visuales previos',                      'Propranolol 40mg/dia / Sumatriptan 50mg en crisis',                                                      'No suspender bruscamente. Usar sumatriptan al inicio del dolor. Max 200mg/dia.',         @cita6),
('INFECCION RESPIRATORIA AGUDA',       'Rinofaringitis aguda de probable etiologia viral',                              'Amoxicilina 500mg cada 8h / Ibuprofeno 400mg cada 8h',                                                   'Terminar tratamiento completo. Solo ibuprofeno si fiebre >38°. Hidratacion.',            @cita7),
('TAQUICARDIA SINUSAL',                'Taquicardia sinusal por ansiedad, sin cardiopatia subyacente',                 'Atenolol 25mg/dia / Loratadina 10mg/dia',                                                               'Tomar con desayuno. Controlar frecuencia cardiaca. Reducir cafeina.',                    @cita8),
('CONTROL MEDICO SIN HALLAZGOS',       'Chequeo trimestral de rutina, examen fisico sin alteraciones',                 'Multivitaminico 1 tableta/dia',                                                                          'Continuar habitos saludables. Proximo control en 3 meses.',                              @cita9),
('DERMATITIS ATOPICA',                 'Dermatitis atopica leve en pliegues de codos y rodillas',                      'Betametasona crema 0.05% topico / Emoliente corporal',                                                   'Aplicar capa fina en zona afectada. Emoliente tras el bano. No en cara.',                @cita10),
('FRACTURA CONSOLIDADA',               'Fractura de radio distal en proceso de consolidacion completa, retiro de inmovilizacion', 'Calcio + Vitamina D 600mg/1000UI',                                                       'Iniciar fisioterapia de rehabilitacion. Evitar cargas pesadas por 2 semanas mas.',       @cita11),
('HIPOTIROIDISMO PRIMARIO',            'Hipotiroidismo diagnosticado por TSH elevada y T4 libre bajo',                 'Levotiroxina 50mcg en ayunas',                                                                           'Esperar 30 min antes de comer. No con calcio o hierro. Control TSH en 4 sem.',           @cita12),
('HIPOTIROIDISMO SUBCLINICO',          'TSH levemente elevada compatible con hipotiroidismo subclinico',               'Levotiroxina 25mcg en ayunas',                                                                           'Control de TSH en 6 semanas. Esperar 30 min antes de comer.',                            @cita13),
('GASTRITIS CRONICA',                  'Gastritis antral cronica no erosiva, H. pylori negativo',                      'Omeprazol 20mg antes del desayuno / Sucralfato 1g 3 veces al dia',                                       'Dieta blanda sin irritantes. Evitar AINES. Sucralfato 1h antes de comidas.',             @cita14),
('SIN PATOLOGIA AGUDA',                'Examen fisico y laboratorios dentro de rangos normales',                       '',                                                                                                        'Continuar habitos saludables. Actividad fisica 150 min/semana. Control anual.',          @cita15),
('AMIGDALITIS BACTERIANA',             'Amigdalitis pultacea compatible con Streptococcus pyogenes',                   'Amoxicilina 500mg cada 8h / Ibuprofeno pediatrico 200mg',                                                'Completar tratamiento 7 dias. Gargaras salinas. Calcular ibuprofeno por peso.',          @cita16),
('EMBARAZO NORMAL SEMANA 22',          'Gestacion de bajo riesgo con feto en adecuadas condiciones',                   'Acido folico 1mg/dia / Sulfato ferroso 300mg',                                                           'Indispensable en embarazo. Tomar con vitamina C para absorcion de hierro.',              @cita17),
('LUMBALGIA MECANICA',                 'Lumbalgia inespecifica de tipo mecanico sin irradiacion',                      'Ciclobenzaprina 5mg cada 8h / Diclofenaco 75mg cada 12h',                                                'Fisioterapia 3 veces/semana. Faja lumbar en actividad. Puede causar somnolencia.',       @cita18),
('COLITIS CRONICA EN CONTROL',         'Evolucion favorable de colitis, sin signos de actividad inflamatoria',         'Mesalazina 500mg cada 12h',                                                                              'Mantener dieta baja en residuos. Proximo control en 2 meses.',                            @cita19),
('HIPERTENSION ARTERIAL NO CONTROLADA','Cifras tensionales por encima de meta pese a tratamiento actual, se ajusta dosis','Losartan 50mg cada 12h / Amlodipino 5mg/dia',                                                            'Monitoreo de presion arterial diario. Dieta hiposodica estricta.',                        @cita20),
('FARINGOAMIGDALITIS VIRAL',           'Infeccion viral de vias respiratorias altas sin complicaciones',              'Paracetamol 1g cada 8h',                                                                                 'Reposo relativo 48h. No sobrepasar 3g al dia. Hidratacion abundante.',                   @cita21),
('NEUROPATIA PERIFERICA LEVE',         'Parestesias en manos sin deficit motor, origen a determinar',                 'Vitamina B1 100mg/dia / Vitamina B12 500mcg/dia',                                                        'Ejercicios de movilidad de manos. Tomar con el desayuno.',                               @cita22),
('POST-OPERATORIO BYPASS',             'Buena evolucion post bypass aortocoronario de doble vaso',                    'Aspirina 100mg/dia / Clopidogrel 75mg/dia / Warfarina 5mg/dia',                                          'Antiagregante + anticoagulante. Control INR semanal. Consultar antes de procedimientos.',@cita23),
('GASTRITIS POR AINES',                'Irritacion gastrica secundaria al uso cronico de ibuprofeno',                 'Omeprazol 20mg antes del desayuno',                                                                      'Suspender AINES. Dieta blanda. Evitar alcohol.',                                         @cita24),
('QUISTE OVARICO FUNCIONAL',           'Quiste folicular en ovario derecho de 3cm, sin tabiques',                     'Anticonceptivo oral 1 comprimido cada 24h',                                                              'Tomar a la misma hora. Ecografia de control en 60 dias.',                                @cita25),
('DESARROLLO NORMAL',                  'Crecimiento y desarrollo acordes a percentiles para la edad',                 'Sulfato ferroso 300mg/dia / Vitamina D 400UI/dia',                                                       'Vacuna triple viral aplicada. Suplemento para crecimiento.',                             @cita26),
('SINDROME DE INTESTINO IRRITABLE',    'SII con predominio diarreico, criterios de Roma IV',                          'Loperamida 2mg segun necesidad / Probiotico Lactobacillus 1 capsula/dia',                                 'Dieta baja en FODMAP. Evitar lactosa y gluten. Refrigerar probiotico.',                   @cita27),
('ESGUINCE DE TOBILLO GRADO II',       'Lesion ligamentosa parcial en tobillo izquierdo',                             'Ibuprofeno 400mg cada 8h / Diclofenaco gel 1% topico',                                                   'Inmovilizacion 10 dias. Hielo local. Aplicar gel con masaje suave.',                     @cita28),
('ACNE VULGAR GRADO 2',                'Acne inflamatorio moderado en region facial y dorsal',                        'Tretinona crema 0.025% nocturna / Doxiciclina 100mg cada 12h',                                           'Protector solar diario. Doxiciclina con abundante agua. No acostarse inmediatamente.',   @cita29),
('DIABETES MELLITUS TIPO 2',           'DM2 descompensada con HbA1c de 9.2%',                                        'Metformina 850mg cada 12h / Insulina Glargina 10 UI nocturna',                                           'Control glucemico diario. Dieta diabetica estricta. Rotar zonas de insulina.',           @cita30);

-- ============================================================
-- 12. CONSULTAS (formulario público de contacto)
-- ============================================================
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

-- ============================================================
-- 13. TESTIMONIOS
-- ============================================================
INSERT INTO testimonios (calificacion, mensaje, fecha_creacion, activo, paciente_id) VALUES
(5, 'La Dra. Mora me ayudó mucho con mis dolores de cabeza. Después de años con migrañas, por fin encontré un tratamiento que funciona. Muy profesional.', '2026-05-10 14:30:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234501')),
(5, 'La Dra. Flores es excelente cardióloga. Detectó mi hipertension a tiempo y el tratamiento ha dado muy buenos resultados. Totalmente recomendable.', '2026-05-12 10:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234502')),
(4, 'El Dr. Castro atendió muy bien a mi hijo. Le explicó todo con paciencia y el niño se sintió cómodo. Solo mejoraría los tiempos de espera.', '2026-05-14 16:45:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234503')),
(5, 'La Dra. Herrera es excepcional. Me acompañó durante mi consulta ginecológica con mucha profesionalismo y empatía. Las instalaciones son impecables.', '2026-05-15 09:20:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234504')),
(5, 'El Dr. Vega trató mi fractura de rodilla con excelencia. La recuperación fue rápida y el seguimiento postoperatorio muy detallado. Gran traumatólogo.', '2026-05-17 11:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234505')),
(4, 'Muy buena experiencia con la Dra. Mora. Logró controlar mis migrañas con aura que arrastraba hace años. El seguimiento postconsulta es muy bueno.', '2026-05-19 13:15:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234506')),
(5, 'El Dr. Ramírez es excelente médico general. Me atendió por una infección respiratoria y el tratamiento fue efectivo. Trato cercano y profesional.', '2026-05-20 08:30:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234507')),
(5, 'La Dra. Flores me ayudó a controlar mi presión arterial. Es muy detallista en las explicaciones y seguimiento. La recomiendo ampliamente.', '2026-05-22 14:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234508')),
(4, 'El Dr. Rivas trató mi dermatitis en brazos con mucha eficacia. En pocas semanas mejoró notablemente. Las instalaciones del consultorio son excelentes.', '2026-05-25 10:45:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234509')),
(5, 'La Dra. Aguilar es muy competente en endocrinología. Gracias a ella mi hipotiroidismo está perfectamente controlado. Muy recomendable.', '2026-05-27 16:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234510')),
(5, 'El Dr. Silva trató mi gastritis con un enfoque integral. No solo medicación sino también orientación nutricional. Excelente gastroenterólogo.', '2026-05-29 09:30:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234511')),
(4, 'El Dr. Ramírez me atendió mi chequeo general con mucha amabilidad. Explica todo con claridad y te da confianza. Muy buen médico.', '2026-06-01 11:15:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234512')),
(5, 'La Dra. Aguilar es la mejor endocrinóloga que he visitado. Su tratamiento para mi hipotiroidismo es impecable. El personal es muy amable y atento.', '2026-06-05 10:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234515')),
(5, 'La Dra. Herrera brindó una atención personalizada durante mi control ginecológico. Muy profesional y empática. 100% recomendable.', '2026-06-10 09:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234514')),
(4, 'Buena experiencia en cardiología. La Dra. Flores detectó a tiempo mi taquicardia y ajustó el tratamiento. El sistema de citas es muy práctico.', '2026-06-12 14:20:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234518')),
(5, 'La Dra. Mora diagnosticó correctamente mi neuropatía periférica después de años sin respuestas en otros centros. Muy agradecido.', '2026-06-14 11:30:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234517')),
(5, 'El Dr. Silva es excelente. Me ayudó con mi gastritis crónica con un plan de tratamiento efectivo. El trato es muy humano y profesional.', '2026-06-16 08:45:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234519')),
(5, 'La Dra. Herrera es muy atenta. Mi control de quiste ovárico fue tranquilo y bien explicado. Las instalaciones de ginecología son de primer nivel.', '2026-06-18 09:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234520')),
(4, 'El Dr. Rivas trató mi acne con mucha profesionalismo. El tratamiento ha dado excelentes resultados. Muy recomendable el servicio de dermatología.', '2026-06-20 14:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234524')),
(5, 'CitaMed superó mis expectativas. La Dra. Aguilar es excelente endocrinóloga y mi diabetes está bajo control. La atención es rápida y los doctores son muy capacitados.', '2026-06-22 16:00:00', TRUE, (SELECT id FROM pacientes WHERE dni = '71234525'));