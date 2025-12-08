package Negocio.Interfaces;

import Entidades.Rol;
import Negocio.NegocioException;
import java.util.List;

public interface IRolService {
    boolean insertar(Rol r) throws NegocioException;
    boolean actualizar(Rol r) throws NegocioException;
    boolean eliminar(int idRol) throws NegocioException;
    List<Rol> listar();
}

