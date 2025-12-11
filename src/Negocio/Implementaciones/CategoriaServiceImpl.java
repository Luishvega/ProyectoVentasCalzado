package Negocio.Implementaciones;

import Datos.CategoriaDAO;
import Datos.Interfaces.CategoriaInterface;
import Entidades.Categoria;
import Negocio.Interfaces.ICategoriaService;
import Negocio.NegocioException;
import java.util.List;

public class CategoriaServiceImpl extends BaseServiceImpl<Categoria> implements ICategoriaService {
    
    private final CategoriaInterface categoriaDAO;
    
    public CategoriaServiceImpl() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    @Override
    protected void validar(Categoria c) throws NegocioException {
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
    
    @Override
    protected boolean ejecutarInsertar(Categoria c) {
        return categoriaDAO.insertar(c);
    }
    
    @Override
    protected boolean ejecutarActualizar(Categoria c) {
        if (c.getIdCategoria() <= 0) {
            throw new RuntimeException("ID de categoría inválido");
        }
        return categoriaDAO.actualizar(c);
    }
    
    @Override
    protected boolean ejecutarDesactivar(int id) {
        return categoriaDAO.desactivar(id);
    }
    
    @Override
    protected List<Categoria> ejecutarBuscar(String filtro) {
        return categoriaDAO.listar(filtro);
    }
    
    @Override
    public String buscarNombrePorId(int idCategoria) {
        return categoriaDAO.buscarPorId(idCategoria);
    }
}

