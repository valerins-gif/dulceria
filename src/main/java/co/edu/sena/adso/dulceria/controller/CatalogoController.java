package co.edu.sena.adso.dulceria.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.service.DulceService;
 
@Controller
@RequestMapping("/catalogo")   // Prefijo común para todas las rutas de esta clase
public class CatalogoController {
 
    private final DulceService dulces;
 
    public CatalogoController(DulceService dulces) {
        this.dulces = dulces;
    }
 
    // GET /catalogo                     -> todos
    // GET /catalogo?categoria=COCADAS   -> solo una categoría (PARÁMETRO DE CONSULTA)
    @GetMapping
    public String catalogo(@RequestParam(name = "categoria", required = false) Categoria categoria,
                           Model model) {
        model.addAttribute("dulces", dulces.listarPorCategoria(categoria));
        model.addAttribute("categorias", Categoria.values());   // Para los botones de filtro
        model.addAttribute("categoriaActual", categoria);
        return "catalogo";
    }
 
    // GET /catalogo/3   -> detalle del dulce 3 (VARIABLE DE RUTA)
    @GetMapping("/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dulce", dulces.buscar(id));
        return "detalle";
    }
}

