/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Cliente;
import java.util.List;


public interface ClienteInterface {
    boolean insertar(Cliente c);
    boolean actualizar(Cliente c);
    boolean desactivar(int idCliente);
    Cliente buscarPorId(int idCliente);
    Cliente buscarPorDocumento(String documento);
    List<Cliente> listar(String filtro);
}
