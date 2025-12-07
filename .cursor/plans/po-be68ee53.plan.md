<!-- be68ee53-2a5c-4602-bf67-6bf171e69c58 77469135-fe85-49a6-9f6b-d27a68a11027 -->
# Implementación de POO - Herencia y Polimorfismo (Enfoque Conservador)

**Principio clave**: No se modifican DAOs existentes ni se rompen formularios. La base de datos NO cambia.

---

## Parte 1: Herencia en Entidades

### 1.1 Crear clase abstracta `Persona`

Crear la clase padre abstracta en [src/Entidades/Persona.java](src/Entidades/Persona.java) con atributos comunes:

- nombres, apellidos, telefono, direccion, estado
- Constructor y getters/setters
- Método abstracto `getTipoPersona()` para demostrar polimorfismo

### 1.2 Modificar `Cliente` para heredar de `Persona`

Actualizar [src/Entidades/Cliente.java](src/Entidades/Cliente.java):

- Extender de `Persona`
- Mantener solo atributos propios (idCliente, dni)
- Usar `super()` en constructor
- Implementar método `getTipoPersona()` retornando "Cliente"

### 1.3 Crear clase abstracta `Transaccion`

Crear [src/Entidades/Transaccion.java](src/Entidades/Transaccion.java) con atributos comunes:

- fecha, idUsuario, total
- Método abstracto `getTipoTransaccion()`

### 1.4 Modificar `Venta` para heredar de `Transaccion`

Actualizar [src/Entidades/Venta.java](src/Entidades/Venta.java):

- Extender de `Transaccion`
- Mantener atributos propios (idVenta, idCliente, subtotal, igv)
- Implementar `getTipoTransaccion()` retornando "Venta"

---

## Parte 2: Clase Abstracta para DAOs

### 2.1 Crear clase abstracta `BaseDAO<T>`

Crear [src/Datos/BaseDAO.java](src/Datos/BaseDAO.java) con:

- Método `getConexion()` para obtener conexión
- Método `cerrarRecursos()` para cerrar conexiones
- Métodos abstractos: `insertar()`, `listar()`, `getNombreTabla()`

### 2.2 Modificar `CategoriaDAO` para heredar de `BaseDAO`

Actualizar [src/Datos/CategoriaDAO.java](src/Datos/CategoriaDAO.java):

- Extender de `BaseDAO<Categoria>`
- Implementar los métodos abstractos
- Usar métodos heredados para conexión

---

## Parte 3: Documentación

### 3.1 Actualizar RESUMEN_TECNICO.md

Agregar sección explicando:

- Herencia implementada (Persona, Transaccion)
- Polimorfismo demostrado (métodos abstractos)
- Clase abstracta BaseDAO
- Beneficios de POO en el proyecto

---

## Archivos a crear/modificar:

- **Crear**: `Persona.java`, `Transaccion.java`, `BaseDAO.java`
- **Modificar**: `Cliente.java`, `Venta.java`, `CategoriaDAO.java`, `RESUMEN_TECNICO.md`

### To-dos

- [ ] Crear clase abstracta Persona con atributos comunes y método abstracto
- [ ] Modificar Cliente para heredar de Persona
- [ ] Crear clase abstracta Transaccion
- [ ] Modificar Venta para heredar de Transaccion
- [ ] Crear clase abstracta BaseDAO genérica
- [ ] Modificar CategoriaDAO para heredar de BaseDAO
- [ ] Actualizar RESUMEN_TECNICO.md con explicación de POO