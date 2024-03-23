package com.company.service;

import com.company.model.Servicio;
import com.company.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> getServicioById(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio createServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(Long id, Servicio servicio) {
        if (servicioRepository.existsById(id)) {
            servicio.setIdservicio(id);
            return servicioRepository.save(servicio);
        }
        return null;
    }

    public void deleteServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
