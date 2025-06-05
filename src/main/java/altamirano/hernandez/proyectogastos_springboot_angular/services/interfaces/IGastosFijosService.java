package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;

import java.util.List;

public interface IGastosFijosService {
    public abstract List<GastosFijos> findAll();
    public abstract GastosFijos findById(Long id);
    public abstract void save(GastosFijos gastosFijos);
    public abstract void deleteById(Long id);
}
