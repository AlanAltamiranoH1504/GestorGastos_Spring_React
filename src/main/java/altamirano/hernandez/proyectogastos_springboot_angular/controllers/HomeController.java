package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> metodoGet(){
        Map<String, Object> json = new HashMap<>();
       json.put("msg", "Funcionando controlador home");

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
