# 📦 Sistema de Ventas de Calzado

## Descripción General

Sistema de escritorio desarrollado en **Java SE** utilizando **NetBeans IDE** para la gestión integral de ventas de una tienda de calzado. Permite administrar productos, clientes, proveedores, usuarios y realizar operaciones de compra-venta con control de inventario.

---

## 🏗️ Arquitectura del Sistema

El proyecto implementa una **arquitectura de 3 capas** (Presentación, Lógica de Negocio/Datos, Persistencia):

```
┌─────────────────────────────────────────────────────────┐
│                   CAPA DE PRESENTACIÓN                  │
│              (Paquete: Presentacion)                    │
│   FrmLogin, FrmPrincipal, FrmClientes, FrmProductos,    │
│           FrmProveedores, FrmVenta                      │
└─────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│              CAPA DE ACCESO A DATOS (DAO)               │
│                  (Paquete: Datos)                       │
│   ProductoDAO, ClienteDAO, VentaDAO, UsuarioDAO, etc.   │
│                                                         │
│              Interfaces (Datos/Interfaces)              │
│   ProductoInterface, ClienteInterface, VentaInterface   │
└─────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                   CAPA DE ENTIDADES                     │
│                 (Paquete: Entidades)                    │
│   Producto, Cliente, Venta, DetalleVenta, Usuario, etc. │
└─────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                  CAPA DE CONEXIÓN                       │
│                  (Paquete: Conexion)                    │
│                    Conexion.java                        │
└─────────────────────────────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│              BASE DE DATOS MySQL                        │
│                   dbcalzado                             │
└─────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tecnologías Utilizadas

| Componente | Tecnología |
|------------|------------|
| **Lenguaje** | Java SE |
| **IDE** | NetBeans IDE |
| **GUI** | Java Swing |
| **Base de Datos** | MySQL |
| **Conector BD** | JDBC (MySQL Connector) |
| **Patrón de Diseño** | DAO (Data Access Object) |
| **Look & Feel** | Nimbus |

---

## 📁 Estructura de Paquetes

```
src/
├── Conexion/
│   ├── Conexion.java          # Singleton de conexión a MySQL
│   └── DemoCnx.java           # Clase de prueba de conexión
│
├── Entidades/                  # POJOs (Plain Old Java Objects)
│   ├── Producto.java
│   ├── Cliente.java
│   ├── Usuario.java
│   ├── Venta.java
│   ├── DetalleVenta.java
│   ├── Compra.java
│   ├── DetalleCompra.java
│   ├── Proveedor.java
│   ├── Categoria.java
│   ├── Marca.java
│   ├── Talla.java
│   └── Rol.java
│
├── Datos/                      # Capa de Acceso a Datos
│   ├── Interfaces/            # Contratos para los DAOs
│   │   ├── ProductoInterface.java
│   │   ├── ClienteInterface.java
│   │   ├── VentaInterface.java
│   │   └── ...
│   ├── ProductoDAO.java
│   ├── ClienteDAO.java
│   ├── VentaDAO.java
│   ├── UsuarioDAO.java
│   └── ...
│
├── Presentacion/               # Interfaces gráficas (JFrame/JInternalFrame)
│   ├── FrmLogin.java          # Formulario de inicio de sesión
│   ├── FrmPrincipal.java      # Ventana principal MDI
│   ├── FrmClientes.java
│   ├── FrmProductos.java
│   ├── FrmProveedores.java
│   └── FrmVenta.java
│
└── Pruebas/
    └── PruebaDAO.java         # Pruebas unitarias de DAOs
```

---

## 🗄️ Modelo de Base de Datos

### Entidades Principales

| Entidad | Descripción | Campos Clave |
|---------|-------------|--------------|
| **Producto** | Artículos de calzado | id_producto, nombre, precio, stock, código_barras, id_categoria, id_marca, id_talla, color |
| **Cliente** | Clientes del negocio | id_cliente, nombres, apellidos, dni, dirección, teléfono, estado |
| **Usuario** | Usuarios del sistema | id_usuario, nombre_usuario, contraseña, id_rol, estado |
| **Venta** | Cabecera de venta | id_venta, fecha, id_cliente, id_usuario, subtotal, igv, total |
| **DetalleVenta** | Líneas de venta | id_detalle, id_venta, id_producto, cantidad, precio_unitario, subtotal |
| **Compra** | Registro de compras | id_compra, fecha, id_proveedor, id_usuario, total |
| **Proveedor** | Proveedores de productos | id_proveedor, nombre, ruc, dirección, teléfono |
| **Categoria** | Categorías de productos | id_categoria, nombre, descripción |
| **Marca** | Marcas de calzado | id_marca, nombre |
| **Talla** | Tallas disponibles | id_talla, numero |
| **Rol** | Roles de usuario | id_rol, nombre (Administrador, Vendedor) |

### Diagrama de Relaciones

```
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│   Usuario    │       │    Venta     │       │   Cliente    │
│──────────────│       │──────────────│       │──────────────│
│ id_usuario   │◄──────│ id_usuario   │       │ id_cliente   │
│ nombre       │       │ id_cliente   │──────►│ nombres      │
│ contraseña   │       │ fecha        │       │ apellidos    │
│ id_rol       │       │ total        │       │ dni          │
└──────────────┘       └──────┬───────┘       └──────────────┘
       │                      │
       ▼                      ▼
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│     Rol      │       │ DetalleVenta │       │   Producto   │
│──────────────│       │──────────────│       │──────────────│
│ id_rol       │       │ id_venta     │       │ id_producto  │◄┐
│ nombre       │       │ id_producto  │──────►│ nombre       │ │
└──────────────┘       │ cantidad     │       │ precio       │ │
                       │ subtotal     │       │ stock        │ │
                       └──────────────┘       │ id_categoria │ │
                                              │ id_marca     │ │
                                              │ id_talla     │ │
                                              └──────────────┘ │
                                                      ▲        │
                       ┌──────────────┐               │        │
                       │ DetalleCompra│───────────────┘        │
                       │──────────────│                        │
                       │ id_compra    │◄───────────────────────┤
                       │ id_producto  │       ┌──────────────┐ │
                       │ cantidad     │       │    Compra    │ │
                       └──────────────┘       │──────────────│ │
                                              │ id_compra    │─┘
                                              │ id_proveedor │
                                              │ id_usuario   │
                                              └──────┬───────┘
                                                     │
                                                     ▼
                                              ┌──────────────┐
                                              │  Proveedor   │
                                              │──────────────│
                                              │ id_proveedor │
                                              │ nombre       │
                                              │ ruc          │
                                              └──────────────┘
