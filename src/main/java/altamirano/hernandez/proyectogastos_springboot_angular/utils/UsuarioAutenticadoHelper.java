package altamirano.hernandez.proyectogastos_springboot_angular.utils;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAutenticadoHelper {
    public Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()){
            Object principal = auth.getPrincipal();
            if (principal instanceof Usuario) {
                return (Usuario) principal;
            }
        }
        return null;
    }
}
