/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Categoria;
import java.util.List;
/**
 *
 * @author macbook
 */

public interface CategoriaInterface {
    boolean insertar(Categoria c);
    boolean actualizar(Categoria c);
    boolean eliminar(int idCategoria);
    List<Categoria> listar(String filtro);
}
    