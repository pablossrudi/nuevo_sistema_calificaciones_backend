-- Insert data into Alumno --
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('94596502-fa2b-4cf5-83eb-4b2d321898a4','12345678-9', 'Juan Perez', 'Calle 123');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('6d8cb608-2ba0-4bc1-a435-8aad316321f3','98765432-1', 'Maria Lopez', 'Avenida Siempre Viva');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('34ce8218-bab0-4857-b740-7a6815be8d44','12312312-3', 'Carlos Gonzalez', 'Calle Falsa 123');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('98b6a959-0058-49ca-b6b3-28f891b14f9b','11111111-1', 'Ana Torres', 'Boulevard Principal');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('ecdc8506-8701-4f33-b651-384ada4e6c52','22222222-2', 'Luis Martinez', 'Pasaje Secundario');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('e157cf3a-6894-45e8-866c-082b880f95dc','33333333-3', 'Sofia Ramirez', 'Plaza Central');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('e84801e1-5669-4df7-b44b-246570dbb5f6','44444444-4', 'Diego Herrera', 'Calle Luna');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('6922a795-11ad-4a57-975c-ad8458e04679','55555555-5', 'Laura Fernandez', 'Calle Sol');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('5c151812-347c-49b9-b956-612980965631','66666666-6', 'Pedro Castillo', 'Calle Mar');
INSERT INTO alumnos (alumno_id, alumno_rut, alumno_nombre, alumno_direccion) VALUES ('cb732e6c-f82e-41e8-a13e-aacd61c3864c','77777777-7', 'Lucia Vega', 'Avenida del Norte');

-- Insert data into Materia --
INSERT INTO materias (materia_id, materia_nombre) VALUES ('88000002-ab7c-4c1a-86f0-73635afa351b','Matematicas');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('7f025a6c-b689-47be-9d3f-ee3636d09ae0','Fisica');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('5bf949ea-a733-42e8-b5b0-74cb24d81fac','Quimica');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('f3127428-ce3f-4749-8f41-580bf6df5968','Historia');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('1bc8fa3b-ab2a-4f80-88bd-d50d5eb1ee97','Geografia');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('28fd8bde-a68c-497a-b6bc-5a611bfb1e14','Biologia');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('d5bfa198-c31f-4ff5-bbdc-b1a7585a9c2d','Ingles');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('d051a390-6e6c-4b57-83ec-77399d9256eb','Programacion');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('c322fb42-52e6-476d-b16d-4cfeefa3b5e5','Arte');
INSERT INTO materias (materia_id, materia_nombre) VALUES ('62b17952-ec28-4a9d-949d-1aa5fcaf7d03','Musica');

-- Insertar materias a alumnos
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('109e5b0e-94fa-4214-8f35-4f8af670b1cc','34ce8218-bab0-4857-b740-7a6815be8d44','88000002-ab7c-4c1a-86f0-73635afa351b', 4.4);
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('7689cb7a-da42-4b94-8eeb-08a888854e10','34ce8218-bab0-4857-b740-7a6815be8d44','88000002-ab7c-4c1a-86f0-73635afa351b', 5);
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('1f0df344-1ac0-4138-b5b3-c46477bd5bb5','34ce8218-bab0-4857-b740-7a6815be8d44','7f025a6c-b689-47be-9d3f-ee3636d09ae0', 5.5);
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('f1dd3600-9f63-4f30-9a63-4927b5be5367','34ce8218-bab0-4857-b740-7a6815be8d44','5bf949ea-a733-42e8-b5b0-74cb24d81fac', 7);
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('383ae4ea-7f1f-471f-bd14-3009ffe0db2a','6d8cb608-2ba0-4bc1-a435-8aad316321f3','88000002-ab7c-4c1a-86f0-73635afa351b', 6.3);
INSERT INTO alumnos_materias (alumno_materia_id, alumno_id, materia_id, nota) values ('a274976c-9bc1-40e3-9b21-b50decdc5a51','6d8cb608-2ba0-4bc1-a435-8aad316321f3','f3127428-ce3f-4749-8f41-580bf6df5968',5.8);


-- Insert roles
INSERT INTO roles (role_id, name) VALUES ('84f473e7-ba0c-4309-9aa4-801c65d5c1ba','ROLE_ADMIN');
INSERT INTO roles (role_id, name) VALUES ('b60e9cb2-c2a2-4496-b2b3-1f056b4e8cb8','ROLE_CLIENT');

-- Inserta el usuario administrador con su hash de contraseña (bcrypt)
INSERT INTO users (user_id, user_name, rut, password, email, is_active) VALUES ('7a4b44b7-99cc-412c-afdd-5367c9825b8d','admin', '26.931.652-7', '$2a$10$jMEiqDfyI0LfLcKhnUQTH./Hxac12x9HWKkAyJlhRyiscO9tN4dDC', 'admin@example.com', true);

-- Inserta el usuario cliente con su hash de contraseña (bcrypt)
INSERT INTO users (user_id, user_name, rut, password, email, is_active) VALUES ('d5db08f6-1501-426d-9a3f-0216f63ccca8','client', '12.321.344-1', '$2a$10$eyVQL9xnd09GIoDTekNmk.yqRyD0vv.jZEq1BjiX4I7XP4wl36lMa', 'client@example.com', true);

-- Asigna el rol ROLE_ADMIN al usuario admin
INSERT INTO user_roles (user_id, role_id) VALUES ('7a4b44b7-99cc-412c-afdd-5367c9825b8d', '84f473e7-ba0c-4309-9aa4-801c65d5c1ba');

-- Asigna el rol ROLE_CLIENT al usuario client
INSERT INTO user_roles (user_id, role_id) VALUES ('d5db08f6-1501-426d-9a3f-0216f63ccca8', 'b60e9cb2-c2a2-4496-b2b3-1f056b4e8cb8');