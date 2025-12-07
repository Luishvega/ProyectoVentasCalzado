/*
 * Clase abstracta BaseDAO - Demuestra HERENCIA en la capa de datos
 * Esta clase es padre de todos los DAOs del sistema
 */
package Datos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Clase abstracta genérica que define la estructura base para todos los DAOs.
 * Utiliza Genericos para permitir que las clases hijas trabajen
 * con diferentes tipos de entidades.
 * @param <T> Tipo de entidad que maneja el DAO
 */
public abstract class BaseDAO<T> {
    
    // Atributos protegidos para uso en clases hijas
    protected Connection cn;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    /**
     * Método protegido para obtener conexión a la base de datos.
     * @return Conexión a la base de datos
     */
    protected Connection getConexion() throws Exception {
        return Conexion.getConexion();
    }
    
    /**
     * Método protegido para cerrar recursos de base de datos.
     * Evita la repetición de código en cada DAO.
     * @param cn Conexión a cerrar
     * @param ps PreparedStatement a cerrar
     * @param rs ResultSet a cerrar
     */
    protected void cerrarRecursos(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (cn != null) cn.close();
        } catch (Exception e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
    
    /**
     * Método protegido para cerrar recursos sin ResultSet.
     * @param cn Conexión a cerrar
     * @param ps PreparedStatement a cerrar
     */
    protected void cerrarRecursos(Connection cn, PreparedStatement ps) {
        cerrarRecursos(cn, ps, null);
    }
    
    /**
     * Método abstracto para insertar un registro
     * @param entidad Objeto a insertar
     * @return true si se insertó correctamente
     */
    public abstract boolean insertar(T entidad);
    
    /**
     * Método abstracto para listar registros
     * @return Lista de entidades
     */
    public abstract List<T> listar();
    
    /**
     * Método abstracto para obtener el nombre de la tabla
     * @return Nombre de la tabla en la base de datos
     */
    public abstract String getNombreTabla();
    
    /**
     * Método que muestra información del DAO.
     * Demuestra cómo los métodos heredados pueden usar métodos abstractos.
     */
    public void mostrarInfo() {
        System.out.println("DAO para tabla: " + getNombreTabla());
    }
}

