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

    public Mascota createMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota updateMascota(Long id, Mascota mascota) {
        if (mascotaRepository.existsById(id)) {
            mascota.setIdmascota(id);
            return mascotaRepository.save(mascota);
        }
        return null;
    }

    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}

