/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Proveedor;
import java.util.List;


public interface ProveedorInterface {
    boolean insertar(Proveedor p);
    boolean actualizar(Proveedor p);
    boolean eliminar(int idProveedor);
    Proveedor buscarPorId(int idProveedor);
    List<Proveedor> listar(String filtro);
}

