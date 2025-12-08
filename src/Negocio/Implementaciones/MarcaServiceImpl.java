package Negocio.Implementaciones;

import Datos.MarcaDAO;
import Datos.Interfaces.MarcaInterface;
import Entidades.Marca;
import Negocio.Interfaces.IMarcaService;
import Negocio.NegocioException;
import java.util.List;

public class MarcaServiceImpl implements IMarcaService {
    
    private final MarcaInterface marcaDAO;
    
    public MarcaServiceImpl() {
        this.marcaDAO = new MarcaDAO();
    }
    
    @Override
    public boolean insertar(Marca m) throws NegocioException {
        validarMarca(m);
        try {
            return marcaDAO.insertar(m);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar marca", e);
        }
    }
    
    @Override
    public boolean actualizar(Marca m) throws NegocioException {
        validarMarca(m);
        if (m.getIdMarca() <= 0) {
            throw new NegocioException("ID de marca inválido");
        }
        try {
            return marcaDAO.actualizar(m);
        } catch (Exception e) {
            throw new NegocioException("Error al actualizar marca", e);
        }
    }
    
    @Override
    public boolean desactivar(int idMarca) throws NegocioException {
        if (idMarca <= 0) {
            throw new NegocioException("ID de marca inválido");
        }
        try {
            return marcaDAO.desactivar(idMarca);
        } catch (Exception e) {
            throw new NegocioException("Error al desactivar marca", e);
        }
    }
    
    @Override
    public List<Marca> listar() {
        return marcaDAO.listar();
    }
    
    @Override
    public List<Marca> listarTodas() {
        return marcaDAO.listarTodas();
    }
    
    @Override
    public String buscarNombrePorId(int idMarca) {
        return marcaDAO.buscarPorId(idMarca);
    }
    
    private void validarMarca(Marca m) throws NegocioException {
        if (m == null) {
            throw new NegocioException("La marca no puede ser nula");
        }
        if (m.getNombre() == null || m.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre de la marca es obligatorio");
        }
        if (m.getNombre().length() > 100) {
            throw new NegocioException("El nombre de la marca no puede exceder 100 caracteres");
        }
    }
}

