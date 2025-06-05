package altamirano.hernandez.proyectogastos_springboot_angular.services;

import altamirano.hernandez.proyectogastos_springboot_angular.models.Perfil;
import altamirano.hernandez.proyectogastos_springboot_angular.repositories.IPerfilRepository;
import altamirano.hernandez.proyectogastos_springboot_angular.services.interfaces.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplPerfilService implements IPerfilService {

    @Autowired
    private IPerfilRepository iPerfilRepository;

    @Override
    public List<Perfil> findAll() {
        try {
            List<Perfil> perfiles = (List<Perfil>) iPerfilRepository.findAll();
            return perfiles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Perfil findById(Long id) {
        try {
            Perfil perfil = iPerfilRepository.findById(id).get();
            return perfil;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Perfil perfil) {
        try {
            iPerfilRepository.save(perfil);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            iPerfilRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
