package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;

import java.util.List;

public interface IEstadoService {
    public abstract List<Estado> findAll();
    public abstract Estado findById(Long id);
    public abstract List<Estado> findByIdIn(List<Long> ids);
    public abstract void save(Estado estado);
    public abstract void deleteById(Long  id);
}
