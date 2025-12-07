# 📦 Sistema de Ventas de Calzado

## Descripción General

Sistema de escritorio desarrollado en **Java SE** utilizando **NetBeans IDE** para la gestión integral de una tienda de calzado. Permite administrar productos, clientes, proveedores, categorías, marcas y realizar operaciones de venta con control de inventario en tiempo real.

---

## 🏗️ Arquitectura del Sistema

El proyecto implementa una **arquitectura de 3 capas** que separa las responsabilidades del sistema:

### Capa de Presentación
Contiene todas las interfaces gráficas del usuario. Utiliza formularios Swing con un diseño MDI (Multiple Document Interface) que permite tener múltiples ventanas abiertas simultáneamente dentro de una ventana principal.

### Capa de Acceso a Datos (DAO)
Implementa el patrón DAO (Data Access Object) para encapsular toda la lógica de acceso a la base de datos. Cada entidad del sistema tiene su propio DAO que maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

### Capa de Entidades
Define los objetos de negocio del sistema como clases Java (POJOs). Estas clases representan las tablas de la base de datos y contienen únicamente atributos y métodos getter/setter.

### Capa de Conexión
Gestiona la conexión con la base de datos MySQL mediante JDBC, proporcionando un punto centralizado para obtener conexiones.

---

## 🛠️ Tecnologías Utilizadas

| Componente | Tecnología |
|------------|------------|
| Lenguaje | Java SE 21 |
| IDE | NetBeans IDE |
| Interfaz Gráfica | Java Swing |
| Base de Datos | MySQL 8.0 |
| Conector BD | MySQL Connector/J 8.0.33 |
| Selector de Fechas | JCalendar 1.4 (Toedter) |
| Patrón de Diseño | DAO (Data Access Object) |
| Look & Feel | Nimbus |

---

## 📁 Estructura del Proyecto

### Paquete Conexion
Contiene la clase de conexión a la base de datos que centraliza los parámetros de conexión (URL, usuario, contraseña) y proporciona un método estático para obtener conexiones.

### Paquete Entidades
Incluye 12 clases que representan las entidades del negocio:
- **Producto**: Artículos de calzado con sus características
- **Cliente**: Información de los compradores
- **Usuario**: Usuarios del sistema con credenciales
- **Venta y DetalleVenta**: Transacciones de venta
- **Compra y DetalleCompra**: Transacciones de compra
- **Proveedor**: Proveedores de mercadería
- **Categoria**: Clasificación de productos
- **Marca**: Marcas de calzado
- **Talla**: Tallas disponibles
- **Rol**: Roles de usuario (Administrador, Vendedor)

### Paquete Datos
Contiene los DAOs e interfaces que definen los contratos de acceso a datos. Cada DAO implementa las operaciones específicas para su entidad.

### Paquete Presentacion
Contiene los formularios de la aplicación:
- **FrmLogin**: Pantalla de inicio de sesión
- **FrmPrincipal**: Ventana principal con menú y escritorio MDI
- **FrmClientes**: Gestión de clientes
- **FrmProveedores**: Gestión de proveedores
- **FrmProductos**: Gestión de productos
- **FrmCategoriasMarcas**: Gestión de categorías y marcas
- **FrmVenta**: Registro de ventas
- **FrmReporteVentas**: Reporte de ventas por fecha

---

## 🗄️ Base de Datos

### Estructura de Tablas

El sistema utiliza una base de datos MySQL llamada **dbcalzado** con las siguientes tablas:

| Tabla | Descripción |
|-------|-------------|
| rol | Roles del sistema (Administrador, Vendedor, Almacenero) |
| usuario | Usuarios con credenciales y rol asignado |
| cliente | Clientes con datos personales |
| proveedor | Proveedores con razón social y RUC |
| categoria | Categorías de productos (Zapatillas, Botas, etc.) |
| marca | Marcas de calzado (Nike, Adidas, etc.) |
| talla | Tallas disponibles (35-45) |
| producto | Productos con precio, stock y características |
| venta | Cabecera de ventas con totales |
| detalle_venta | Productos vendidos en cada venta |
| compra | Cabecera de compras a proveedores |
| detalle_compra | Productos comprados en cada compra |

### Relaciones Principales

- Un **Usuario** pertenece a un **Rol**
- Una **Venta** la realiza un **Usuario** a un **Cliente**
- Un **Producto** pertenece a una **Categoría**, **Marca** y **Talla**
- Los **Detalles** vinculan las transacciones con los productos

