package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.GastoPorDiaDTO;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IGastosPorDiaService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IProveedorService;
import altamirano.hernandez.proyectogastos_springboot_angular.utils.UsuarioAutenticadoHelper;
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
@RequestMapping("/gastos-por-dia")
public class GastoPorDiaController {

    @Autowired
    private IProveedorService iProveedorService;
    @Autowired
    private IGastosPorDiaService iGastosPorDiaService;
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;

    @GetMapping("")
    public ResponseEntity<?> list() {
        Map<String, Object> json = new HashMap<>();
        try {
            LocalDate fecha = LocalDate.now();
            Usuario usuarioInSesion = usuarioAutenticadoHelper.obtenerUsuarioAutenticado();
            List<GatosPorDia> gastosPorDia = iGastosPorDiaService.findByMesActual(fecha.getMonthValue(), fecha.getYear(), usuarioInSesion.getId());
            json.put("gastosPorDia", gastosPorDia);
            return ResponseEntity.status(HttpStatus.OK).body(gastosPorDia);
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody GastoPorDiaDTO gastoPorDiaDto, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Optional<Proveedor> proveedor = iProveedorService.findById(gastoPorDiaDto.getProveedorId());
                if (!proveedor.isPresent()) {
                    json.put("msg", "Proveedor no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                } else {
                    Usuario userInSesion = usuarioAutenticadoHelper.obtenerUsuarioAutenticado();
                    LocalDate fecha = LocalDate.now();
                    GatosPorDia gastoPorDia = new GatosPorDia(gastoPorDiaDto.getNeto(), gastoPorDiaDto.getIva(), gastoPorDiaDto.getTotal(), fecha, gastoPorDiaDto.getDescripcion(), proveedor.get(), userInSesion);
                    iGastosPorDiaService.save(gastoPorDia);
                    json.put("msg", "Gasto por dia guardada con exito");
                    return ResponseEntity.status(HttpStatus.CREATED).body(json);
                }
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody GastoPorDiaDTO gastoPorDiaDTO, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Optional<GatosPorDia> gastoPorDia = iGastosPorDiaService.findById(id);
                if (!gastoPorDia.isPresent()) {
                    json.put("msg", "Gasto no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                } else {
                    Optional<Proveedor> proveedor = iProveedorService.findById(gastoPorDiaDTO.getProveedorId());
                    if (!proveedor.isPresent()) {
                        json.put("msg", "Proveedor no encontrado");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                    }
                    double total = gastoPorDiaDTO.getNeto() * (1 + (gastoPorDiaDTO.getIva() / 100));
                    gastoPorDia.get().setNeto(gastoPorDiaDTO.getNeto());
                    gastoPorDia.get().setIva(gastoPorDiaDTO.getIva());
                    gastoPorDia.get().setDescripcion(gastoPorDiaDTO.getDescripcion());
                    gastoPorDia.get().setTotal(total);

                    iGastosPorDiaService.save(gastoPorDia.get());
                    json.put("msg", "Gasto por dia actualizado con exito");
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                }
            } catch (RuntimeException e) {
                json.put("msg", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> json = new HashMap<>();
        try {

            Optional<GatosPorDia> gastosPorDia = iGastosPorDiaService.findById(id);
            if (gastosPorDia.isPresent()) {
                iGastosPorDiaService.deleteById(gastosPorDia.get().getId());
                json.put("msg", "Gasto por dia eliminado con exito");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("msg", "Gasto no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

}
