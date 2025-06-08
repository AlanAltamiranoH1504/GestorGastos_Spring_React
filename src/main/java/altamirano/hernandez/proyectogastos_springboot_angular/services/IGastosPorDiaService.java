package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IGastosPorDiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IGastosPorDiaService implements altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IGastosPorDiaService {

    @Autowired
    private IGastosPorDiaRepository iGastosPorDiaRepository;

    @Override
    public List<GatosPorDia> findAll() {
        try {
            List<GatosPorDia> gastosPorDia = (List<GatosPorDia>) iGastosPorDiaRepository.findAll();
            return gastosPorDia;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GatosPorDia> findByMesActual(int mes, int año) {
        try {
            List<GatosPorDia> gastos = iGastosPorDiaRepository.findAllByMesActual(mes, año);
            return gastos;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<GatosPorDia> findById(int id) {
        try {
            Optional<GatosPorDia> gastosPorDia = iGastosPorDiaRepository.findById(id);
            return gastosPorDia;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(GatosPorDia gatosPorDia) {
        try {
            iGastosPorDiaRepository.save(gatosPorDia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            iGastosPorDiaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
