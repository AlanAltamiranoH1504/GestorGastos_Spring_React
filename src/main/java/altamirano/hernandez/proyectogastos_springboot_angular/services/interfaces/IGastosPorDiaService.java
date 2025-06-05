package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;

import java.util.List;

public interface IGastosPorDiaService {
    public abstract List<GatosPorDia> findAll();
    public abstract GatosPorDia findById(Long id);
    public abstract void save(GatosPorDia gatosPorDia);
    public abstract void deleteById(Long id);
}
