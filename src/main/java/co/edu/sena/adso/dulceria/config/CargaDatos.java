package co.edu.sena.adso.dulceria.config;
 
import java.util.List;
 
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.repository.DulceRepository;
 
// Se ejecuta UNA vez, cuando la aplicación termina de arrancar
@Component
public class CargaDatos implements CommandLineRunner {
 
    private final DulceRepository repositorio;
 
    public CargaDatos(DulceRepository repositorio) {
        this.repositorio = repositorio;
    }
 
    @Override
    public void run(String... args) {
        if (repositorio.count() > 0) {
            return;   // La tabla ya tiene datos: no se duplican
        }
        Dulce tamarindo = new Dulce("Bolitas de tamarindo", "Pulpa de tamarindo con azúcar", 2000, Categoria.OTROS);
        tamarindo.setDisponible(false);
 
        repositorio.saveAll(List.of(
                new Dulce("Cocada blanca", "Coco rallado y azúcar, receta tradicional", 3000, Categoria.COCADAS),
                new Dulce("Cocada de panela", "Coco con panela, color dorado", 3000, Categoria.COCADAS),
                new Dulce("Cocada de piña", "Coco con piña caramelizada", 3500, Categoria.COCADAS),
                new Dulce("Alegría de millo", "Bolita de millo, coco y panela con anís", 2500, Categoria.ALEGRIAS),
                new Dulce("Panelita de leche", "Leche y azúcar cortadas en cuadritos", 2000, Categoria.PANELITAS),
                new Dulce("Caballito de papaya", "Papaya verde en almíbar con clavo y canela", 4500, Categoria.CONSERVAS),
                new Dulce("Dulce de icaco", "Icacos en almíbar", 4000, Categoria.CONSERVAS),
                tamarindo));
        System.out.println(">>> Se guardaron 8 dulces en PostgreSQL");
    }
}

