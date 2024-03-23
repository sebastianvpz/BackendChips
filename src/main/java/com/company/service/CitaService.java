package com.company.service;

import com.company.model.Cita;
import com.company.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getCitaById(Long id) {
        return citaRepository.findById(id);
    }

    public Cita createCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita updateCita(Long id, Cita cita) {
        if (citaRepository.existsById(id)) {
            cita.setIdcita(id);
            return citaRepository.save(cita);
        }
        return null;
    }

    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }
}

