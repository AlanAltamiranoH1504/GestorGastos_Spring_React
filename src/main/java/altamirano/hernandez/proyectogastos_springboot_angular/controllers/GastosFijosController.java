package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.GastoFijoDTO;
import altamirano.hernandez.proyectogastos_springboot_angular.services.ImplGastosFijosService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/gastos-fijos")
public class GastosFijosController {

    @Autowired
    private ImplGastosFijosService implGastosFijosService;
    @Autowired
    private IEstadoService iEstadoService;
    @Autowired
    private IProveedorService iProveedorService;

    @GetMapping("/actual")
    public ResponseEntity<?> findAllByMesActual() {
        try {
            LocalDate fechaActual = LocalDate.now();
            int numeroMes = fechaActual.getMonthValue();
            int numeroAño = fechaActual.getYear();

            List<GastosFijos> gastosFijos = implGastosFijosService.findByMesActual(numeroMes, numeroAño);
            return ResponseEntity.status(HttpStatus.OK).body(gastosFijos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{mes}/{año}")
    public ResponseEntity<?> findAllByMesDado(@PathVariable int mes, @PathVariable int año) {
        try {
            List<GastosFijos> gastosFijos = implGastosFijosService.findByMesDado(mes, año);
            return ResponseEntity.status(HttpStatus.OK).body(gastosFijos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody GastoFijoDTO gastoFijoDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        System.out.println("gas = " + gastoFijoDTO.getIdEstado());
        System.out.println("gastoFijoDTO.getIdProveedor() = " + gastoFijoDTO.getIdProveedor());
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Optional<Proveedor> proveedor = iProveedorService.findById(gastoFijoDTO.getIdProveedor());
            Estado estado = iEstadoService.findById(Long.valueOf(gastoFijoDTO.getIdEstado()));
            LocalDate fecha = LocalDate.now();

            GastosFijos gastoFijo = new GastosFijos(gastoFijoDTO.getNombre(), gastoFijoDTO.getMonto(), fecha, estado, proveedor.get());
            implGastosFijosService.save(gastoFijo);
            json.put("msg", "Gasto fijo guardado correctamente");
            return ResponseEntity.status(HttpStatus.CREATED).body(json);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody GastoFijoDTO gastoFijoDTO, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Optional<GastosFijos> gastoFijoUpdate = implGastosFijosService.findById(id);
            if (gastoFijoUpdate.isPresent()) {
                Optional<Proveedor> proveedor = iProveedorService.findById(gastoFijoDTO.getIdProveedor());
                Estado estado = iEstadoService.findById(Long.valueOf(gastoFijoDTO.getIdEstado()));

                gastoFijoUpdate.get().setNombre(gastoFijoDTO.getNombre());
                gastoFijoUpdate.get().setMonto(gastoFijoDTO.getMonto());
                gastoFijoUpdate.get().setProveedor(proveedor.get());
                gastoFijoUpdate.get().setEstado(estado);

                implGastosFijosService.save(gastoFijoUpdate.get());
                json.put("msg", "Gasto fijo actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("msg", "Gasto fijo no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            Map<String, Object> json = new HashMap<>();

            Optional<GastosFijos> gastoFijo = implGastosFijosService.findById(id);
            if (gastoFijo.isPresent()) {
                implGastosFijosService.deleteById(id);
                json.put("msg", "Gasto fijo eliminado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("msg", "Gasto fijo no existente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
