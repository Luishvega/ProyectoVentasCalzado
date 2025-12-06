/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Pruebas;

import Datos.CategoriaDAO;
import Datos.TallaDAO;
import Entidades.Categoria;
import Entidades.Talla;

public class PruebaDAO {
    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        TallaDAO tallaDAO = new TallaDAO();

        // Probar categorías
        System.out.println("=== Categorías activas ===");
        for (Categoria c : categoriaDAO.listar()) {
            System.out.println("ID: " + c.getIdCategoria() + " | Nombre: " + c.getNombre());
        }

        // Probar tallas
        System.out.println("=== Tallas activas ===");
        for (Talla t : tallaDAO.listar()) {
            System.out.println("ID: " + t.getIdTalla() + " | Etiqueta: " + t.getEtiqueta());
        }
    }
}