package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Proveedor;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IProveedorRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Proveedor findById(Long id) {
        try {
            Proveedor proveedor = iProveedorRepository.findById(id).get();
            return proveedor;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public void deleteById(Long id) {
        try {
            iProveedorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
