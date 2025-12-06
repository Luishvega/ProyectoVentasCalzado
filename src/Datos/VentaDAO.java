/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Entidades.DetalleVenta;
import Entidades.Venta;
import Conexion.Conexion;
import Datos.Interfaces.VentaInterface;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author macbook
 */

public class VentaDAO implements VentaInterface {

    @Override
    public boolean insertar(Venta v) {
        String sql = "INSERT INTO venta(fecha, id_cliente, id_usuario, total, subtotal, igv) VALUES(?,?,?,?,?,?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setTimestamp(1, new java.sql.Timestamp(v.getFecha().getTime()));
            ps.setInt(2, v.getIdCliente());
            ps.setInt(3, v.getIdUsuario());
            ps.setDouble(4, v.getTotal());
            ps.setDouble(5, v.getSubtotal());
            ps.setDouble(6, v.getIgv());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Venta buscarPorId(int idVenta) {
        String sql = "SELECT * FROM venta WHERE id_venta=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setFecha(rs.getTimestamp("fecha"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                v.setTotal(rs.getDouble("total"));
                v.setSubtotal(rs.getDouble("subtotal"));
                v.setIgv(rs.getDouble("igv"));
                return v;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrarVentaConDetalles(Venta venta, List<DetalleVenta> detalles) {
        boolean confirmacion = false;
        Connection cn = null;
        PreparedStatement psVenta = null;
        PreparedStatement psDetalle = null;
        try {
            cn = Conexion.getConexion();
            cn.setAutoCommit(false); // iniciar transacción

            // Insertar cabecera de venta
            String sqlVenta = "INSERT INTO venta (fecha, id_cliente, id_usuario, total, subtotal, igv) VALUES (NOW(), ?, ?, ?, ?, ?)";
            psVenta = cn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, venta.getIdCliente());
            psVenta.setInt(2, venta.getIdUsuario());
            psVenta.setDouble(3, venta.getTotal());
            psVenta.setDouble(4, venta.getSubtotal());
            psVenta.setDouble(5, venta.getIgv());
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            int idVenta = 0;
            if (rs.next()) {
                idVenta = rs.getInt(1);
            }

            // Insertar detalles
            String sqlDetalle = "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            psDetalle = cn.prepareStatement(sqlDetalle);

            for (DetalleVenta d : detalles) {
                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, d.getIdProducto());
                psDetalle.setInt(3, d.getCantidad());
                psDetalle.setDouble(4, d.getPrecioUnitario());
                psDetalle.setDouble(5, d.getSubtotal());
                psDetalle.executeUpdate();

                // Actualizar stock
                String sqlStock = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
                try (PreparedStatement psStock = cn.prepareStatement(sqlStock)) {
                    psStock.setInt(1, d.getCantidad());
                    psStock.setInt(2, d.getIdProducto());
                    psStock.executeUpdate();
                }
            }

            cn.commit();
            confirmacion = true;

        } catch (Exception e) {
            try {
                if (cn != null) cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "No se pudo registrar la venta: " + e.getMessage());
        } finally {
            try {
                if (psVenta != null) psVenta.close();
                if (psDetalle != null) psDetalle.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return confirmacion;
    }

    @Override
    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (Connection cn = Conexion.getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta();
                v.setIdVenta(rs.getInt("id_venta"));
                v.setFecha(rs.getTimestamp("fecha"));
                v.setIdCliente(rs.getInt("id_cliente"));
                v.setIdUsuario(rs.getInt("id_usuario"));
                v.setTotal(rs.getDouble("total"));
                v.setSubtotal(rs.getDouble("subtotal"));
                v.setIgv(rs.getDouble("igv"));
                lista.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}