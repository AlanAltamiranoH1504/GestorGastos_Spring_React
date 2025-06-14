package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IPerfilService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
            }
        }
    }
}
