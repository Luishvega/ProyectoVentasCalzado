/*
 * CategoriaDAO
 * Demuestra cómo un DAO extiende la funcionalidad de BaseDAO
 */
package Datos;

import Conexion.Conexion;
import Datos.Interfaces.CategoriaInterface;
import Entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO extends BaseDAO<Categoria> implements CategoriaInterface {

    @Override
    public String getNombreTabla() {
        return "categoria";
    }

    @Override
    public boolean insertar(Categoria c) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = getConexion();
            ps = cn.prepareStatement(
                "INSERT INTO categoria (nombre, descripcion, estado) VALUES (?, ?, ?)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getEstado());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps);
        }
        return resp;
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre, descripcion, estado FROM categoria WHERE estado=1 ORDER BY nombre";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = getConexion();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps, rs);
        }
        return lista;
    }

    @Override
    public boolean actualizar(Categoria c) {
        boolean resp = false;
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                 "UPDATE categoria SET nombre=?, descripcion=?, estado=? WHERE id_categoria=?")) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getEstado());
            ps.setInt(4, c.getIdCategoria());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean desactivar(int idCategoria) {
        boolean resp = false;
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                 "UPDATE categoria SET estado=0 WHERE id_categoria=?")) {
            ps.setInt(1, idCategoria);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al desactivar categoría: " + e.getMessage());
        }
        return resp;
    }

    @Override
    public List<Categoria> listarTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre, descripcion, estado FROM categoria ORDER BY nombre";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<Categoria> listar(String filtro) {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre, descripcion, estado FROM categoria WHERE nombre LIKE ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getInt("estado"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error al listar categorías con filtro: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public String buscarPorId(int idCategoria) {
        String nombre = "";
        String sql = "SELECT nombre FROM categoria WHERE id_categoria=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar categoría: " + e.getMessage());
        }
        return nombre;
    }
}
