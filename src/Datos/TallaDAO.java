/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Entidades.Talla;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TallaDAO {

    // Listar todas las tallas activas (para combos)
    public List<Talla> listar() {
        List<Talla> lista = new ArrayList<>();
        String sql = "SELECT id_talla, etiqueta, estado FROM talla WHERE estado=1";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Talla t = new Talla();
                t.setIdTalla(rs.getInt("id_talla"));
                t.setEtiqueta(rs.getString("etiqueta"));
                t.setEstado(rs.getInt("estado"));
                lista.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tallas: " + e.getMessage());
        }
        return lista;
    }

    // Listar tallas con filtro (para búsquedas)
    public List<Talla> listar(String filtro) {
        List<Talla> lista = new ArrayList<>();
        String sql = "SELECT id_talla, etiqueta, estado FROM talla WHERE etiqueta LIKE ? AND estado=1";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Talla t = new Talla();
                t.setIdTalla(rs.getInt("id_talla"));
                t.setEtiqueta(rs.getString("etiqueta"));
                t.setEstado(rs.getInt("estado"));
                lista.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tallas con filtro: " + e.getMessage());
        }
        return lista;
    }

    // Buscar etiqueta por ID (para mostrar en tabla de productos)
    public String buscarPorId(int idTalla) {
        String etiqueta = "";
        String sql = "SELECT etiqueta FROM talla WHERE id_talla=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idTalla);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                etiqueta = rs.getString("etiqueta");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar talla: " + e.getMessage());
        }
        return etiqueta;
    }
}