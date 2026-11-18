package co.edu.sena.adso.dulceria.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import co.edu.sena.adso.dulceria.service.DulceService;
 
@Controller
public class CatalogoController {
 
    private final DulceService dulces;
 
    // El controlador pide el servicio en su constructor y Spring se lo entrega
    public CatalogoController(DulceService dulces) {
        this.dulces = dulces;
    }
 
    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("dulces", dulces.listar());   // ¡Mucho más limpio!
        return "catalogo";
    }
}

