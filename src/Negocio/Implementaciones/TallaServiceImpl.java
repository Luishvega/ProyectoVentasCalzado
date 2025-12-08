package Negocio.Implementaciones;

import Datos.TallaDAO;
import Datos.Interfaces.TallaInterface;
import Entidades.Talla;
import Negocio.Interfaces.ITallaService;
import java.util.List;

public class TallaServiceImpl implements ITallaService {
    
    private final TallaInterface tallaDAO;
    
    public TallaServiceImpl() {
        this.tallaDAO = new TallaDAO();
    }
    
    @Override
    public List<Talla> listar() {
        return tallaDAO.listar();
    }
    
    @Override
    public List<Talla> buscar(String filtro) {
        return tallaDAO.listar(filtro != null ? filtro : "");
    }
    
    @Override
    public String buscarEtiquetaPorId(int idTalla) {
        return tallaDAO.buscarPorId(idTalla);
    }
}

