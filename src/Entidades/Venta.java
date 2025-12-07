/*
 * Clase Venta
 * Demuestra cómo una clase hija extiende la funcionalidad de la clase padre
 */
package Entidades;

import java.util.Date;

/**S
 * Mantiene sus atributos propios (idVenta, idCliente, subtotal, igv) y hereda
 * los atributos comunes de Transaccion (fecha, idUsuario, total)
 
 */
public class Venta extends Transaccion {
    
    // Atributos propios de Venta (no heredados)
    private int idVenta;
    private int idCliente;
    private double subtotal;
    private double igv;

    // Constructor vacío
    public Venta() {
        super(); // Llama al constructor de la clase padre
    }

    // Constructor completo usando super() para inicializar atributos heredados
    public Venta(int idVenta, Date fecha, int idCliente, int idUsuario, 
                 double total, double subtotal, double igv) {
        super(fecha, idUsuario, total);
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.subtotal = subtotal;
        this.igv = igv;
    }

    // Getters y Setters propios de Venta
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }
    
    /**
     * Implementación del método abstracto getTipoTransaccion()
     * Cada clase hija implementa este método de forma diferente
     * @return Tipo de transacción
     */
    @Override
    public String getTipoTransaccion() {
        return "Venta";
    }
    
    /**
     * Método propio de Venta para calcular el IGV
     * @param subtotal Subtotal de la venta
     * @return Valor del IGV (18%)
     */
    public double calcularIGV(double subtotal) {
        return subtotal * 0.18;
    }
}
