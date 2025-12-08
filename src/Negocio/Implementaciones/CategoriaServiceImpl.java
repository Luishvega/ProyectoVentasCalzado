package Negocio.Implementaciones;

import Datos.CategoriaDAO;
import Datos.Interfaces.CategoriaInterface;
import Entidades.Categoria;
import Negocio.Interfaces.ICategoriaService;
import Negocio.NegocioException;
import java.util.List;

public class CategoriaServiceImpl implements ICategoriaService {
    
    private final CategoriaInterface categoriaDAO;
    
    public CategoriaServiceImpl() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    @Override
    public boolean insertar(Categoria c) throws NegocioException {
        validarCategoria(c);
        try {
            return categoriaDAO.insertar(c);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar categoría", e);
        }
    }
    
    @Override
    public boolean actualizar(Categoria c) throws NegocioException {
        validarCategoria(c);
        if (c.getIdCategoria() <= 0) {
            throw new NegocioException("ID de categoría inválido");
        }
        try {
            return categoriaDAO.actualizar(c);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar categoría", e);
        }
    }
    
    @Override
    public boolean desactivar(int idCategoria) throws NegocioException {
        if (idCategoria <= 0) {
            throw new NegocioException("ID de categoría inválido");
        }
        try {
            return categoriaDAO.desactivar(idCategoria);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar categoría", e);
        }
    }
    
    @Override
    public List<Categoria> listar() {
        return categoriaDAO.listar();
    }
    
    @Override
    public List<Categoria> listarTodas() {
        return categoriaDAO.listarTodas();
    }
    
    @Override
    public List<Categoria> buscar(String filtro) {
        return categoriaDAO.listar(filtro != null ? filtro : "");
    }
    
    @Override
    public String buscarNombrePorId(int idCategoria) {
        return categoriaDAO.buscarPorId(idCategoria);
    }
    
    private void validarCategoria(Categoria c) throws NegocioException {
        if (c == null) {
            throw new NegocioException("La categoría no puede ser nula");
        }
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre de la categoría es obligatorio");
        }
        if (c.getNombre().length() > 100) {
            throw new NegocioException("El nombre de la categoría no puede exceder 100 caracteres");
        }
    }
}

