package Negocio.Interfaces;

import Entidades.Producto;
import Negocio.NegocioException;

public interface IProductoService extends IBaseService<Producto> {
    Producto buscarPorId(int idProducto);
    Producto buscarPorCodigoBarras(String codigoBarras);
    int obtenerUltimoCodigo();
    boolean actualizarStock(int idProducto, int cantidad) throws NegocioException;
    boolean verificarStock(int idProducto, int cantidadRequerida) throws NegocioException;
}

