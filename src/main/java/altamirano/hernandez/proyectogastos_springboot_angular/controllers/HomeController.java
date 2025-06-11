package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home/home"; // Redirige a una vista HTML llamada "index.html"
    }
}

