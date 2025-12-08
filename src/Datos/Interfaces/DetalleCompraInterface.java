package Datos.Interfaces;

import Entidades.DetalleCompra;
import java.util.List;

public interface DetalleCompraInterface {
    boolean insertar(DetalleCompra d);
    List<DetalleCompra> listarPorCompra(int idCompra);
}

