package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IProveedorRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplProveedorService implements IProveedorService {

    @Autowired
    private IProveedorRepository iProveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        try {
            List<Proveedor> proveedors = (List<Proveedor>) iProveedorRepository.findAll();
            return proveedors;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Proveedor> findById(int id) {
        try {
            Optional<Proveedor> proveedor = iProveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                 return proveedor;
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar proveedor con ID: " + id, e);
        }
    }

    @Override
    public void save(Proveedor proveedor) {
        try {
            iProveedorRepository.save(proveedor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            iProveedorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
