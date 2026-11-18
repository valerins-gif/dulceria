package co.edu.sena.adso.dulceria.service;
 
import java.util.Comparator;
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.repository.DulceRepository;
 
@Service   // Capa de NEGOCIO: aquí van las reglas de la tienda
public class DulceService {
 
    private final DulceRepository repositorio;
 
    // INYECCIÓN DE DEPENDENCIAS: el servicio NO hace "new". Spring le entrega el repositorio.
    public DulceService(DulceRepository repositorio) {
        this.repositorio = repositorio;
    }
 
    // Todos los dulces, ordenados por nombre
    public List<Dulce> listar() {
        return repositorio.findAll().stream()
                .sorted(Comparator.comparing(Dulce::getNombre))
                .toList();
    }
 
    // Los de una categoría. Si la categoría es null, todos.
    public List<Dulce> listarPorCategoria(Categoria categoria) {
        if (categoria == null) {
            return listar();
        }
        return listar().stream()
                .filter(d -> d.getCategoria() == categoria)
                .toList();
    }
 
    // REGLA DE NEGOCIO: en el inicio se destacan 3 dulces disponibles
    public List<Dulce> destacados() {
        return listar().stream()
                .filter(Dulce::isDisponible)
                .limit(3)
                .toList();
    }
 
    // Busca un dulce; si no existe, responde 404 Not Found
    public Dulce buscar(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un dulce con el código " + id));
    }
}

