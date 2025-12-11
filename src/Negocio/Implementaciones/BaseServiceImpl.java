package Negocio.Implementaciones;

import Negocio.Interfaces.IBaseService;
import Negocio.NegocioException;
import java.util.List;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    
    @Override
    public boolean insertar(T entidad) throws NegocioException {
        validar(entidad);
        try {
            return ejecutarInsertar(entidad);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar", e);
        }
    }
    
    @Override
    public boolean actualizar(T entidad) throws NegocioException {
        validar(entidad);
        try {
            return ejecutarActualizar(entidad);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar", e);
        }
    }
    
    @Override
    public boolean desactivar(int id) throws NegocioException {
        if (id <= 0) {
            throw new NegocioException("ID inválido");
        }
        try {
            return ejecutarDesactivar(id);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar", e);
        }
    }
    
    @Override
    public List<T> buscar(String filtro) {
        return ejecutarBuscar(filtro != null ? filtro : "");
    }
    
    protected abstract void validar(T entidad) throws NegocioException;
    protected abstract boolean ejecutarInsertar(T entidad);
    protected abstract boolean ejecutarActualizar(T entidad);
    protected abstract boolean ejecutarDesactivar(int id);
    protected abstract List<T> ejecutarBuscar(String filtro);
}

