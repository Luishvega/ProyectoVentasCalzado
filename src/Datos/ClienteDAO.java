/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Conexion.Conexion;
import Datos.Interfaces.ClienteInterface;
import Entidades.Cliente;
import java.sql.*;
import java.util.*;


public class ClienteDAO implements ClienteInterface {

    @Override
    public boolean insertar(Cliente c) {
        String sql = "INSERT INTO cliente(nombres, apellidos, dni, direccion, telefono, estado) VALUES(?,?,?,?,?,?)";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getTelefono());
            ps.setInt(6, c.getEstado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean actualizar(Cliente c) {
        String sql = "UPDATE cliente SET nombres=?, apellidos=?, dni=?, direccion=?, telefono=?, estado=? WHERE id_cliente=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, c.getNombres());
            ps.setString(2, c.getApellidos());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getTelefono());
            ps.setInt(6, c.getEstado());
            ps.setInt(7, c.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente=?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setDni(rs.getString("dni"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setEstado(rs.getInt("estado"));
                return c;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Cliente> listar(String filtro) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nombres LIKE ? OR apellidos LIKE ?";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            ps.setString(2, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombres(rs.getString("nombres"));
                c.setApellidos(rs.getString("apellidos"));
                c.setDni(rs.getString("dni"));
                c.setDireccion(rs.getString("direccion"));
                c.setTelefono(rs.getString("telefono"));
                c.setEstado(rs.getInt("estado"));
                lista.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }
    
    
public Map<Integer, String> listarMapa() {
        Map<Integer, String> mapa = new LinkedHashMap<>();
        String sql = "SELECT id_cliente, CONCAT(nombres, ' ', apellidos) AS nombreCompleto FROM cliente WHERE estado = 1 ORDER BY nombres";
        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                mapa.put(rs.getInt("id_cliente"), rs.getString("nombreCompleto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa;
}
}