### Manejo de Estados

El sistema implementa **eliminación lógica** mediante un campo `estado`:
- **1 = Activo**: El registro está disponible
- **0 = Inactivo**: El registro está desactivado (no se elimina físicamente)

---

## 🔐 Sistema de Autenticación

### Proceso de Login

1. El usuario ingresa su nombre de usuario y contraseña
2. El sistema busca en la base de datos un usuario que coincida
3. Verifica que el usuario esté activo (estado = 1)
4. Si las credenciales son correctas, abre el menú principal
5. El sistema registra qué usuario está logueado para las operaciones

### Usuarios por Defecto

| Usuario | Contraseña | Rol |
|---------|------------|-----|
| admin | admin123 | Administrador |
| vendedor1 | 123456 | Vendedor |

---

## 💼 Módulos del Sistema

### 1. Módulo de Login
- Validación de credenciales contra la base de datos
- Verificación de estado activo del usuario
- Mensaje de error en credenciales incorrectas
- Redirección automática al menú principal

### 2. Módulo Principal (Menú)
- Interfaz MDI con escritorio para formularios internos
- Barra de estado mostrando usuario, rol y fecha/hora
- Menú organizado por categorías funcionales
- Ventanas que se pueden cerrar, maximizar y minimizar

### 3. Módulo de Clientes
- Registro de nuevos clientes con datos personales
- Búsqueda por nombre o apellido
- Edición de datos existentes
- Visualización en tabla con todos los campos
- Desactivación de clientes (eliminación lógica)

### 4. Módulo de Proveedores
- Registro de proveedores con razón social y RUC
- Gestión de datos de contacto
- Búsqueda y filtrado
- Mantenimiento completo (crear, editar, desactivar)

### 5. Módulo de Productos
- Registro de productos con todos sus atributos
- Código de barras autogenerado
- Selección de categoría, marca y talla desde combos
- Control de stock y precios
- Tabla con información completa del inventario

### 6. Módulo de Categorías y Marcas
- Gestión organizada en pestañas separadas
- Crear nuevas categorías con nombre y descripción
- Crear nuevas marcas
- Editar seleccionando de la tabla
- Desactivar con confirmación (no elimina, solo cambia estado)
- Visualización del estado (Activo/Inactivo)

### 7. Módulo de Ventas
- Selección de cliente desde combo
- Búsqueda de productos por código o nombre
- Agregar múltiples productos al carrito
- Cálculo automático de:
  - Subtotal por producto
  - Subtotal general
  - IGV (18%)
  - Total final
- Eliminación de productos del carrito
- Registro de venta con actualización automática de stock
- Manejo transaccional (si falla algo, se revierte todo)

### 8. Módulo de Reporte de Ventas
- Selección de rango de fechas con calendario visual
- Fecha actual por defecto al abrir
- Filtrado de ventas por período
- Visualización de todas las ventas con:
  - ID de venta
  - Fecha y hora
  - Nombre del cliente
  - Nombre del vendedor
  - Subtotal, IGV y Total
- Contador de ventas encontradas
- Suma total del período consultado

---

## 🎯 Programación Orientada a Objetos (POO)

El proyecto implementa los principales conceptos de POO para demostrar buenas prácticas de desarrollo:

### Herencia en Entidades

El sistema utiliza **clases abstractas** como base para entidades relacionadas:

**Clase Persona (abstracta)**
- Es la clase padre de `Cliente`
- Define atributos comunes: nombres, apellidos, teléfono, dirección, estado
- Los getters y setters son heredados por las clases hijas
- Incluye un método abstracto `getTipoPersona()` que cada clase hija implementa

**Clase Transaccion (abstracta)**
- Es la clase padre de `Venta`
- Define atributos comunes: fecha, idUsuario, total
- Incluye un método abstracto `getTipoTransaccion()` para polimorfismo

### Polimorfismo

Se demuestra mediante **métodos abstractos** que cada clase hija implementa de forma diferente:

- `getTipoPersona()`: Cliente retorna "Cliente", otras clases retornarían su tipo
- `getTipoTransaccion()`: Venta retorna "Venta", Compra retornaría "Compra"
- `getNombreTabla()`: Cada DAO retorna el nombre de su tabla correspondiente

Esto permite tratar objetos de diferentes tipos de manera uniforme.

### Herencia en Capa de Datos

