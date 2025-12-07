/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Producto;
import java.util.List;


public interface ProductoInterface {
    boolean insertar(Producto p);
    boolean actualizar(Producto p);
    boolean eliminar(int idProducto);
    List<Producto> listar(String filtro);
}
