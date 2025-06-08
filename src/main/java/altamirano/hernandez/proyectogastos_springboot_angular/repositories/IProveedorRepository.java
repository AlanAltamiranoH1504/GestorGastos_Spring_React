package altamirano.hernandez.proyectogastos_springboot_angular.repositories;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import org.springframework.data.repository.CrudRepository;

public interface IProveedorRepository extends CrudRepository<Proveedor, Integer> {
}
