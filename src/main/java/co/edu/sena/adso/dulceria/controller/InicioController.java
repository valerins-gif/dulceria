package co.edu.sena.adso.dulceria.controller;
 
import java.time.LocalTime;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import co.edu.sena.adso.dulceria.service.DulceService;
 
@Controller
public class InicioController {
 
    private final DulceService dulces;
 
    public InicioController(DulceService dulces) {
        this.dulces = dulces;
    }
 
    @GetMapping("/")
    public String inicio(Model model) {
        int hora = LocalTime.now().getHour();
        model.addAttribute("saludo", hora < 12 ? "¡Buenos días!" : hora < 18 ? "¡Buenas tardes!" : "¡Buenas noches!");
        model.addAttribute("horario", "Todos los días de 9:00 a. m. a 9:00 p. m.");
        model.addAttribute("destacados", dulces.destacados());
        return "inicio";
    }
}

