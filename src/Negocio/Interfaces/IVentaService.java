package Negocio.Interfaces;

import Entidades.Venta;
import Entidades.DetalleVenta;
import Negocio.NegocioException;
import java.util.Date;
import java.util.List;

public interface IVentaService {
    boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles) throws NegocioException;
    Venta buscarPorId(int idVenta);
    List<Venta> listar();
    List<Venta> listarPorFecha(Date fechaInicio, Date fechaFin);
    String obtenerNombreCliente(int idCliente);
    String obtenerNombreUsuario(int idUsuario);
}

