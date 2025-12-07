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
 * Implementa el concepto de HERENCIA en la capa de acceso a datos.
 * 
 * Utiliza Generics (T) para permitir que las clases hijas trabajen
 * con diferentes tipos de entidades.
 * 
 * @param <T> Tipo de entidad que maneja el DAO
 * @author Proyecto Calzado
 */
public abstract class BaseDAO<T> {
    
    // Atributos protegidos para uso en clases hijas
    protected Connection cn;
    protected PreparedStatement ps;
    protected ResultSet rs;
    
    /**
     * Método protegido para obtener conexión a la base de datos.
     * Las clases hijas heredan este método y no necesitan repetir el código.
     * @return Conexión a la base de datos
     * @throws Exception Si hay error de conexión
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
     * Método abstracto para insertar un registro - POLIMORFISMO
     * Cada DAO implementará este método según su entidad.
     * @param entidad Objeto a insertar
     * @return true si se insertó correctamente
     */
    public abstract boolean insertar(T entidad);
    
    /**
     * Método abstracto para listar registros - POLIMORFISMO
     * Cada DAO implementará este método según su entidad.
     * @return Lista de entidades
     */
    public abstract List<T> listar();
    
    /**
     * Método abstracto para obtener el nombre de la tabla - POLIMORFISMO
     * Cada DAO retornará el nombre de su tabla correspondiente.
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

