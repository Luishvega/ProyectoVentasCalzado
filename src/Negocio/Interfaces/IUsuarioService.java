package Negocio.Interfaces;

import Entidades.Usuario;
import Negocio.NegocioException;
import java.util.List;

public interface IUsuarioService {
    boolean insertar(Usuario u) throws NegocioException;
    boolean actualizar(Usuario u) throws NegocioException;
    boolean desactivar(int idUsuario) throws NegocioException;
    Usuario autenticar(String nombreUsuario, String contrasenia) throws NegocioException;
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    List<Usuario> buscar(String filtro);
}

