/*
 * Clase abstracta Transaccion - Demuestra HERENCIA en POO
 * Esta clase es padre de Venta y Compra
 */
package Entidades;

import java.util.Date;

/**
 * Clase abstracta que define los atributos comunes de una transacción comercial.
 * Implementa el concepto de HERENCIA permitiendo que Venta y Compra
 * hereden estos atributos y métodos.
 * 
 * @author Proyecto Calzado
 */
public abstract class Transaccion {
    
    // Atributos comunes para cualquier transacción
    protected Date fecha;
    protected int idUsuario;
    protected double total;

    // Constructor vacío
    public Transaccion() {
    }

    // Constructor con parámetros
    public Transaccion(Date fecha, int idUsuario, double total) {
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.total = total;
    }

    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    /**
     * Método abstracto que demuestra POLIMORFISMO.
     * Cada clase hija (Venta, Compra) implementará este método de forma diferente.
     * @return Tipo de transacción
     */
    public abstract String getTipoTransaccion();
    
    /**
     * Método que verifica si la transacción tiene un monto válido.
     * Este método es heredado por todas las clases hijas.
     * @return true si el total es mayor a 0
     */
    public boolean esValida() {
        return total > 0;
    }
}

