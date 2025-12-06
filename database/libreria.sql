CREATE DATABASE IF NOT EXISTS libreria DEFAULT CHARACTER SET utf8mb4;
USE libreria;

CREATE TABLE genero (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(60) NOT NULL
);

CREATE TABLE editorial (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80) NOT NULL
);

CREATE TABLE libro (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  autor  VARCHAR(120) NOT NULL,
  paginas INT NOT NULL,
  premios INT NOT NULL,
  genero_id INT NOT NULL,
  editorial_id INT NOT NULL,
  FOREIGN KEY (genero_id) REFERENCES genero(id),
  FOREIGN KEY (editorial_id) REFERENCES editorial(id)
);

INSERT INTO genero (nombre) VALUES ('Novela'), ('Ciencia'), ('Tecnología'), ('Historia');
INSERT INTO editorial (nombre) VALUES ('Alfa'), ('Beta'), ('Gamma');

INSERT INTO libro (titulo, autor, paginas, premios, genero_id, editorial_id) VALUES
('Patrones de Diseño', 'E. Gamma', 395, 0, 3, 3),
('Historia Breve del Tiempo', 'S. Hawking', 256, 1, 2, 1),
('La Ciudad y los Perros', 'M. Vargas Llosa', 512, 1, 1, 2),
('Algoritmos Modernos', 'J. Doe', 620, 0, 3, 1);
