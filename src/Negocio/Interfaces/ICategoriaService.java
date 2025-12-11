package Negocio.Interfaces;

import Entidades.Categoria;

public interface ICategoriaService extends IBaseService<Categoria> {
    String buscarNombrePorId(int idCategoria);
}

