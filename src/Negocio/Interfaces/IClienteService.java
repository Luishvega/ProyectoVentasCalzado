package Negocio.Interfaces;

import Entidades.Cliente;
import Negocio.NegocioException;
import java.util.List;
import java.util.Map;

public interface IClienteService {
    boolean insertar(Cliente c) throws NegocioException;
    boolean actualizar(Cliente c) throws NegocioException;
    boolean desactivar(int idCliente) throws NegocioException;
    Cliente buscarPorId(int idCliente);
    Cliente buscarPorDocumento(String documento);
    List<Cliente> buscar(String filtro);
    Map<Integer, String> listarMapa();
}

