package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.ErrorResponse;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.UsuarioDTO;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IPerfilService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import groovyjarjarpicocli.CommandLine;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IPerfilService iPerfilService;
    @Autowired
    private IEstadoService iEstadoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public ResponseEntity<?> listUsuarios() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Usuario> usuarios = iUsuarioService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Error en listado de usuarios", e.getClass().getName(), e.getMessage(), LocalDate.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Usuario usuario = iUsuarioService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Error en busqueda de usuario con id: " + id, e.getClass().getName(), e.getMessage(), LocalDate.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Perfil perfilDefault = iPerfilService.findById(1L);
                Estado estadoDefault = iEstadoService.findById(1L);

                usuario.setPerfil(perfilDefault);
                usuario.setEstado(estadoDefault);
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

                iUsuarioService.save(usuario);
                json.put("mensaje", "Usuario guardado correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (NoSuchElementException e) {
                json.put("error", "Perfil o Estado por defecto no encontrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
            } catch (Exception e) {
                ErrorResponse error = new ErrorResponse("Error en creacion de nuevo usuario.", e.getClass().getName(), e.getMessage(), LocalDate.now());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid UsuarioDTO usuarioDTO, @PathVariable int id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            try {
                Usuario usuario = iUsuarioService.findById(id);
                if (usuario == null) {
                    json.put("error", "Usuario no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                }
                Perfil perfilNuevo = iPerfilService.findById((long) usuarioDTO.getPerfilId());
                Estado estadoNuevo = iEstadoService.findById((long) usuarioDTO.getEstadoId());

                usuario.setNombre(usuarioDTO.getNombre());
                usuario.setEmail(usuarioDTO.getEmail());
                usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
                usuario.setFecha(new Date(System.currentTimeMillis()));
                usuario.setEstado(estadoNuevo);
                usuario.setPerfil(perfilNuevo);
                iUsuarioService.save(usuario);

                json.put("msg", "Usuario actualizado correctamente");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } catch (Exception e) {
                ErrorResponse error = new ErrorResponse("Error en actualizacion de usuario.", e.getClass().getName(), e.getMessage(), LocalDate.now());
                throw new RuntimeException(e);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Usuario usuario = iUsuarioService.findById(id);
            if (usuario == null) {
                json.put("error", "Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            iUsuarioService.deleteById(id);
            json.put("msg", "Usuario eliminado correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            ErrorResponse error = new ErrorResponse("Error en eliminiacion de usaurio con id: " + id, e.getClass().getName(), e.getMessage(), LocalDate.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
