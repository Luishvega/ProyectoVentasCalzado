package Negocio.Implementaciones;

import Datos.ProductoDAO;
import Datos.Interfaces.ProductoInterface;
import Entidades.Producto;
import Negocio.Interfaces.IProductoService;
import Negocio.NegocioException;
import java.util.List;

public class ProductoServiceImpl implements IProductoService {
    
    private final ProductoInterface productoDAO;
    
    public ProductoServiceImpl() {
        this.productoDAO = new ProductoDAO();
    }
    
    @Override
    public boolean insertar(Producto p) throws NegocioException {
        validarProducto(p);
        try {
            return productoDAO.insertar(p);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar producto", e);
        }
    }
    
    @Override
    public boolean actualizar(Producto p) throws NegocioException {
        validarProducto(p);
        if (p.getIdProducto() <= 0) {
            throw new NegocioException("ID de producto inválido");
        }
        try {
            return productoDAO.actualizar(p);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar producto", e);
        }
    }
    
    @Override
    public boolean desactivar(int idProducto) throws NegocioException {
        if (idProducto <= 0) {
            throw new NegocioException("ID de producto inválido");
        }
        try {
            return productoDAO.desactivar(idProducto);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar producto", e);
        }
    }
    
    @Override
    public List<Producto> buscar(String filtro) {
        return productoDAO.listar(filtro != null ? filtro : "");
    }
    
    @Override
    public Producto buscarPorId(int idProducto) {
        return productoDAO.buscarPorId(idProducto);
    }
    
    @Override
    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return productoDAO.buscarPorCodigoBarras(codigoBarras);
    }
    
    @Override
    public int obtenerUltimoCodigo() {
        return productoDAO.obtenerUltimoCodigo();
    }
    
    @Override
    public boolean actualizarStock(int idProducto, int cantidad) throws NegocioException {
        if (idProducto <= 0) {
            throw new NegocioException("ID de producto inválido");
        }
        if (cantidad <= 0) {
            throw new NegocioException("La cantidad debe ser mayor a cero");
        }
        try {
            return productoDAO.actualizarStock(idProducto, cantidad);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar stock", e);
        }
    }
    
    @Override
    public boolean verificarStock(int idProducto, int cantidadRequerida) throws NegocioException {
        Producto p = buscarPorId(idProducto);
        if (p == null) {
            throw new NegocioException("Producto no encontrado");
        }
        if (p.getStock() < cantidadRequerida) {
            throw new NegocioException("Stock insuficiente. Disponible: " + p.getStock());
        }
        return true;
    }
    
    private void validarProducto(Producto p) throws NegocioException {
        if (p == null) {
            throw new NegocioException("El producto no puede ser nulo");
        }
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del producto es obligatorio");
        }
        if (p.getNombre().length() > 200) {
            throw new NegocioException("El nombre del producto no puede exceder 200 caracteres");
        }
        if (p.getPrecio() <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero");
        }
        if (p.getStock() < 0) {
            throw new NegocioException("El stock no puede ser negativo");
        }
        if (p.getIdCategoria() <= 0) {
            throw new NegocioException("Debe seleccionar una categoría");
        }
        if (p.getIdmarca() <= 0) {
            throw new NegocioException("Debe seleccionar una marca");
        }
        if (p.getIdtalla() <= 0) {
            throw new NegocioException("Debe seleccionar una talla");
        }
    }
}

