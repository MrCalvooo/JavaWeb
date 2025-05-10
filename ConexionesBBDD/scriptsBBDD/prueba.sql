BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "departamentos" (
	"dptoNo"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"ubicacion"	TEXT,
	PRIMARY KEY("dptoNo" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "empleados" (
	"empNo"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"apellidos"	TEXT,
	"dptoNo"	INTEGER NOT NULL,
	"direccion"	TEXT,
	PRIMARY KEY("empNo" AUTOINCREMENT),
	FOREIGN KEY("dptoNo") REFERENCES "departamentos"("dptoNo")
);
INSERT INTO "departamentos" VALUES (1,'Ventas','Madrid');
INSERT INTO "departamentos" VALUES (2,'Compras','Bilbao');
INSERT INTO "departamentos" VALUES (3,'RRHH','Madrid');
INSERT INTO "empleados" VALUES (1,'Maria','Martinez Martos',1,'Alcalá de Henares');
INSERT INTO "empleados" VALUES (2,'Pedro','Pérez Pérez',2,'Getxo');
INSERT INTO "empleados" VALUES (3,'Marcos','Márquez Martínez',3,'Torrejón de Ardoz');
COMMIT;
