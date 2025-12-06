/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Conexion.Conexion;
import Datos.Interfaces.CompraInterface;
import Entidades.Compra;
import java.sql.*;
import java.util.*;
/**
 *
 * @author macbook
 */

public class CompraDAO implements CompraInterface {

    @Override
    public boolean insertar(Compra c) {
        String sql = "INSERT INTO compra(fecha, id_proveedor, id_usuario, total) VALUES(?,?,?,?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(c.getFecha().getTime()));
            ps.setInt(2, c.getIdProveedor());
            ps.setInt(3, c.getIdUsuario());
            ps.setDouble(4, c.getTotal());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Compra buscarPorId(int idCompra) {
        String sql = "SELECT * FROM compra WHERE id_compra=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Compra c = new Compra();
                c.setIdCompra(rs.getInt("id_compra"));
                c.setFecha(rs.getTimestamp("fecha"));
                c.setIdProveedor(rs.getInt("id_proveedor"));
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setTotal(rs.getDouble("total"));
                return c;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Compra> listar() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compra";
        try (Connection cn = Conexion.getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Compra c = new Compra();
                c.setIdCompra(rs.getInt("id_compra"));
                c.setFecha(rs.getTimestamp("fecha"));
                c.setIdProveedor(rs.getInt("id_proveedor"));
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setTotal(rs.getDouble("total"));
                lista.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
}

