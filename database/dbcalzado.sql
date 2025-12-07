-- =====================================================
-- SCRIPT SQL - SISTEMA DE VENTAS DE CALZADO
-- Base de datos: dbcalzado
-- MySQL 8.0+
-- Columnas EXACTAS según los DAOs del proyecto
-- =====================================================

DROP DATABASE IF EXISTS dbcalzado;
CREATE DATABASE dbcalzado CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE dbcalzado;

-- =====================================================
-- TABLAS MAESTRAS
-- =====================================================

-- Tabla: rol (RolDAO: id_rol, nombre)
CREATE TABLE rol (
    id_rol INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- Tabla: categoria (CategoriaDAO: id_categoria, nombre, descripcion, estado)
CREATE TABLE categoria (
    id_categoria INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado INT DEFAULT 1
) ENGINE=InnoDB;

-- Tabla: marca (MarcaDAO: id_marca, nombre)
CREATE TABLE marca (
    id_marca INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    estado INT DEFAULT 1
) ENGINE=InnoDB;

-- Tabla: talla (TallaDAO: id_talla, etiqueta, estado)
CREATE TABLE talla (
    id_talla INT PRIMARY KEY AUTO_INCREMENT,
    etiqueta VARCHAR(20) NOT NULL,
    estado INT DEFAULT 1
) ENGINE=InnoDB;

-- Tabla: cliente (ClienteDAO: id_cliente, nombres, apellidos, dni, direccion, telefono, estado)
CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    estado INT DEFAULT 1
) ENGINE=InnoDB;

-- Tabla: proveedor (ProveedorDAO: id_proveedor, razon_social, ruc, telefono, direccion, estado)
CREATE TABLE proveedor (
    id_proveedor INT PRIMARY KEY AUTO_INCREMENT,
    razon_social VARCHAR(150) NOT NULL,
    ruc VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    estado INT DEFAULT 1
) ENGINE=InnoDB;

-- =====================================================
-- TABLAS CON FK
-- =====================================================

-- Tabla: usuario (UsuarioDAO: id_usuario, nombre_usuario, contrasenia, id_rol, estado)
CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,
    estado INT DEFAULT 1,
    FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
) ENGINE=InnoDB;

-- Tabla: producto (ProductoDAO usa MEZCLA de nombres)
-- listar/insertar/actualizar/eliminar usan: idProducto, idCategoria (camelCase)
-- buscarPorId/obtenerPorId usan: id_producto, id_categoria (snake_case)
-- obtenerUltimoCodigo usa: codigo_barras como INT
-- SOLUCIÓN: Usar camelCase para principales + codigo_barras como INT
CREATE TABLE producto (
    idProducto INT PRIMARY KEY AUTO_INCREMENT,
    codigo_barras INT DEFAULT 0,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    precio_venta DECIMAL(10,2),
    stock INT DEFAULT 0,
    idCategoria INT NOT NULL,
    idMarca INT,
    idTalla INT,
    color VARCHAR(50),
    estado INT DEFAULT 1,
    -- Aliases para métodos que usan snake_case (MySQL no soporta, pero documentamos)
    FOREIGN KEY (idCategoria) REFERENCES categoria(id_categoria),
    FOREIGN KEY (idMarca) REFERENCES marca(id_marca),
    FOREIGN KEY (idTalla) REFERENCES talla(id_talla)
) ENGINE=InnoDB;

-- Tabla: venta (VentaDAO: id_venta, fecha, id_cliente, id_usuario, total, subtotal, igv)
CREATE TABLE venta (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_cliente INT NOT NULL,
    id_usuario INT NOT NULL,
    subtotal DECIMAL(10,2) DEFAULT 0,
    igv DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE=InnoDB;

-- Tabla: detalle_venta (DetalleVentaDAO: id_detalle, id_venta, id_producto, cantidad, precio_unitario, subtotal)
CREATE TABLE detalle_venta (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id_venta) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(idProducto)
) ENGINE=InnoDB;

-- Tabla: compra (CompraDAO: id_compra, fecha, id_proveedor, id_usuario, total)
CREATE TABLE compra (
    id_compra INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_proveedor INT NOT NULL,
    id_usuario INT NOT NULL,
    total DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
) ENGINE=InnoDB;

