/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {

    // Insertar usuario
    public boolean insertar(Usuario u) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO usuario (nombre_usuario, contrasenia, id_rol, estado) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setInt(3, u.getIdRol());
            ps.setInt(4, u.getEstado());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    // Actualizar usuario
    public boolean actualizar(Usuario u) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE usuario SET nombre_usuario=?, contrasenia=?, id_rol=?, estado=? WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasenia());
            ps.setInt(3, u.getIdRol());
            ps.setInt(4, u.getEstado());
            ps.setInt(5, u.getIdUsuario());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    // Eliminar usuario
    public boolean eliminar(int idUsuario) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "DELETE FROM usuario WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    // Listar usuarios
    public List<Usuario> listar(String filtro) {
        List<Usuario> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM usuario WHERE nombre_usuario LIKE ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + filtro + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setEstado(rs.getInt("estado"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return lista;
    }
}
