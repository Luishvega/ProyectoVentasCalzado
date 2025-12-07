/*
 * CategoriaDAO - Hereda de BaseDAO (HERENCIA en capa de datos)
 * Demuestra cómo un DAO extiende la funcionalidad de BaseDAO
 */
package Datos;

import Conexion.Conexion;
import Entidades.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la entidad Categoria que HEREDA de BaseDAO.
 * Implementa los métodos abstractos definidos en la clase padre
 * y agrega métodos específicos para categorías.
 
 */
public class CategoriaDAO extends BaseDAO<Categoria> {

    /**
     * Implementación del método abstracto getNombreTabla()
     * @return Nombre de la tabla en la base de datos
     */
    @Override
    public String getNombreTabla() {
        return "categoria";
    }

    /**
     * Implementación del método abstracto insertar()
     * Usa el método heredado getConexion() de BaseDAO
     */
    @Override
    public boolean insertar(Categoria c) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = getConexion(); // Método heredado de BaseDAO
            ps = cn.prepareStatement(
                "INSERT INTO categoria (nombre, descripcion, estado) VALUES (?, ?, ?)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getEstado());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
        } finally {
            cerrarRecursos(cn, ps); // Método heredado de BaseDAO
        }
        return resp;
    }

    /**
     * Implementación del método abstracto listar()
     * Lista solo categorías activas
     */
    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nombre, descripcion, estado FROM categoria WHERE estado=1 ORDER BY nombre";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = getConexion(); // Método heredado
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
            cerrarRecursos(cn, ps, rs); // Método heredado
        }
        return lista;
    }

    // ========== Métodos adicionales específicos de CategoriaDAO ==========

    /**
     * Actualizar categoría
     */
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

    /**
     * Desactivar categoría (cambiar estado a 0)
     */
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

    /**
     * Listar todas las categorías (activas e inactivas)
     */
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

    /**
     * Listar categorías con filtro (para búsquedas)
     */
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

    /**
     * Buscar nombre por ID (para mostrar en tabla de productos)
     */
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
