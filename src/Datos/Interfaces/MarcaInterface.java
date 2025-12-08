package Datos.Interfaces;

import Entidades.Marca;
import java.util.List;

public interface MarcaInterface {
    boolean insertar(Marca m);
    boolean actualizar(Marca m);
    boolean desactivar(int idMarca);
    List<Marca> listar();
    List<Marca> listarTodas();
    String buscarPorId(int idMarca);
}

