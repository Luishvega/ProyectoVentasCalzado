package Negocio.Interfaces;

import Entidades.Marca;

public interface IMarcaService extends IBaseService<Marca> {
    String buscarNombrePorId(int idMarca);
}

