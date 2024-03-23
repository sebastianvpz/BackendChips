package com.company.service;

import com.company.model.Especie;
import com.company.repository.EspecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> getAllEspecies() {
        return especieRepository.findAll();
    }

    public Optional<Especie> getEspecieById(Long id) {
        return especieRepository.findById(id);
    }

    public Especie createEspecie(Especie especie) {
        return especieRepository.save(especie);
    }

    public Especie updateEspecie(Long id, Especie especie) {
        if (especieRepository.existsById(id)) {
            especie.setIdespecie(id);
            return especieRepository.save(especie);
        }
        return null;
    }

    public void deleteEspecie(Long id) {
        especieRepository.deleteById(id);
    }
}

