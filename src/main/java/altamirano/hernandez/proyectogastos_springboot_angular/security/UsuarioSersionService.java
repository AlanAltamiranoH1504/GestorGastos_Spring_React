//package altamirano.hernandez.proyectogastos_springboot_angular.security;
//
//import altamirano.hernandez.proyectogastos_springboot_angular.jwt.UserInfoDetails;
//import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UsuarioSersionService {
//    public Usuario getUsuarioEnSesion() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserInfoDetails) {
//            return ((UserInfoDetails) principal).getUsuario() ;
//        } else {
//            return null;
//        }
//    }
//}
