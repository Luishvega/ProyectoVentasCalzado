/*
 * Clase abstracta Persona - Demuestra HERENCIA en POO
 * Esta clase es padre de Cliente y otras entidades que representan personas
 */
package Entidades;

/**
 * Clase abstracta que define los atributos comunes de una persona.
 * Implementa el concepto de HERENCIA permitiendo que otras clases
 * hereden estos atributos y métodos.
 * 
 * @author Proyecto Calzado
 */
public abstract class Persona {
    
    // Atributos comunes para cualquier persona
    protected String nombres;
    protected String apellidos;
    protected String telefono;
    protected String direccion;
    protected int estado;

    // Constructor vacío
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombres, String apellidos, String telefono, String direccion, int estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    // Getters y Setters
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    /**
     * Método abstracto que demuestra POLIMORFISMO.
     * Cada clase hija implementará este método de forma diferente.
     * @return Descripción del tipo de persona
     */
    public abstract String getTipoPersona();
    
    /**
     * Método que retorna el nombre completo de la persona.
     * Este método es heredado por todas las clases hijas.
     * @return Nombre completo (nombres + apellidos)
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}

