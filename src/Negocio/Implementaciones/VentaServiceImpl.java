package Negocio.Implementaciones;

import Datos.VentaDAO;
import Entidades.DetalleVenta;
import Entidades.Venta;
import Negocio.Interfaces.IVentaService;
import Negocio.Interfaces.IProductoService;
import Negocio.NegocioException;
import java.util.Date;
import java.util.List;

public class VentaServiceImpl implements IVentaService {
    
    private final VentaDAO ventaDAO;
    private final IProductoService productoService;
    
    public VentaServiceImpl() {
        this.ventaDAO = new VentaDAO();
        this.productoService = new ProductoServiceImpl();
    }
    
    @Override
    public boolean registrarVentaCompleta(Venta venta, List<DetalleVenta> detalles) throws NegocioException {
        validarVenta(venta);
        validarDetalles(detalles);
        verificarStockProductos(detalles);
        calcularTotales(venta, detalles);
        
        try {
            return ventaDAO.registrarVentaConDetalles(venta, detalles);
        } catch (Exception e) {
            throw new NegocioException("Error al registrar la venta", e);
        }
    }
    
    @Override
    public Venta buscarPorId(int idVenta) {
        return ventaDAO.buscarPorId(idVenta);
    }
    
    @Override
    public List<Venta> listar() {
        return ventaDAO.listar();
    }
    
    @Override
    public List<Venta> listarPorFecha(Date fechaInicio, Date fechaFin) {
        return ventaDAO.listarPorFecha(fechaInicio, fechaFin);
    }
    
    @Override
    public String obtenerNombreCliente(int idCliente) {
        return ventaDAO.obtenerNombreCliente(idCliente);
    }
    
    @Override
    public String obtenerNombreUsuario(int idUsuario) {
        return ventaDAO.obtenerNombreUsuario(idUsuario);
    }
    
    private void validarVenta(Venta v) throws NegocioException {
        if (v == null) {
            throw new NegocioException("La venta no puede ser nula");
        }
        if (v.getIdCliente() <= 0) {
            throw new NegocioException("Debe seleccionar un cliente");
        }
        if (v.getIdUsuario() <= 0) {
            throw new NegocioException("Usuario inválido");
        }
    }
    
    private void validarDetalles(List<DetalleVenta> detalles) throws NegocioException {
        if (detalles == null || detalles.isEmpty()) {
            throw new NegocioException("La venta debe tener al menos un producto");
        }
        
        for (DetalleVenta d : detalles) {
            if (d.getIdProducto() <= 0) {
                throw new NegocioException("Producto inválido en el detalle");
            }
            if (d.getCantidad() <= 0) {
                throw new NegocioException("La cantidad debe ser mayor a cero");
            }
            if (d.getPrecioUnitario() <= 0) {
                throw new NegocioException("El precio unitario debe ser mayor a cero");
            }
        }
    }
    
    private void verificarStockProductos(List<DetalleVenta> detalles) throws NegocioException {
        for (DetalleVenta d : detalles) {
            productoService.verificarStock(d.getIdProducto(), d.getCantidad());
        }
    }
    
    private void calcularTotales(Venta venta, List<DetalleVenta> detalles) {
        double subtotal = 0;
        for (DetalleVenta d : detalles) {
            d.setSubtotal(d.getCantidad() * d.getPrecioUnitario());
            subtotal += d.getSubtotal();
        }
        
        venta.setSubtotal(subtotal);
        venta.setIgv(subtotal * 0.18);
        venta.setTotal(subtotal + venta.getIgv());
    }
}

