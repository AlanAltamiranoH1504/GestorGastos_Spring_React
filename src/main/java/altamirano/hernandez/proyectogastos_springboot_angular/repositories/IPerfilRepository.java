package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;
import org.springframework.data.repository.CrudRepository;

public interface IPerfilRepository extends CrudRepository<Perfil, Long> {
}
