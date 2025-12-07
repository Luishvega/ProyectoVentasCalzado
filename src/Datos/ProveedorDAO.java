/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Datos.Interfaces.ProveedorInterface;
import Entidades.Proveedor;
import java.sql.*;
import java.util.*;


public class ProveedorDAO implements ProveedorInterface {

    @Override
    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO proveedor(razon_social, ruc, telefono, direccion, estado) VALUES(?,?,?,?,?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setInt(5, p.getEstado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE proveedor SET razon_social=?, ruc=?, telefono=?, direccion=?, estado=? WHERE id_proveedor=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getRazonSocial());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getDireccion());
            ps.setInt(5, p.getEstado());
            ps.setInt(6, p.getIdProveedor());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(int idProveedor) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Proveedor buscarPorId(int idProveedor) {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setRazonSocial(rs.getString("razon_social"));
                p.setRuc(rs.getString("ruc"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setEstado(rs.getInt("estado"));
                return p;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Proveedor> listar(String filtro) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE razon_social LIKE ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setRazonSocial(rs.getString("razon_social"));
                p.setRuc(rs.getString("ruc"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}

