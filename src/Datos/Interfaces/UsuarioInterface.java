/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Usuario;
import java.util.List;


public interface UsuarioInterface {
    boolean insertar(Usuario u);
    boolean actualizar(Usuario u);
    boolean eliminar(int idUsuario);
    List<Usuario> listar(String filtro);
}
