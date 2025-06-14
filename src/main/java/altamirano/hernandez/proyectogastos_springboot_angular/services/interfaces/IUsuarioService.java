package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public abstract List<Usuario> findAll();
    public abstract Usuario findById(int id);
    public Optional<Usuario> findByEmail(String email);
    public Optional<Usuario> findByEmailAndEstadoId(String email, Estado estado);
    public abstract void save(Usuario usuario);
    public abstract void deleteById(int id);
}
