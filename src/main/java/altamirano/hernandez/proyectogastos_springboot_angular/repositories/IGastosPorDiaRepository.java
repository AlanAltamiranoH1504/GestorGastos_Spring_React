package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGastosPorDiaRepository extends CrudRepository<GatosPorDia, Integer> {

    @Query("SELECT g FROM GatosPorDia g WHERE MONTH(g.fecha)=:mes AND YEAR(g.fecha)=:año")
    List<GatosPorDia> findAllByMesActual(@Param("mes") int mes, @Param("año") int año);
}
