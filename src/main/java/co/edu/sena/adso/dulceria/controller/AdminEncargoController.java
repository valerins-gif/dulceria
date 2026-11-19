package co.edu.sena.adso.dulceria.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import co.edu.sena.adso.dulceria.service.EncargoService;
 
@Controller
@RequestMapping("/admin/encargos")
public class AdminEncargoController {
 
    private final EncargoService encargos;
 
    public AdminEncargoController(EncargoService encargos) {
        this.encargos = encargos;
    }
 
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("encargos", encargos.listar());
        model.addAttribute("total", encargos.totalEncargado());
        return "admin/encargos";
    }
}

