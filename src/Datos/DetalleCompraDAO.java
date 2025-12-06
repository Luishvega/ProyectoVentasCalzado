/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.Conexion;
import Entidades.DetalleCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author macbook
 */


public class DetalleCompraDAO {

    // Insertar detalle de compra
    public boolean insertar(DetalleCompra d) {
        boolean resp = false;
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, d.getIdCompra());
            ps.setInt(2, d.getIdProducto());
            ps.setInt(3, d.getCantidad());
            ps.setDouble(4, d.getPrecioUnitario());
            ps.setDouble(5, d.getSubtotal());
            resp = ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar detalle de compra: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return resp;
    }

    // Listar detalles por compra
    public List<DetalleCompra> listarPorCompra(int idCompra) {
        List<DetalleCompra> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = Conexion.getConexion();
            String sql = "SELECT * FROM detalle_compra WHERE id_compra=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idCompra);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleCompra d = new DetalleCompra();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdCompra(rs.getInt("id_compra"));
                d.setIdProducto(rs.getInt("id_producto"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                d.setSubtotal(rs.getDouble("subtotal"));
                lista.add(d);
            }
        } catch (Exception e) {
            System.out.println("Error al listar detalle de compra: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); if (cn != null) cn.close(); } catch (Exception ex) {}
        }
        return lista;
    }
}