```

---

## 🔐 Sistema de Autenticación y Control de Acceso

### Flujo de Autenticación

1. El usuario ingresa credenciales en `FrmLogin`
2. Se valida contra la base de datos mediante `UsuarioDAO`
3. Se verifica que el usuario esté activo (`estado = 1`)
4. Si es válido, se abre `FrmPrincipal` pasando el objeto `Usuario`

### Control de Roles

| Rol | ID | Permisos |
|-----|----|----------|
| **Administrador** | 1 | Acceso completo: Usuarios, Roles, Compras, Ventas, Mantenimientos |
| **Vendedor** | 2 | Acceso limitado: Clientes, Productos, Ventas |

```java
private void controlarAcceso(int idRol) {
    boolean esVendedor = (idRol == 2);
    mnuUsuarios.setEnabled(!esVendedor);
    mnuRoles.setEnabled(!esVendedor);
    mnuCompras.setEnabled(!esVendedor);
}
```

---

## 💼 Funcionalidades Principales

### 1. Módulo de Ventas
- Registro de ventas con múltiples productos
- Cálculo automático de subtotal, IGV y total
- Control transaccional (commit/rollback)
- Actualización automática de stock
- Búsqueda de productos por código de barras o nombre

### 2. Módulo de Productos
- CRUD completo de productos
- Gestión de categorías, marcas y tallas
- Control de stock
- Código de barras

### 3. Módulo de Clientes
- Registro y mantenimiento de clientes
- Búsqueda por DNI o nombre

### 4. Módulo de Proveedores
- Gestión de proveedores
- Registro de compras

### 5. Módulo de Reportes *(planificado)*
- Inventario
- Ventas por fecha
- Compras por proveedor

---

## 🔄 Patrón DAO Implementado

Cada entidad tiene su correspondiente DAO que implementa una interfaz:

```java
// Interface
public interface ProductoInterface {
    boolean insertar(Producto p);
    boolean actualizar(Producto p);
    boolean eliminar(int idProducto);
    List<Producto> listar(String filtro);
}

// Implementación
public class ProductoDAO implements ProductoInterface {
    // Métodos CRUD con PreparedStatement
}
```

### Operaciones CRUD típicas

| Operación | Método | SQL |
|-----------|--------|-----|
| Create | `insertar()` | `INSERT INTO tabla...` |
| Read | `listar()`, `buscarPorId()` | `SELECT * FROM tabla...` |
| Update | `actualizar()` | `UPDATE tabla SET...` |
| Delete | `eliminar()` | `DELETE FROM tabla...` |

---

## 🔌 Conexión a Base de Datos

```java
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/dbcalzado";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    public static Connection getConexion() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
```

---

## 📊 Transacciones

El sistema implementa **transacciones ACID** para operaciones críticas como el registro de ventas:

```java
public boolean registrarVentaConDetalles(Venta venta, List<DetalleVenta> detalles) {
    cn.setAutoCommit(false);  // Iniciar transacción
    
    // 1. Insertar cabecera de venta
    // 2. Obtener ID generado
    // 3. Insertar cada detalle
    // 4. Actualizar stock de cada producto
    
    cn.commit();  // Confirmar si todo OK
    // o
    cn.rollback();  // Revertir si hay error
}
```

---

## 🖥️ Interfaz de Usuario

- **Patrón MDI** (Multiple Document Interface) con `JDesktopPane`
- Formularios internos (`JInternalFrame`) para cada módulo
- Barra de estado con usuario activo, rol y fecha/hora
- Menú principal con acceso según permisos
- Look & Feel **Nimbus** para apariencia moderna

---

## 📋 Requisitos del Sistema

- **JDK**: Java 8 o superior
- **MySQL**: 5.7 o superior
- **Driver**: MySQL Connector/J
- **RAM**: 512 MB mínimo
- **SO**: Windows, Linux o macOS

---

## 🚀 Ejecución

1. Crear la base de datos `dbcalzado` en MySQL
2. Ejecutar scripts de creación de tablas
3. Configurar credenciales en `Conexion.java`
4. Compilar y ejecutar `FrmLogin.java`

---

## 📝 Notas Técnicas

- Las contraseñas se almacenan en texto plano (se recomienda implementar hash)
- El sistema soporta eliminación lógica mediante campo `estado`
- Los formularios `.form` son diseñados con el GUI Builder de NetBeans
- El IGV se calcula sobre el subtotal en las ventas

---

## 👥 Autores

Proyecto desarrollado como sistema de gestión comercial para tienda de calzado.

---

*Documentación generada para explicación técnica del proyecto.*


