package altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    public abstract List<Proveedor> findAll();
    public abstract Optional<Proveedor> findById(Long id);
    public abstract void save(Proveedor proveedor);
    public abstract void deleteById(Long id);
}
