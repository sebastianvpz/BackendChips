package com.company.service;

import com.company.model.Mascota;
import com.company.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getMascotaById(Long id) {
        return mascotaRepository.findById(id);
    }

    public void createMascota(Mascota mascota) {
        mascotaRepository.save(mascota);
    }

    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}

