package co.edu.sena.adso.dulceria.service;
 
import java.util.Comparator;
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.repository.DulceRepository;
 
@Service
public class DulceService {
 
    private final DulceRepository repositorio;
 
    public DulceService(DulceRepository repositorio) {
        this.repositorio = repositorio;
    }
 
    public List<Dulce> listar() {
        return repositorio.findAll().stream()
                .sorted(Comparator.comparing(Dulce::getNombre))
                .toList();
    }
 
    public List<Dulce> listarPorCategoria(Categoria categoria) {
        if (categoria == null) {
            return listar();
        }
        return listar().stream()
                .filter(d -> d.getCategoria() == categoria)
                .toList();
    }
 
    public List<Dulce> destacados() {
        return listar().stream()
                .filter(Dulce::isDisponible)
                .limit(3)
                .toList();
    }
 
    public Dulce buscar(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe un dulce con el código " + id));
    }
 
    // NUEVO · Guarda un dulce: si no tiene id lo INSERTA; si tiene id lo ACTUALIZA
    public Dulce guardar(Dulce dulce) {
        dulce.setNombre(dulce.getNombre().trim());
        return repositorio.save(dulce);
    }
 
    // NUEVO · Elimina un dulce (primero verifica que exista)
    public void eliminar(Long id) {
        buscar(id);
        repositorio.deleteById(id);
    }
}

