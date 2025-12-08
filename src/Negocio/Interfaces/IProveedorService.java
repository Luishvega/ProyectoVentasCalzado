package Negocio.Interfaces;

import Entidades.Proveedor;
import Negocio.NegocioException;
import java.util.List;

public interface IProveedorService {
    boolean insertar(Proveedor p) throws NegocioException;
    boolean actualizar(Proveedor p) throws NegocioException;
    boolean desactivar(int idProveedor) throws NegocioException;
    Proveedor buscarPorId(int idProveedor);
    Proveedor buscarPorRuc(String ruc);
    List<Proveedor> buscar(String filtro);
}

