package com.company.service;

import com.company.model.Medico;
import com.company.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico createMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico updateMedico(Long id, Medico medico) {
        if (medicoRepository.existsById(id)) {
            medico.setIdmedico(id);
            return medicoRepository.save(medico);
        }
        return null;
    }

    public void deleteMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}

