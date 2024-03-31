package com.company.controller;

import com.company.model.Cita;
import com.company.model.Usuario;
import com.company.service.CitaService;
import com.company.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
@CrossOrigin("*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> getAllCitas() {
        List<Cita> citas = citaService.getAllCitas();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable("id") Long id) {
        Optional<Cita> cita = citaService.getCitaById(id);
        return cita.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<Cita>> getCitasByUsuarioId(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(idUsuario);
        if (usuarioOptional.isPresent()) {
            List<Cita> citas = citaService.getCitasByUsuarioId(idUsuario);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Cita createdCita = citaService.createCita(cita);
        return new ResponseEntity<>(createdCita, HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Cita> updateCita(@PathVariable("id") Long id, @RequestBody Cita cita) {
        Cita updatedCita = citaService.updateCita(id, cita);
        if (updatedCita != null) {
            return new ResponseEntity<>(updatedCita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable("id") Long id) {
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