**Clase BaseDAO (abstracta)**
- Es la clase padre de los DAOs del sistema
- Define métodos comunes: `getConexion()`, `cerrarRecursos()`
- Define métodos abstractos: `insertar()`, `listar()`, `getNombreTabla()`
- Utiliza **Generics** para trabajar con diferentes tipos de entidades

**CategoriaDAO hereda de BaseDAO**
- Implementa los métodos abstractos de la clase padre
- Usa los métodos heredados para conexión y cierre de recursos
- Agrega métodos específicos como `listarTodas()` y `buscarPorId()`

### Beneficios de POO en el Proyecto

| Concepto | Beneficio |
|----------|-----------|
| Herencia | Reutilización de código, atributos comunes en una sola clase |
| Polimorfismo | Flexibilidad para tratar objetos de diferentes tipos |
| Abstracción | Define contratos que las clases hijas deben cumplir |
| Encapsulamiento | Protege los datos con modificadores de acceso |

### Diagrama de Herencia

**Entidades:**
- Persona (abstracta) → Cliente
- Transaccion (abstracta) → Venta

**Capa de Datos:**
- BaseDAO (abstracta) → CategoriaDAO

---

## 🔄 Características Técnicas

### Transacciones
Las operaciones críticas como el registro de ventas utilizan transacciones de base de datos para garantizar la integridad de los datos. Si ocurre un error durante el proceso, todos los cambios se revierten automáticamente.

### Control de Inventario
Cada vez que se registra una venta, el sistema actualiza automáticamente el stock de los productos vendidos, restando las cantidades correspondientes.

### Validaciones
- Campos obligatorios verificados antes de guardar
- Confirmación antes de desactivar registros
- Mensajes de éxito y error informativos
- Validación de fechas en reportes

### Interfaz de Usuario
- Diseño consistente en todos los formularios
- Botones con acciones claras (Guardar, Eliminar, Limpiar, Buscar)
- Tablas con datos organizados y legibles
- Formularios que se pueden redimensionar y reorganizar
- Componentes de fecha con calendario visual

---

## 📊 Flujo de una Venta

1. El vendedor abre el módulo de Ventas
2. Selecciona un cliente del listado
3. Ingresa el código del producto o busca por nombre
4. El sistema muestra la información del producto
5. Ingresa la cantidad deseada
6. Agrega el producto al carrito
7. Repite para más productos si es necesario
8. Verifica los totales calculados automáticamente
9. Presiona "Registrar Venta"
10. El sistema:
    - Guarda la cabecera de la venta
    - Guarda cada detalle
    - Descuenta el stock de cada producto
    - Muestra confirmación de éxito

---

## 📋 Requisitos del Sistema

### Hardware Mínimo
- Procesador: Cualquier procesador moderno
- RAM: 512 MB (recomendado 1 GB)
- Espacio: 100 MB para la aplicación

### Software Requerido
- Java JDK 21 o superior
- MySQL Server 8.0 o superior
- Sistema Operativo: Windows, Linux o macOS

### Librerías Externas
- MySQL Connector/J 8.0.33
- JCalendar 1.4

---

## 🚀 Instalación y Ejecución

### Paso 1: Base de Datos
Ejecutar el script SQL proporcionado en la carpeta `database/` para crear la base de datos con todas las tablas y datos iniciales.

### Paso 2: Configuración
Verificar los parámetros de conexión en la clase Conexion:
- Host: localhost
- Puerto: 3306
- Base de datos: dbcalzado
- Usuario y contraseña de MySQL

### Paso 3: Ejecución
Compilar y ejecutar el proyecto desde NetBeans o ejecutar el JAR generado en la carpeta `dist/`.

### Paso 4: Acceso
Ingresar con las credenciales de usuario (admin/admin123 por defecto).

---

## 📝 Consideraciones Finales

### Seguridad
- Las contraseñas se almacenan en texto plano (para entorno educativo)
- En producción se recomienda implementar hash de contraseñas

### Escalabilidad
- La arquitectura en capas permite agregar nuevos módulos fácilmente
- Los DAOs pueden extenderse para nuevas funcionalidades

### Mantenibilidad
- Código organizado por responsabilidades
- Nombres descriptivos en clases y métodos
- Separación clara entre interfaz y lógica

---

## 👥 Créditos

Sistema desarrollado como proyecto de gestión comercial para tienda de calzado, implementando las mejores prácticas de desarrollo en Java con acceso a bases de datos.

---

*Documentación actualizada - Sistema de Ventas de Calzado v1.0*
