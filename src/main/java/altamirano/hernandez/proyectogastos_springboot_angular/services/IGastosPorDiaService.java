package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.GastosFijos;
import altamirano.hernandez.proyectogastos_springboot_angular.models.GatosPorDia;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IGastosPorDiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public GatosPorDia findById(Long id) {
        try {
            GatosPorDia gastosPorDia = iGastosPorDiaRepository.findById(id).get();
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
    public void deleteById(Long id) {
        try {
            iGastosPorDiaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
