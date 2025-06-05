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
import java.util.Optional;

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
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Proveedor> proveedor = implProveedorService.findById(id);
            if (proveedor.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(proveedor.get());
            }else {
                json.put("msg", "El proveedor no existe");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
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
                Optional<Proveedor> proveedorUpdate = implProveedorService.findById(id);
                if (!proveedorUpdate.isPresent()) {
                    json.put("msg", "Proveedor no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                }
                proveedorUpdate.get().setNombre(proveedor.getNombre());
                implProveedorService.save(proveedorUpdate.get());
                json.put("msg", "Proveedor actualizado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Proveedor> proveedor = implProveedorService.findById(id);
            if (proveedor.isPresent()) {
                implProveedorService.deleteById(id);
                json.put("msg", "Proveedor eliminado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
            json.put("msg", "Proveedor no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
