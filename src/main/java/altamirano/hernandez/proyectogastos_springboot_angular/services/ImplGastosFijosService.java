package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IGastosFijosRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IGastosFijosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplGastosFijosService implements IGastosFijosService {

    @Autowired
    private IGastosFijosRepository iGastosFijosRepository;

    @Override
    public List<GastosFijos> findAll() {
        try {
            List<GastosFijos> gastosFijos = (List<GastosFijos>) iGastosFijosRepository.findAll();
            return gastosFijos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GastosFijos findById(Long id) {
        try {
            GastosFijos gastosFijos = iGastosFijosRepository.findById(id).get();
            return gastosFijos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(GastosFijos gastosFijos) {
        try {
            iGastosFijosRepository.save(gastosFijos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            iGastosFijosRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
