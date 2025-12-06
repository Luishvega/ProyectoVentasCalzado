/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.DetalleVenta;
import java.util.List;

/**
 *
 * @author macbook
 */

public interface DetalleVentaInterface {
    boolean insertar(DetalleVenta d);
    List<DetalleVenta> listarPorVenta(int idVenta);
}
