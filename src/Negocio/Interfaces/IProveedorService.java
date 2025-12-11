package Negocio.Interfaces;

import Entidades.Proveedor;

public interface IProveedorService extends IBaseService<Proveedor> {
    Proveedor buscarPorId(int idProveedor);
    Proveedor buscarPorRuc(String ruc);
}

