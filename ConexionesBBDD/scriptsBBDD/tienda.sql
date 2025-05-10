-- Habilitar restricciones de claves foráneas (requerido en SQLite)
PRAGMA foreign_keys = ON;

-- Eliminar tablas si existen
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS fabricantes;

-- Crear tabla fabricante
CREATE TABLE fabricantes (
  codigo INTEGER PRIMARY KEY AUTOINCREMENT,   
  nombre TEXT NOT NULL UNIQUE
);

-- Crear tabla producto
CREATE TABLE productos (
  codigo INTEGER PRIMARY KEY AUTOINCREMENT, 
  nombre TEXT NOT NULL UNIQUE,
  precio REAL NOT NULL CHECK (precio > 0),  -- Usamos CHECK para validar precios > 0
  codigo_fabricante INTEGER NOT NULL,
  FOREIGN KEY (codigo_fabricante) REFERENCES fabricantes(codigo)
);

-- Insertar datos en fabricante (incluyendo 'codigo' explícitamente)
INSERT INTO fabricantes (codigo, nombre) VALUES 
(1, 'Asus'),
(2, 'Lenovo'),
(3, 'Hewlett-Packard'),
(4, 'Samsung'),
(5, 'Seagate'),
(6, 'Crucial'),
(7, 'Gigabyte'),
(8, 'Huawei'),
(9, 'Xiaomi');

-- Insertar datos en producto
INSERT INTO productos (codigo, nombre, precio, codigo_fabricante) VALUES 
(1, 'Disco duro SATA3 1TB', 86.99, 5),
(2, 'Memoria RAM DDR4 8GB', 120, 6),
(3, 'Disco SSD 1 TB', 150.99, 4),
(4, 'GeForce GTX 1050Ti', 185, 7),
(5, 'GeForce GTX 1080 Xtreme', 755, 6),
(6, 'Monitor 24 LED Full HD', 202, 1),
(7, 'Monitor 27 LED Full HD', 245.99, 1),
(8, 'Portátil Yoga 520', 559, 2),
(9, 'Portátil Ideapd 320', 444, 2),
(10, 'Impresora HP Deskjet 3720', 59.99, 3),
(11, 'Impresora HP Laserjet Pro M26nw', 180, 3);