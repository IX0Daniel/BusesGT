DROP DATABASE IF EXISTS BUS;
CREATE DATABASE BUS;
USE BUS;


----------
CREATE TABLE sucursal(
    codigo_sucursal INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) UNIQUE  

);


----------
CREATE TABLE bus(
    numero_placa VARCHAR(15) PRIMARY KEY,
    ruta_foto VARCHAR(80) NOT NULL,
    marca VARCHAR(80) NOT NULL,
    modelo VARCHAR(80) NOT NULL,
    año INT NOT NULL,
    capacidad INT DEFAULT 10,
    kilometraje DECIMAL(10, 2) DEFAULT 0.00,
    estado ENUM('activo','deshabilitado'),

    codigo_sucursal INT, 

    CONSTRAINT fk_bus_sucursal
        FOREIGN KEY (codigo_sucursal) 
        REFERENCES sucursal(codigo_sucursal),

    codigo_sucursal_actual INT NOT NULL, 

    CONSTRAINT fk_bus_sucursal_actual
        FOREIGN KEY (codigo_sucursal_actual) 
        REFERENCES sucursal(codigo_sucursal)

);

CREATE TABLE usuario(
    correo VARCHAR(20) UNIQUE PRIMARY KEY,
    contraseña VARCHAR(20) NOT NULL,
    rol ENUM('usuario', 'chofer', 'administrador-sucursal', 'administrador') NOT NULL,
    estado ENUM('activo', 'inhabilitado') NOT NULL
);

CREATE TABLE perfil_usuario(
    dpi VARCHAR(15) PRIMARY KEY NOT NULL,
    nit VARCHAR(15) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    nombre_completo VARCHAR(80) NOT NULL,
    saldo DECIMAL(10, 5) DEFAULT 0.00,
    correo VARCHAR(20) UNIQUE NOT NULL,
    CONSTRAINT perfil_usuario_usuario
        FOREIGN KEY (correo)
        REFERENCES usuario(correo)
);


CREATE TABLE administrador_sucursal(
    codigo_sucursal INT NOT NULL,
    correo VARCHAR(20) UNIQUE NOT NULL,

    CONSTRAINT perfil_usuario_usuario
        FOREIGN KEY (correo)
        REFERENCES usuario(correo),


    CONSTRAINT administrador_sucursal_sucursal
        FOREIGN KEY (codigo_sucursal) 
        REFERENCES sucursal(codigo_sucursal)
);

CREATE TABLE chofer(
    no_licencia VARCHAR(15) PRIMARY KEY,
    ruta_foto VARCHAR(80) NOT NULL,
    tipo_licencia ENUM('A', 'B', 'C', 'D'),
    fecha_vencimiento DATE NOT NULL,

    salario DECIMAL(10, 2) DEFAULT 0.00,
    codigo_sucursal INT NOT NULL,
    
    correo VARCHAR(20) UNIQUE NOT NULL,

    CONSTRAINT perfil_usuario_usuario
        FOREIGN KEY (correo)
        REFERENCES usuario(correo),

    CONSTRAINT fk_chofer_sucursal
        FOREIGN KEY(codigo_sucursal)
        REFERENCES sucursal(codigo_sucursal)
);



CREATE TABLE ruta(

    id_ruta INT AUTO_INCREMENT PRIMARY KEY,

    codigo_sucursal_origen INT,
    codigo_sucursal_destino INT,
    distancia DECIMAL(10, 2) DEFAULT 0.00,
    precio_boleto DECIMAL(10, 2) DEFAULT 0.00,
 
    CONSTRAINT fk_ruta_sucursal
        FOREIGN KEY (codigo_sucursal_origen) 
        REFERENCES sucursal(codigo_sucursal),

    CONSTRAINT fk_ruta_sucursal_destino
        FOREIGN KEY (codigo_sucursal_destino) 
        REFERENCES sucursal(codigo_sucursal)

);

CREATE TABLE viaje(
    id_viaje INT PRIMARY KEY AUTO_INCREMENT,
    numero_placa VARCHAR(15) NOT NULL,
    no_licencia VARCHAR(15) NOT NULL,
    fecha_hora_salida DATETIME NOT NULL,
    hora_estimada_llegada TIME NOT NULL,
    tipo_viaje ENUM('regular', 'privado') NOT NULL,
    estado ENUM('programado', 'en_curso', 'finalizado') NOT NULL,
    id_ruta INT NOT NULL,
    
    -- espacios_bus INT,
    -- espacios_ocupados INT

    CONSTRAINT fk_viaje_ruta
        FOREIGN KEY (id_ruta) 
        REFERENCES ruta(id_ruta),

    CONSTRAINT fk_viaje_bus
        FOREIGN KEY (numero_placa) 
        REFERENCES bus(numero_placa),
        
    CONSTRAINT fk_viaje_chofer
        FOREIGN KEY (no_licencia) 
        REFERENCES chofer(no_licencia)
 
); 

CREATE TABLE viaje_regular(
    id_viaje INT PRIMARY KEY NOT NULL,
    id_ruta INT NOT NULL,
    
    CONSTRAINT fk_viaje_regular_ruta
        FOREIGN KEY (id_ruta) 
        REFERENCES ruta(id_ruta),
    CONSTRAINT fk_viaje_regular_viaje
        FOREIGN KEY (id_viaje) 
        REFERENCES viaje(id_viaje)
); 

CREATE TABLE viaje_privado(
    id_viaje INT PRIMARY KEY,
     
    origen VARCHAR(150) NOT NULL,
    destino VARCHAR(150) NOT NULL,
    cantidad_pasajeros INT NOT NULL,

 
    CONSTRAINT fk_viaje_privado_viaje
        FOREIGN KEY (id_viaje) 
        REFERENCES viaje(id_viaje)

    
); 


CREATE TABLE salida_viaje(

     
    id_viaje INT PRIMARY KEY,
    correo VARCHAR(20) NOT NULL, 
    fecha_hora_salida DATETIME NOT NULL,
    kilometraje_actual DECIMAL(10, 2) DEFAULT 0.00,


    CONSTRAINT fk_salida_viaje_viaje
        FOREIGN KEY (id_viaje)
        REFERENCES viaje(id_viaje),

    CONSTRAINT fk_salida_viaje_usuario
        FOREIGN KEY (correo) 
        REFERENCES usuario(correo)
 
);

CREATE TABLE llegada_viaje(

    id_llegada INT PRIMARY KEY AUTO_INCREMENT,
    id_viaje INT NOT NULL,
    dpi VARCHAR(15) NOT NULL, 
    fecha_hora_llegada DATETIME NOT NULL,
    kilometraje_final DECIMAL(10, 2) DEFAULT 0.00,
    gasto_combustible DECIMAL(10, 2) NOT NULL,

    CONSTRAINT fk_llegada_viaje_viaje
        FOREIGN KEY (id_viaje)
        REFERENCES viaje(id_viaje),

    CONSTRAINT fk_llegada_viaje_perfil_usuario
        FOREIGN KEY (dpi) 
        REFERENCES perfil_usuario(dpi)
    
);

CREATE TABLE recarga_saldo (

    id_recarga INT AUTO_INCREMENT PRIMARY KEY,

    dpi VARCHAR(15) NOT NULL,
    monto DECIMAL(12,2) NOT NULL,

    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_recarga_saldo_perfil_usuario
        FOREIGN KEY (dpi) 
        REFERENCES perfil_usuario(dpi)
);


CREATE TABLE solicitud_viaje_privado( 
    id_solicitud INT PRIMARY KEY
);