/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Venta;
import java.util.List;
/**
 *
 * @author macbook
 */

public interface VentaInterface {
    boolean insertar(Venta v);
    Venta buscarPorId(int idVenta);
    List<Venta> listar();
}
