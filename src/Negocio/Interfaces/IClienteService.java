package Negocio.Interfaces;

import Entidades.Cliente;
import java.util.Map;

public interface IClienteService extends IBaseService<Cliente> {
    Cliente buscarPorId(int idCliente);
    Cliente buscarPorDocumento(String documento);
    Map<Integer, String> listarMapa();
}