-- Tabla: detalle_compra (DetalleCompraDAO: id_detalle, id_compra, id_producto, cantidad, precio_unitario, subtotal)
CREATE TABLE detalle_compra (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    id_compra INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_compra) REFERENCES compra(id_compra) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(idProducto)
) ENGINE=InnoDB;

-- =====================================================
-- DATOS INICIALES
-- =====================================================

INSERT INTO rol (nombre) VALUES 
('Administrador'),
('Vendedor'),
('Almacenero');

INSERT INTO usuario (nombre_usuario, contrasenia, id_rol, estado) VALUES 
('admin', 'admin123', 1, 1),
('vendedor1', '123456', 2, 1);

INSERT INTO categoria (nombre, descripcion, estado) VALUES 
('Zapatillas', 'Calzado deportivo y casual', 1),
('Zapatos Formales', 'Calzado para ocasiones formales', 1),
('Sandalias', 'Calzado abierto para verano', 1),
('Botas', 'Calzado alto que cubre el tobillo', 1),
('Chanclas', 'Calzado ligero para playa o casa', 1);

INSERT INTO marca (nombre, estado) VALUES 
('Nike', 1),
('Adidas', 1),
('Puma', 1),
('Reebok', 1),
('New Balance', 1),
('Converse', 1),
('Vans', 1),
('Caterpillar', 1);

INSERT INTO talla (etiqueta, estado) VALUES 
('35', 1),
('36', 1),
('37', 1),
('38', 1),
('39', 1),
('40', 1),
('41', 1),
('42', 1),
('43', 1),
('44', 1),
('45', 1);

INSERT INTO cliente (nombres, apellidos, dni, direccion, telefono, estado) VALUES 
('Juan Carlos', 'Pérez López', '12345678', 'Av. Principal 123, Lima', '999888777', 1),
('María Elena', 'García Rodríguez', '87654321', 'Jr. Las Flores 456, Lima', '999777666', 1),
('Carlos Alberto', 'Mendoza Vargas', '11223344', 'Calle Los Pinos 789, Lima', '999666555', 1),
('Cliente', 'General', '00000000', 'Dirección General', '000000000', 1);

INSERT INTO proveedor (razon_social, ruc, telefono, direccion, estado) VALUES 
('Distribuidora Calzado Norte SAC', '20123456789', '014567890', 'Av. Industrial 100, Lima', 1),
('Importadora Shoes Perú EIRL', '20987654321', '015678901', 'Jr. Comercio 200, Lima', 1),
('Calzados del Sur SA', '20112233445', '016789012', 'Av. Grau 300, Arequipa', 1);

-- Productos con codigo_barras como INT (para que MAX() funcione)
INSERT INTO producto (codigo_barras, nombre, descripcion, precio, precio_venta, stock, idCategoria, idMarca, idTalla, color, estado) VALUES 
(1, 'Air Max 90', 'Zapatillas clásicas Nike Air Max 90', 350.00, 459.90, 25, 1, 1, 6, 'Blanco/Negro', 1),
(2, 'Ultraboost 22', 'Zapatillas running Adidas Ultraboost', 420.00, 549.90, 20, 1, 2, 7, 'Negro', 1),
(3, 'Suede Classic', 'Zapatillas casuales Puma Suede', 220.00, 299.90, 30, 1, 3, 5, 'Azul', 1),
(4, 'Classic Leather', 'Zapatillas retro Reebok', 200.00, 279.90, 15, 1, 4, 6, 'Blanco', 1),
(5, 'Chuck Taylor All Star', 'Zapatillas icónicas Converse', 180.00, 249.90, 40, 1, 6, 7, 'Negro', 1),
(6, 'Old Skool', 'Zapatillas skate Vans', 210.00, 289.90, 35, 1, 7, 6, 'Negro/Blanco', 1),
(7, 'Zapato Oxford Negro', 'Zapato formal clásico', 150.00, 199.90, 20, 2, NULL, 7, 'Negro', 1),
(8, 'Bota Colorado', 'Bota de trabajo Caterpillar', 300.00, 399.90, 10, 4, 8, 8, 'Miel', 1);

-- =====================================================
-- VERIFICACIÓN
-- =====================================================

SELECT '✅ Base de datos creada!' AS mensaje;
SHOW TABLES;
SELECT '👤 Usuarios: admin/admin123, vendedor1/123456' AS credenciales;
