package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;

import java.util.List;

public interface IPerfilService {
    public abstract List<Perfil> findAll();
    public abstract Perfil findById(Long id);
    public abstract void save(Perfil perfil);
    public abstract void deleteById(Long id);
}
