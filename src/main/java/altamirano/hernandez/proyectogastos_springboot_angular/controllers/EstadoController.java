package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.services.ImplEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private ImplEstadoService implEstadoService;

    @GetMapping("")
    public ResponseEntity<?> findAllEstados() {
        try {
            List<Estado> estados = implEstadoService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(estados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/estados-gastos")
    public ResponseEntity<?> findAllEstadosParaGastos() {
        try {
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(2L);

            List<Estado> estadosFiltrados = implEstadoService.findByIdIn(ids);
            return ResponseEntity.status(HttpStatus.OK).body(estadosFiltrados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
