package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
}
