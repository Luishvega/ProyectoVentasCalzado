package Negocio.Interfaces;

import Entidades.Categoria;
import Negocio.NegocioException;
import java.util.List;

public interface ICategoriaService {
    boolean insertar(Categoria c) throws NegocioException;
    boolean actualizar(Categoria c) throws NegocioException;
    boolean desactivar(int idCategoria) throws NegocioException;
    List<Categoria> listar();
    List<Categoria> listarTodas();
    List<Categoria> buscar(String filtro);
    String buscarNombrePorId(int idCategoria);
}

