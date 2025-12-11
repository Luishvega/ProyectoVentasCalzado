package Negocio.Interfaces;

import Entidades.Usuario;
import Negocio.NegocioException;

public interface IUsuarioService extends IBaseService<Usuario> {
    Usuario autenticar(String nombreUsuario, String contrasenia) throws NegocioException;
    Usuario buscarPorNombreUsuario(String nombreUsuario);
}

