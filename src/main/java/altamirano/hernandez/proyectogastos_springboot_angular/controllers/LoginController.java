package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.jwt.JWTService;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.JWTResponseDTO;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.LoginDTO;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IEstadoService iEstadoService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errores);
        } else {
            Estado estadoActivo = iEstadoService.findById(1L);
            Optional<Usuario> usuario = iUsuarioService.findByEmailAndEstadoId(loginDTO.getEmail(), estadoActivo);
            if (!usuario.isPresent()) {
                json.put("error", "Usuario no encontrado o no activo");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            } else {
                if (passwordEncoder.matches(loginDTO.getPassword(), usuario.get().getPassword())) {
                    Usuario usuarioActual = usuario.get();
                    Perfil perfilUsuario = usuarioActual.getPerfil();
                    String token = jwtService.generateToken(usuario.get().getEmail());
                    JWTResponseDTO jwtResponseDTO = new JWTResponseDTO(usuarioActual.getId(), usuarioActual.getNombre(), perfilUsuario.getNombre(), Math.toIntExact(perfilUsuario.getId()), token);
                    return ResponseEntity.status(HttpStatus.OK).body(jwtResponseDTO);
                } else {
                    json.put("error", "Credenciales incorrectas");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
                }
            }

        }
    }
}
