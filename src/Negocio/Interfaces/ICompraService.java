package Negocio.Interfaces;

import Entidades.Compra;
import Negocio.NegocioException;
import java.util.List;

public interface ICompraService {
    boolean insertar(Compra c) throws NegocioException;
    Compra buscarPorId(int idCompra);
    List<Compra> listar();
}

