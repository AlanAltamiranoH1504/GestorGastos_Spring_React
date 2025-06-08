package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGastosFijosRepository extends CrudRepository<GastosFijos, Integer> {

    @Query("SELECT g FROM GastosFijos g WHERE MONTH(g.fecha)=:mes AND YEAR(g.fecha)=:año")
    List<GastosFijos> findAllByMesActual(@Param("mes") int mes, @Param("año") int año);

    @Query("SELECT g FROM GastosFijos g WHERE MONTH(g.fecha)=:mes AND YEAR(g.fecha)=:año")
    List<GastosFijos> findAllByMesDado(@Param("mes") int mes, @Param("año") int año);
}
