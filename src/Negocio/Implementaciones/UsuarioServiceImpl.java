package Negocio.Implementaciones;

import Datos.UsuarioDAO;
import Datos.Interfaces.UsuarioInterface;
import Entidades.Usuario;
import Negocio.Interfaces.IUsuarioService;
import Negocio.NegocioException;
import java.util.List;

public class UsuarioServiceImpl implements IUsuarioService {
    
    private final UsuarioInterface usuarioDAO;
    
    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    @Override
    public boolean insertar(Usuario u) throws NegocioException {
        validarUsuario(u);
        validarNombreUsuarioDuplicado(u.getNombreUsuario(), 0);
        try {
            return usuarioDAO.insertar(u);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar usuario", e);
        }
    }
    
    @Override
    public boolean actualizar(Usuario u) throws NegocioException {
        validarUsuario(u);
        if (u.getIdUsuario() <= 0) {
            throw new NegocioException("ID de usuario inválido");
        }
        validarNombreUsuarioDuplicado(u.getNombreUsuario(), u.getIdUsuario());
        try {
            return usuarioDAO.actualizar(u);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar usuario", e);
        }
    }
    
    @Override
    public boolean desactivar(int idUsuario) throws NegocioException {
        if (idUsuario <= 0) {
            throw new NegocioException("ID de usuario inválido");
        }
        try {
            return usuarioDAO.desactivar(idUsuario);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar usuario", e);
        }
    }
    
    @Override
    public Usuario autenticar(String nombreUsuario, String contrasenia) throws NegocioException {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new NegocioException("El nombre de usuario es obligatorio");
        }
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria");
        }
        
        Usuario usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
        if (usuario == null) {
            throw new NegocioException("Usuario o contraseña incorrectos");
        }
        if (usuario.getEstado() != 1) {
            throw new NegocioException("Usuario inactivo");
        }
        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new NegocioException("Usuario o contraseña incorrectos");
        }
        
        return usuario;
    }
    
    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
    }
    
    @Override
    public List<Usuario> buscar(String filtro) {
        return usuarioDAO.listar(filtro != null ? filtro : "");
    }
    
    private void validarUsuario(Usuario u) throws NegocioException {
        if (u == null) {
            throw new NegocioException("El usuario no puede ser nulo");
        }
        if (u.getNombreUsuario() == null || u.getNombreUsuario().trim().isEmpty()) {
            throw new NegocioException("El nombre de usuario es obligatorio");
        }
        if (u.getNombreUsuario().length() < 4) {
            throw new NegocioException("El nombre de usuario debe tener al menos 4 caracteres");
        }
        if (u.getContrasenia() == null || u.getContrasenia().trim().isEmpty()) {
            throw new NegocioException("La contraseña es obligatoria");
        }
        if (u.getContrasenia().length() < 4) {
            throw new NegocioException("La contraseña debe tener al menos 4 caracteres");
        }
        if (u.getIdRol() <= 0) {
            throw new NegocioException("Debe seleccionar un rol");
        }
    }
    
    private void validarNombreUsuarioDuplicado(String nombreUsuario, int idUsuarioActual) throws NegocioException {
        Usuario existente = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
        if (existente != null && existente.getIdUsuario() != idUsuarioActual) {
            throw new NegocioException("Ya existe un usuario con el nombre " + nombreUsuario);
        }
    }
}

