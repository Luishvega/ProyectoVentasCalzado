/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Rol;
import java.util.List;



public interface RolInterface {
    boolean insertar(Rol r);
    boolean actualizar(Rol r);
    boolean eliminar(int idRol);
    List<Rol> listar();
}
