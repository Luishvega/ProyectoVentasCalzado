package Negocio.Interfaces;

import Entidades.Producto;
import Negocio.NegocioException;
import java.util.List;

public interface IProductoService {
    boolean insertar(Producto p) throws NegocioException;
    boolean actualizar(Producto p) throws NegocioException;
    boolean desactivar(int idProducto) throws NegocioException;
    List<Producto> buscar(String filtro);
    Producto buscarPorId(int idProducto);
    Producto buscarPorCodigoBarras(String codigoBarras);
    int obtenerUltimoCodigo();
    boolean actualizarStock(int idProducto, int cantidad) throws NegocioException;
    boolean verificarStock(int idProducto, int cantidadRequerida) throws NegocioException;
}

