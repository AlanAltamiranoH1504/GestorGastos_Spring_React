package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import org.springframework.data.repository.CrudRepository;

public interface IEstadoRepository extends CrudRepository<Estado, Long> {
}
