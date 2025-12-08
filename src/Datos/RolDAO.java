/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Datos.Interfaces.RolInterface;
import Entidades.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolDAO implements RolInterface {

    @Override
    public boolean insertar(Rol r) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO rol (nombre) VALUES (?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, r.getNombre());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar rol: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    @Override
    public boolean actualizar(Rol r) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE rol SET nombre=? WHERE id_rol=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, r.getNombre());
            ps.setInt(2, r.getIdRol());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar rol: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    @Override
    public boolean eliminar(int idRol) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "DELETE FROM rol WHERE id_rol=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idRol);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar rol: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    @Override
    public List<Rol> listar() {
        List<Rol> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM rol";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rol r = new Rol();
                r.setIdRol(rs.getInt("id_rol"));
                r.setNombre(rs.getString("nombre"));
                lista.add(r);
            }
        } catch (Exception e) {
            System.out.println("Error al listar roles: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return lista;
    }
}
