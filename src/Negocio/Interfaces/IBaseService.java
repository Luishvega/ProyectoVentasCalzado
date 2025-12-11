package Negocio.Interfaces;

import Negocio.NegocioException;
import java.util.List;

public interface IBaseService<T> {
    boolean insertar(T entidad) throws NegocioException;
    boolean actualizar(T entidad) throws NegocioException;
    boolean desactivar(int id) throws NegocioException;
    List<T> buscar(String filtro);
}

