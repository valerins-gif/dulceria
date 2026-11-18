package co.edu.sena.adso.dulceria.controller;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
 
@Controller
public class CatalogoController {
 
    // GET http://localhost:8080/catalogo
    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        // ¡OJO! Crear los datos dentro del controlador NO es una buena práctica.
        // Lo hacemos así un momento para ver la idea; en la etapa 5 lo corregimos.
        List<Dulce> dulces = new ArrayList<>();
        dulces.add(new Dulce("Cocada blanca", "Coco rallado y azúcar, receta tradicional", 3000, Categoria.COCADAS));
        dulces.add(new Dulce("Cocada de panela", "Coco con panela, color dorado", 3000, Categoria.COCADAS));
        dulces.add(new Dulce("Cocada de piña", "Coco con piña caramelizada", 3500, Categoria.COCADAS));
        dulces.add(new Dulce("Alegría de millo", "Bolita de millo, coco y panela con anís", 2500, Categoria.ALEGRIAS));
        dulces.add(new Dulce("Panelita de leche", "Leche y azúcar cortadas en cuadritos", 2000, Categoria.PANELITAS));
        dulces.add(new Dulce("Caballito de papaya", "Papaya verde en almíbar con clavo y canela", 4500, Categoria.CONSERVAS));
        dulces.add(new Dulce("Dulce de icaco", "Icacos en almíbar", 4000, Categoria.CONSERVAS));
        dulces.add(new Dulce("Bolitas de tamarindo", "Pulpa de tamarindo con azúcar", 2000, Categoria.OTROS));
 
        model.addAttribute("dulces", dulces);
        return "catalogo";
    }
}

