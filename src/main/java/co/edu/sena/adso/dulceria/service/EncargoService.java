package co.edu.sena.adso.dulceria.service;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
 
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.model.Encargo;
import co.edu.sena.adso.dulceria.repository.EncargoRepository;
 
@Service
public class EncargoService {
 
    private final EncargoRepository repositorio;
 
    public EncargoService(EncargoRepository repositorio) {
        this.repositorio = repositorio;
    }
 
    // Registra un encargo para un dulce
    public Encargo registrar(Dulce dulce, Encargo datos) {
        // REGLA DE NEGOCIO: no se reciben encargos de dulces agotados
        if (!dulce.isDisponible()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, dulce.getNombre() + " está agotado");
        }
        // Se crea un objeto NUEVO solo con los datos permitidos: el cliente no decide el id ni el dulce
        Encargo encargo = new Encargo(dulce, datos.getNombreCliente().trim(), datos.getTelefono(),
                datos.getCantidad(), datos.getFechaEntrega());
        return repositorio.save(encargo);
    }
 
    public List<Encargo> listar() {
        return repositorio.findAllByOrderByCreadoEnDesc();
    }
 
    // Suma del valor de todos los encargos
    public int totalEncargado() {
        return listar().stream().mapToInt(Encargo::getTotal).sum();
    }
}

