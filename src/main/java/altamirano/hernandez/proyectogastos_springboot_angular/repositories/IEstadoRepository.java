package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEstadoRepository extends CrudRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e WHERE e.id IN :ids")
    List<Estado> findByIdIn(@Param("ids") List<Long> ids);
}
