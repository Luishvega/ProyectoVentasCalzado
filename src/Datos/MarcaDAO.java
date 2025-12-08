/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Datos.Interfaces.MarcaInterface;
import Entidades.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements MarcaInterface {
    
    @Override
    public boolean insertar(Marca m) {
        boolean resp = false;
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                 "INSERT INTO marca (nombre, estado) VALUES (?, ?)")) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEstado());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar marca: " + e.getMessage());
        }
        return resp;
    }
    
    @Override
    public boolean actualizar(Marca m) {
        boolean resp = false;
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                 "UPDATE marca SET nombre=?, estado=? WHERE id_marca=?")) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getEstado());
            ps.setInt(3, m.getIdMarca());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar marca: " + e.getMessage());
        }
        return resp;
    }
    
    @Override
    public boolean desactivar(int idMarca) {
        boolean resp = false;
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                 "UPDATE marca SET estado=0 WHERE id_marca=?")) {
            ps.setInt(1, idMarca);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al desactivar marca: " + e.getMessage());
        }
        return resp;
    }
    
    @Override
    public List<Marca> listarTodas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id_marca, nombre, estado FROM marca ORDER BY nombre";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("id_marca"));
                m.setNombre(rs.getString("nombre"));
                m.setEstado(rs.getInt("estado"));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public List<Marca> listar() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT id_marca, nombre, estado FROM marca WHERE estado=1 ORDER BY nombre";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("id_marca"));
                m.setNombre(rs.getString("nombre"));
                m.setEstado(rs.getInt("estado"));
                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String buscarPorId(int id) {
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement("SELECT nombre FROM marca WHERE id_marca=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("nombre");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
