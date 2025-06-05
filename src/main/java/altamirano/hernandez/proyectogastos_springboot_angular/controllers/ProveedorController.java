package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import altamirano.hernandez.proyectogastos_springboot_angular.services.ImplProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    private ImplProveedorService implProveedorService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Proveedor> proveedores = implProveedorService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Proveedor proveedor = implProveedorService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(proveedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Proveedor proveedor, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, Object> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }
            implProveedorService.save(proveedor);
            json.put("msg", "Proveedor registrado de manera correcta");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Proveedor proveedor, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, Object> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            } else {
                implProveedorService.save(proveedor);
                json.put("msg", "Proveedor actualizado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
