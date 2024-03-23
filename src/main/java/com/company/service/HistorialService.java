package com.company.service;

import com.company.model.Historial;
import com.company.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public List<Historial> getAllHistoriales() {
        return historialRepository.findAll();
    }

    public Optional<Historial> getHistorialById(Long id) {
        return historialRepository.findById(id);
    }

    public Historial createHistorial(Historial historial) {
        return historialRepository.save(historial);
    }

    public Historial updateHistorial(Long id, Historial historial) {
        if (historialRepository.existsById(id)) {
            historial.setIdhistorial(id);
            return historialRepository.save(historial);
        }
        return null;
    }

    public void deleteHistorial(Long id) {
        historialRepository.deleteById(id);
    }
}

