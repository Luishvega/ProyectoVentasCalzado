package Negocio.Implementaciones;

import Datos.ProveedorDAO;
import Datos.Interfaces.ProveedorInterface;
import Entidades.Proveedor;
import Negocio.Interfaces.IProveedorService;
import Negocio.NegocioException;
import java.util.List;

public class ProveedorServiceImpl implements IProveedorService {
    
    private final ProveedorInterface proveedorDAO;
    
    public ProveedorServiceImpl() {
        this.proveedorDAO = new ProveedorDAO();
    }
    
    @Override
    public boolean insertar(Proveedor p) throws NegocioException {
        validarProveedor(p);
        validarRucDuplicado(p.getRuc(), 0);
        try {
            return proveedorDAO.insertar(p);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar proveedor", e);
        }
    }
    
    @Override
    public boolean actualizar(Proveedor p) throws NegocioException {
        validarProveedor(p);
        if (p.getIdProveedor() <= 0) {
            throw new NegocioException("ID de proveedor inválido");
        }
        validarRucDuplicado(p.getRuc(), p.getIdProveedor());
        try {
            return proveedorDAO.actualizar(p);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar proveedor", e);
        }
    }
    
    @Override
    public boolean desactivar(int idProveedor) throws NegocioException {
        if (idProveedor <= 0) {
            throw new NegocioException("ID de proveedor inválido");
        }
        try {
            return proveedorDAO.desactivar(idProveedor);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar proveedor", e);
        }
    }
    
    @Override
    public Proveedor buscarPorId(int idProveedor) {
        return proveedorDAO.buscarPorId(idProveedor);
    }
    
    @Override
    public Proveedor buscarPorRuc(String ruc) {
        return proveedorDAO.buscarPorRuc(ruc);
    }
    
    @Override
    public List<Proveedor> buscar(String filtro) {
        return proveedorDAO.listar(filtro != null ? filtro : "");
    }
    
    private void validarProveedor(Proveedor p) throws NegocioException {
        if (p == null) {
            throw new NegocioException("El proveedor no puede ser nulo");
        }
        if (p.getRazonSocial() == null || p.getRazonSocial().trim().isEmpty()) {
            throw new NegocioException("La razón social es obligatoria");
        }
        if (p.getRuc() == null || p.getRuc().trim().isEmpty()) {
            throw new NegocioException("El RUC es obligatorio");
        }
        if (!p.getRuc().matches("\\d{11}")) {
            throw new NegocioException("El RUC debe contener 11 dígitos");
        }
        if (p.getTelefono() != null && !p.getTelefono().trim().isEmpty()) {
            if (!p.getTelefono().matches("\\d{9}")) {
                throw new NegocioException("El teléfono debe contener 9 dígitos");
            }
        }
    }
    
    private void validarRucDuplicado(String ruc, int idProveedorActual) throws NegocioException {
        Proveedor existente = proveedorDAO.buscarPorRuc(ruc);
        if (existente != null && existente.getIdProveedor() != idProveedorActual) {
            throw new NegocioException("Ya existe un proveedor con el RUC " + ruc);
        }
    }
}

