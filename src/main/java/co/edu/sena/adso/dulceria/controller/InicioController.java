package co.edu.sena.adso.dulceria.controller;
 
import java.time.LocalTime;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller   // Esta clase atiende peticiones y responde con PÁGINAS (vistas)
public class InicioController {
 
    // Atiende GET http://localhost:8080/
    @GetMapping("/")
    public String inicio(Model model) {
        // El Model es la "bandeja" donde el controlador deja los datos para la vista
        model.addAttribute("nombreTienda", "Dulces Doña Ana");
        model.addAttribute("eslogan", "Dulces típicos del Portal de los Dulces · Cartagena de Indias");
        model.addAttribute("horario", "Todos los días de 9:00 a. m. a 9:00 p. m.");
 
        // Un dato que cambia en cada petición: prueba de que la página es DINÁMICA
        int hora = LocalTime.now().getHour();
        String saludo = hora < 12 ? "¡Buenos días!" : hora < 18 ? "¡Buenas tardes!" : "¡Buenas noches!";
        model.addAttribute("saludo", saludo);
 
        return "inicio";   // Nombre de la vista: src/main/resources/templates/inicio.html
    }
}

