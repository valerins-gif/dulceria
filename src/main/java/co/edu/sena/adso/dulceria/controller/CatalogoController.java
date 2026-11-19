package co.edu.sena.adso.dulceria.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.model.Encargo;
import co.edu.sena.adso.dulceria.service.DulceService;
import co.edu.sena.adso.dulceria.service.EncargoService;
import jakarta.validation.Valid;
 
@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
 
    private final DulceService dulces;
    private final EncargoService encargos;   // NUEVO
 
    public CatalogoController(DulceService dulces, EncargoService encargos) {
        this.dulces = dulces;
        this.encargos = encargos;
    }
 
    @GetMapping
    public String catalogo(@RequestParam(name = "categoria", required = false) Categoria categoria,
                           Model model) {
        model.addAttribute("dulces", dulces.listarPorCategoria(categoria));
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("categoriaActual", categoria);
        return "catalogo";
    }
 
    @GetMapping("/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dulce", dulces.buscar(id));
        model.addAttribute("encargo", new Encargo());   // NUEVO · objeto vacío para el formulario
        return "detalle";
    }
 
    // NUEVO · POST /catalogo/3/encargos -> recibe el formulario de encargo
    @PostMapping("/{id}/encargos")
    public String encargar(@PathVariable("id") Long id,
                           @Valid @ModelAttribute("encargo") Encargo encargo,
                           BindingResult resultado,
                           Model model,
                           RedirectAttributes redireccion) {
        Dulce dulce = dulces.buscar(id);
        if (resultado.hasErrors()) {
            model.addAttribute("dulce", dulce);   // La vista también necesita el dulce
            return "detalle";
        }
        Encargo guardado = encargos.registrar(dulce, encargo);
        redireccion.addFlashAttribute("mensaje", "¡Gracias, " + guardado.getNombreCliente()
                + "! Recibimos su encargo de " + guardado.getCantidad() + " " + dulce.getNombre()
                + ". Lo llamaremos al " + guardado.getTelefono() + ".");
        return "redirect:/catalogo/" + id;
    }
}

