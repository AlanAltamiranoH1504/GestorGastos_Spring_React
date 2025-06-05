package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IUsuarioRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplUsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        try {
            List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario findById(Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id).get();
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
