package Negocio.Implementaciones;

import Datos.RolDAO;
import Datos.Interfaces.RolInterface;
import Entidades.Rol;
import Negocio.Interfaces.IRolService;
import Negocio.NegocioException;
import java.util.List;

public class RolServiceImpl implements IRolService {
    
    private final RolInterface rolDAO;
    
    public RolServiceImpl() {
        this.rolDAO = new RolDAO();
    }
    
    @Override
    public boolean insertar(Rol r) throws NegocioException {
        validarRol(r);
        try {
            return rolDAO.insertar(r);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar rol", e);
        }
    }
    
    @Override
    public boolean actualizar(Rol r) throws NegocioException {
        validarRol(r);
        if (r.getIdRol() <= 0) {
            throw new NegocioException("ID de rol inválido");
        }
        try {
            return rolDAO.actualizar(r);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar rol", e);
        }
    }
    
    @Override
    public boolean eliminar(int idRol) throws NegocioException {
        if (idRol <= 0) {
            throw new NegocioException("ID de rol inválido");
        }
        try {
            return rolDAO.eliminar(idRol);
        } catch (Exception e) {
            throw new NegocioException("Error al eliminar rol", e);
        }
    }
    
    @Override
    public List<Rol> listar() {
        return rolDAO.listar();
    }
    
    private void validarRol(Rol r) throws NegocioException {
        if (r == null) {
            throw new NegocioException("El rol no puede ser nulo");
        }
        if (r.getNombre() == null || r.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del rol es obligatorio");
        }
        if (r.getNombre().length() > 50) {
            throw new NegocioException("El nombre del rol no puede exceder 50 caracteres");
        }
    }
}

