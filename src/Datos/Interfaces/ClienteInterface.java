/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Cliente;
import java.util.List;
/**
 *
 * @author macbook
 */

public interface ClienteInterface {
    boolean insertar(Cliente c);
    boolean actualizar(Cliente c);
    boolean eliminar(int idCliente);
    Cliente buscarPorId(int idCliente);
    List<Cliente> listar(String filtro);
}
