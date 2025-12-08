package Negocio.Implementaciones;

import Datos.ClienteDAO;
import Datos.Interfaces.ClienteInterface;
import Entidades.Cliente;
import Negocio.Interfaces.IClienteService;
import Negocio.NegocioException;
import java.util.List;
import java.util.Map;

public class ClienteServiceImpl implements IClienteService {
    
    private final ClienteDAO clienteDAO;
    
    public ClienteServiceImpl() {
        this.clienteDAO = new ClienteDAO();
    }
    
    @Override
    public boolean insertar(Cliente c) throws NegocioException {
        validarCliente(c);
        validarDocumentoDuplicado(c.getDni(), 0);
        try {
            return clienteDAO.insertar(c);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar cliente", e);
        }
    }
    
    @Override
    public boolean actualizar(Cliente c) throws NegocioException {
        validarCliente(c);
        if (c.getIdCliente() <= 0) {
            throw new NegocioException("ID de cliente inválido");
        }
        validarDocumentoDuplicado(c.getDni(), c.getIdCliente());
        try {
            return clienteDAO.actualizar(c);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar cliente", e);
        }
    }
    
    @Override
    public boolean desactivar(int idCliente) throws NegocioException {
        if (idCliente <= 0) {
            throw new NegocioException("ID de cliente inválido");
        }
        try {
            return clienteDAO.desactivar(idCliente);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar cliente", e);
        }
    }
    
    @Override
    public Cliente buscarPorId(int idCliente) {
        return clienteDAO.buscarPorId(idCliente);
    }
    
    @Override
    public Cliente buscarPorDocumento(String documento) {
        return clienteDAO.buscarPorDocumento(documento);
    }
    
    @Override
    public List<Cliente> buscar(String filtro) {
        return clienteDAO.listar(filtro != null ? filtro : "");
    }
    
    @Override
    public Map<Integer, String> listarMapa() {
        return clienteDAO.listarMapa();
    }
    
    private void validarCliente(Cliente c) throws NegocioException {
        if (c == null) {
            throw new NegocioException("El cliente no puede ser nulo");
        }
        if (c.getNombres() == null || c.getNombres().trim().isEmpty()) {
            throw new NegocioException("Los nombres son obligatorios");
        }
        if (c.getApellidos() == null || c.getApellidos().trim().isEmpty()) {
            throw new NegocioException("Los apellidos son obligatorios");
        }
        if (c.getDni() == null || c.getDni().trim().isEmpty()) {
            throw new NegocioException("El DNI es obligatorio");
        }
        if (!c.getDni().matches("\\d{8}")) {
            throw new NegocioException("El DNI debe contener 8 dígitos");
        }
        if (c.getTelefono() != null && !c.getTelefono().trim().isEmpty()) {
            if (!c.getTelefono().matches("\\d{9}")) {
                throw new NegocioException("El teléfono debe contener 9 dígitos");
            }
        }
    }
    
    private void validarDocumentoDuplicado(String dni, int idClienteActual) throws NegocioException {
        Cliente existente = clienteDAO.buscarPorDocumento(dni);
        if (existente != null && existente.getIdCliente() != idClienteActual) {
            throw new NegocioException("Ya existe un cliente con el DNI " + dni);
        }
    }
}

