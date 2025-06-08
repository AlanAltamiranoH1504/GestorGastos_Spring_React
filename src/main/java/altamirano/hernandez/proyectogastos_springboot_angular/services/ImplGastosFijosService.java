package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IGastosFijosRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IGastosFijosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<GastosFijos> findById(int id) {
        try {
            Optional<GastosFijos> gastoFijo = iGastosFijosRepository.findById(id);
            if (gastoFijo.isPresent()) {
                return gastoFijo;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<GastosFijos> findByMesActual(int mes, int año) {
        try {
            List<GastosFijos> gastosFijosPorMesActual = iGastosFijosRepository.findAllByMesActual(mes, año);
            return gastosFijosPorMesActual;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GastosFijos> findByMesDado(int mes, int año) {
        try {
            List<GastosFijos> gastosFijosPorMesDado = iGastosFijosRepository.findAllByMesDado(mes, año);
            return gastosFijosPorMesDado;
        } catch (RuntimeException e) {
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
    public void deleteById(int id) {
        try {
            iGastosFijosRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
