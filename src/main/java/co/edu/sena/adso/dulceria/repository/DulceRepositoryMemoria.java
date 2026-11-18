package co.edu.sena.adso.dulceria.repository;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
 
import org.springframework.stereotype.Repository;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
 
@Repository   // Capa de DATOS. Por ahora los datos viven en memoria: se pierden al reiniciar.
public class DulceRepositoryMemoria implements DulceRepository {
 
    private final Map<Long, Dulce> datos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong();
 
    public DulceRepositoryMemoria() {
        save(new Dulce("Cocada blanca", "Coco rallado y azúcar, receta tradicional", 3000, Categoria.COCADAS));
        save(new Dulce("Cocada de panela", "Coco con panela, color dorado", 3000, Categoria.COCADAS));
        save(new Dulce("Cocada de piña", "Coco con piña caramelizada", 3500, Categoria.COCADAS));
        save(new Dulce("Alegría de millo", "Bolita de millo, coco y panela con anís", 2500, Categoria.ALEGRIAS));
        save(new Dulce("Panelita de leche", "Leche y azúcar cortadas en cuadritos", 2000, Categoria.PANELITAS));
        save(new Dulce("Caballito de papaya", "Papaya verde en almíbar con clavo y canela", 4500, Categoria.CONSERVAS));
        save(new Dulce("Dulce de icaco", "Icacos en almíbar", 4000, Categoria.CONSERVAS));
        Dulce tamarindo = new Dulce("Bolitas de tamarindo", "Pulpa de tamarindo con azúcar", 2000, Categoria.OTROS);
        tamarindo.setDisponible(false);   // Uno agotado, para ver cómo se muestra
        save(tamarindo);
    }
 
    @Override
    public List<Dulce> findAll() {
        return new ArrayList<>(datos.values());
    }
 
    @Override
    public Optional<Dulce> findById(Long id) {
        return Optional.ofNullable(datos.get(id));
    }
 
    @Override
    public Dulce save(Dulce dulce) {
        if (dulce.getId() == null) {              // Si es nuevo, se le asigna un código
            dulce.setId(secuencia.incrementAndGet());
        }
        datos.put(dulce.getId(), dulce);
        return dulce;
    }
 
    @Override
    public void deleteById(Long id) {
        datos.remove(id);
    }
}

