/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos.Interfaces;

import Entidades.Compra;
import java.util.List;


public interface CompraInterface {
    boolean insertar(Compra c);
    Compra buscarPorId(int idCompra);
    List<Compra> listar();
}

