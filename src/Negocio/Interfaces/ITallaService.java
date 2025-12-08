package Negocio.Interfaces;

import Entidades.Talla;
import java.util.List;

public interface ITallaService {
    List<Talla> listar();
    List<Talla> buscar(String filtro);
    String buscarEtiquetaPorId(int idTalla);
}

