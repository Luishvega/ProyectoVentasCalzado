package Datos.Interfaces;

import Entidades.Talla;
import java.util.List;

public interface TallaInterface {
    List<Talla> listar();
    List<Talla> listar(String filtro);
    String buscarPorId(int idTalla);
}

