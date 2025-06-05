package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;
import org.springframework.data.repository.CrudRepository;

public interface IGastosPorDiaRepository extends CrudRepository<GatosPorDia, Long> {
}
