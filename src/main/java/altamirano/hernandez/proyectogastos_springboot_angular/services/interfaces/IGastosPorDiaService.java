package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;

import java.util.List;
import java.util.Optional;

public interface IGastosPorDiaService {
    public abstract List<GatosPorDia> findAll();
    public abstract List<GatosPorDia> findByMesActual(int mes, int año);
    public abstract Optional<GatosPorDia> findById(int id);
    public abstract void save(GatosPorDia gatosPorDia);
    public abstract void deleteById(int id);
}
