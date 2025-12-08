package Negocio.Interfaces;

import Entidades.Marca;
import Negocio.NegocioException;
import java.util.List;

public interface IMarcaService {
    boolean insertar(Marca m) throws NegocioException;
    boolean actualizar(Marca m) throws NegocioException;
    boolean desactivar(int idMarca) throws NegocioException;
    List<Marca> listar();
    List<Marca> listarTodas();
    String buscarNombrePorId(int idMarca);
}

