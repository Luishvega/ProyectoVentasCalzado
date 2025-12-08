package Negocio.Implementaciones;

import Datos.CompraDAO;
import Datos.Interfaces.CompraInterface;
import Entidades.Compra;
import Negocio.Interfaces.ICompraService;
import Negocio.NegocioException;
import java.util.List;

public class CompraServiceImpl implements ICompraService {
    
    private final CompraInterface compraDAO;
    
    public CompraServiceImpl() {
        this.compraDAO = new CompraDAO();
    }
    
    @Override
    public boolean insertar(Compra c) throws NegocioException {
        validarCompra(c);
        try {
            return compraDAO.insertar(c);
        } catch (Exception e) {
            throw new NegocioException("Error al insertar compra", e);
        }
    }
    
    @Override
    public Compra buscarPorId(int idCompra) {
        return compraDAO.buscarPorId(idCompra);
    }
    
    @Override
    public List<Compra> listar() {
        return compraDAO.listar();
    }
    
    private void validarCompra(Compra c) throws NegocioException {
        if (c == null) {
            throw new NegocioException("La compra no puede ser nula");
        }
        if (c.getIdProveedor() <= 0) {
            throw new NegocioException("Debe seleccionar un proveedor");
        }
        if (c.getIdUsuario() <= 0) {
            throw new NegocioException("Usuario inválido");
        }
        if (c.getTotal() <= 0) {
            throw new NegocioException("El total debe ser mayor a cero");
        }
    }
}

