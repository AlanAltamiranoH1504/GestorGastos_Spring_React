package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import org.springframework.data.repository.CrudRepository;

public interface IGastosFijosRepository extends CrudRepository<GastosFijos, Long> {
}
