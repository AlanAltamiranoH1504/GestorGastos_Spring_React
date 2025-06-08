package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;

import java.util.List;
import java.util.Optional;

public interface IGastosFijosService {
    public abstract List<GastosFijos> findAll();
    public abstract Optional<GastosFijos> findById(int id);
    public abstract List<GastosFijos> findByMesActual(int mes, int año);
    public abstract List<GastosFijos> findByMesDado(int mes, int año);
    public abstract void save(GastosFijos gastosFijos);
    public abstract void deleteById(int id);
}
