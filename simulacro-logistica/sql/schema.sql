DROP DATABASE IF EXISTS logistica;
CREATE DATABASE logistica CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE logistica;

-- -----------------------------------------------------
-- Tabla: vehiculo
-- -----------------------------------------------------
CREATE TABLE vehiculo (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(15) NOT NULL UNIQUE,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    capacidad_kg INT NOT NULL
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Tabla: conductor (N:1 con vehiculo)
-- -----------------------------------------------------
CREATE TABLE conductor (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(15) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    apellido1 VARCHAR(100) NOT NULL,
    apellido2 VARCHAR(100),
    telefono VARCHAR(20),
    vehiculo_id INT UNSIGNED,
    CONSTRAINT fk_conductor_vehiculo
        FOREIGN KEY (vehiculo_id)
        REFERENCES vehiculo(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Tabla: destino (N:1 con vehiculo)
-- -----------------------------------------------------
CREATE TABLE destino (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    vehiculo_id INT UNSIGNED,
    CONSTRAINT fk_destino_vehiculo
        FOREIGN KEY (vehiculo_id)
        REFERENCES vehiculo(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Tabla: parada (relaciona vehiculo con destino, única)
-- -----------------------------------------------------
CREATE TABLE parada (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    vehiculo_id INT UNSIGNED NOT NULL,
    destino_id INT UNSIGNED NOT NULL,
    fecha_prevista DATE NOT NULL,
    fecha_llegada DATE NULL,
    observaciones VARCHAR(255),
    CONSTRAINT fk_parada_vehiculo
        FOREIGN KEY (vehiculo_id)
        REFERENCES vehiculo(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_parada_destino
        FOREIGN KEY (destino_id)
        REFERENCES destino(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT uq_parada UNIQUE (vehiculo_id, destino_id)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Datos iniciales de ejemplo
-- -----------------------------------------------------
INSERT INTO vehiculo (matricula, marca, modelo, capacidad_kg) VALUES
('1234-ABC', 'Mercedes', 'Sprinter', 1500),
('5678-DEF', 'Iveco', 'Daily', 1200),
('9012-GHI', 'Ford', 'Transit', 1000);

INSERT INTO conductor (dni, nombre, apellido1, apellido2, telefono, vehiculo_id) VALUES
('11111111A', 'Carlos', 'Ruiz', 'López', '600111222', 1),
('22222222B', 'María', 'Gómez', 'Sánchez', '600333444', 1),
('33333333C', 'Javier', 'Martín', 'Pérez', '600555666', 2),
('44444444D', 'Lucía', 'Fernández', 'Díaz', '600777888', NULL);

INSERT INTO destino (nombre, direccion, ciudad, vehiculo_id) VALUES
('Almacén Central', 'C/ Industria 10', 'Madrid', 1),
('Tienda Norte', 'Av. Libertad 25', 'Bilbao', 1),
('Centro Comercial Sur', 'C/ Comercio 5', 'Sevilla', 2),
('Puerto Logístico', 'Muelle 3', 'Valencia', 2),
('Delegación Este', 'C/ Mayor 8', 'Barcelona', 3);

INSERT INTO parada (vehiculo_id, destino_id, fecha_prevista, fecha_llegada, observaciones) VALUES
(1, 1, '2026-05-10', '2026-05-10', 'Entrega completada'),
(1, 2, '2026-05-12', NULL, 'Pendiente de salida'),
(2, 3, '2026-05-11', '2026-05-11', NULL);
