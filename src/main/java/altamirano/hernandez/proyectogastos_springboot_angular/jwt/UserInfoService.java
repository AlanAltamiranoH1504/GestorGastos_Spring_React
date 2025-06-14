package altamirano.hernandez.proyectogastos_springboot_angular.jwt;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IEstadoService iEstadoService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Estado estado = iEstadoService.findById(1L);
        Optional<Usuario> usuario = iUsuarioService.findByEmailAndEstadoId(email, estado);

        return usuario.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuario con email " + email + " no existente."));
    }
}
