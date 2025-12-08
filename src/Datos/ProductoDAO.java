/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Datos.Interfaces.ProductoInterface;
import Entidades.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements ProductoInterface {

    @Override
    public boolean insertar(Producto p) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO producto (codigo_barras, nombre, descripcion, precio, stock, idCategoria, idMarca, idTalla, color, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getCodigobarras() != null ? Integer.parseInt(p.getCodigobarras().replace("CB-", "")) : 0);
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getIdCategoria());
            ps.setInt(7, p.getIdmarca());
            ps.setInt(8, p.getIdtalla());
            ps.setString(9, p.getColor());
            ps.setInt(10, p.getEstado());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }
    
    @Override
    public boolean actualizar(Producto p) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE producto SET codigo_barras=?, nombre=?, descripcion=?, precio=?, stock=?, idCategoria=?, idMarca=?, idTalla=?, color=?, estado=? WHERE idProducto=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getCodigobarras() != null ? Integer.parseInt(p.getCodigobarras().replace("CB-", "")) : 0);
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getIdCategoria());
            ps.setInt(7, p.getIdmarca());
            ps.setInt(8, p.getIdtalla());
            ps.setString(9, p.getColor());
            ps.setInt(10, p.getEstado());
            ps.setInt(11, p.getIdProducto());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    @Override
    public int obtenerUltimoCodigo() {
    int ultimo = 0;
    String sql = "SELECT MAX(codigo_barras) as maximo FROM producto";
    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            ultimo = rs.getInt("maximo");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ultimo;
}
    
    @Override
    public boolean desactivar(int idProducto) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE producto SET estado=0 WHERE idProducto=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idProducto);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al desactivar producto: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }
    
    @Override
    public Producto buscarPorId(int idProducto) {
    String sql = "SELECT * FROM producto WHERE idProducto=?";
    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idProducto);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("idProducto"));
            p.setCodigobarras(rs.getString("codigo_barras"));
            p.setNombre(rs.getString("nombre"));
            p.setIdCategoria(rs.getInt("idCategoria"));
            p.setIdmarca(rs.getInt("idMarca"));
            p.setIdtalla(rs.getInt("idTalla"));
            p.setColor(rs.getString("color"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setEstado(rs.getInt("estado"));
            return p;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
    Producto p = null;
    String sql = "SELECT * FROM producto WHERE codigo_barras = ?";
    try (Connection cn = Conexion.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setString(1, codigoBarras);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new Producto();
            p.setIdProducto(rs.getInt("idProducto"));
            p.setCodigobarras(rs.getString("codigo_barras"));
            p.setNombre(rs.getString("nombre"));
            p.setIdCategoria(rs.getInt("idCategoria"));
            p.setIdmarca(rs.getInt("idMarca"));
            p.setIdtalla(rs.getInt("idTalla"));
            p.setColor(rs.getString("color"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setEstado(rs.getInt("estado"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return p;
}

    @Override
    public boolean actualizarStock(int idProducto, int cantidad) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE producto SET stock = stock - ? WHERE idProducto = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idProducto);
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar stock: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }
    
    @Override
    public List<Producto> listar(String filtro) {
        List<Producto> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + filtro + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setCodigobarras(String.valueOf(rs.getInt("codigo_barras")));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdmarca(rs.getInt("idMarca"));
                p.setIdtalla(rs.getInt("idTalla"));
                p.setColor(rs.getString("color"));
                p.setEstado(rs.getInt("estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return lista;
    }
}
