/*
 * Clase Cliente
 */
package Entidades;

/**
 * Mantiene sus atributos propios (idCliente, dni) y hereda
 * los atributos comunes de Persona (nombres, apellidos, telefono, etc.)
 
 */
public class Cliente extends Persona {
    
    // Atributos propios de Cliente (no heredados)
    private int idCliente;
    private String dni;

    // Constructor vacío
    public Cliente() {
        super(); // Llama al constructor de la clase padre
    }

    // Constructor completo usando super() para inicializar atributos heredados
    public Cliente(int idCliente, String nombres, String apellidos, String dni, 
                   String direccion, String telefono, int estado) {
        super(nombres, apellidos, telefono, direccion, estado); // Herencia
        this.idCliente = idCliente;
        this.dni = dni;
    }

    // Getters y Setters propios de Cliente
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    /**
     * Implementación del método abstracto getTipoPersona()
     * @return Tipo de persona
     */
    @Override
    public String getTipoPersona() {
        return "Cliente";
    }
    
    /**
     * Sobrescritura del método toString() para mostrar información del cliente
     */
    @Override
    public String toString() {
        return getNombreCompleto(); // Usa método heredado de Persona
    }
}
