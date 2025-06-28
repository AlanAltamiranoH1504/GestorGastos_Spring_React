package altamirano.hernandez.proyectogastos_springboot_angular.controllers;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.models.dtos.ErrorResponse;
import altamirano.hernandez.proyectogastos_springboot_angular.services.CloudinaryService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import altamirano.hernandez.proyectogastos_springboot_angular.utils.UsuarioAutenticadoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/imagenes")
public class ImagenesController {
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;
    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping("")
    public ResponseEntity<?> uploadImagen(@RequestParam MultipartFile imagen) {
        try {
            Map<String, Object> json = new HashMap<>();
            //Validaciones con el archivo
            if (imagen.isEmpty()) {
                json.put("error", "Imagen no cargada de manera correcta.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }
            //Validacion de extensiones
            String extensionArchivo = imagen.getContentType();
            List<String> extensionesValidas = Arrays.asList("image/png", "image/jpeg", "image/jpg");
            if (!extensionesValidas.contains(extensionArchivo)) {
                json.put("error", "Extension de archivo no valida");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Subida de imagen y actualizacion de atributo imagen
            Usuario userInSession = usuarioAutenticadoHelper.obtenerUsuarioAutenticado();
            Map result = cloudinaryService.uploadImagen(imagen);
            String urlImagen = result.get("url").toString();
            userInSession.setImagenURL(urlImagen);
            iUsuarioService.save(userInSession);

            json.put("msg", "Imagen subida correctamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (IOException e) {
            ErrorResponse error = new ErrorResponse("Error en subida de imagen", e.getClass().getName(), e.getMessage(), LocalDate.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
