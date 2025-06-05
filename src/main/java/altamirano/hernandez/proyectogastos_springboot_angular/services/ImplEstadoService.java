package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Estado;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IEstadoRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplEstadoService implements IEstadoService {

    @Autowired
    private IEstadoRepository iEstadoRepository;

    @Override
    public List<Estado> findAll() {
        try {
            List<Estado> estados = (List<Estado>) iEstadoRepository.findAll();
            return estados;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Estado findById(Long id) {
        try {
            Estado estado = iEstadoRepository.findById(id).get();
            return estado;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Estado> findByIdIn(List<Long> ids) {
        try {
            List<Estado> estadosFiltrados = iEstadoRepository.findByIdIn(ids);
            return estadosFiltrados;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Estado estado) {
        try {
            iEstadoRepository.save(estado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            iEstadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
