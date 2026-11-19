package co.edu.sena.adso.dulceria.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
import co.edu.sena.adso.dulceria.model.Categoria;
import co.edu.sena.adso.dulceria.model.Dulce;
import co.edu.sena.adso.dulceria.service.DulceService;
import jakarta.validation.Valid;
 
@Controller
@RequestMapping("/admin/dulces")   // Zona de administración de la tienda
public class AdminDulceController {
 
    private final DulceService dulces;
 
    public AdminDulceController(DulceService dulces) {
        this.dulces = dulces;
    }
 
    // GET /admin/dulces -> tabla con todos los dulces
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("dulces", dulces.listar());
        return "admin/dulces";
    }
 
    // GET /admin/dulces/nuevo -> formulario vacío
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("dulce", new Dulce());          // Objeto que llenará el formulario
        model.addAttribute("categorias", Categoria.values());
        return "admin/dulce-form";
    }
 
    // POST /admin/dulces/guardar -> recibe el formulario
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("dulce") Dulce dulce,   // Spring llena el objeto con los campos (DATA BINDING) y lo valida
                          BindingResult resultado,                       // Aquí quedan los errores. ¡Debe ir justo después!
                          Model model,
                          RedirectAttributes redireccion) {
 
        if (resultado.hasErrors()) {
            // Hay errores: se vuelve a mostrar el formulario con lo escrito y los mensajes
            model.addAttribute("categorias", Categoria.values());
            return "admin/dulce-form";
        }
 
        boolean esNuevo = dulce.getId() == null;
        Dulce guardado = dulces.guardar(dulce);
 
        // Mensaje que sobrevive a la redirección (atributo FLASH)
        redireccion.addFlashAttribute("mensaje",
                (esNuevo ? "Se registró " : "Se actualizó ") + "«" + guardado.getNombre() + "».");
 
        // POST-Redirect-GET: al recargar la página no se reenvía el formulario
        return "redirect:/admin/dulces";
    }
}

