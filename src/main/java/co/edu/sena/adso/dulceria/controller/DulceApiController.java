package co.edu.sena.adso.dulceria.controller;
 
import java.util.List;
 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.service.DulceService;
 
// @RestController: responde DATOS (JSON), no páginas.
// Sirve, por ejemplo, para una app móvil o un front-end en JavaScript.
@RestController
@RequestMapping("/api/dulces")
public class DulceApiController {
 
    private final DulceService dulces;   // ¡El MISMO servicio que usan las páginas!
 
    public DulceApiController(DulceService dulces) {
        this.dulces = dulces;
    }
 
    // GET /api/dulces   o   GET /api/dulces?categoria=CONSERVAS
    @GetMapping
    public List<Dulce> listar(@RequestParam(name = "categoria", required = false) Categoria categoria) {
        return dulces.listarPorCategoria(categoria);   // Spring convierte la lista en JSON
    }
 
    // GET /api/dulces/3
    @GetMapping("/{id}")
    public Dulce buscar(@PathVariable("id") Long id) {
        return dulces.buscar(id);
    }
}

