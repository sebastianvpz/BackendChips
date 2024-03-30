package com.company.controller;

import com.company.model.Especie;
import com.company.service.EspecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especies")
@CrossOrigin("*")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("listar")
    public ResponseEntity<List<Especie>> getAllEspecies() {
        List<Especie> especies = especieService.getAllEspecies();
        return new ResponseEntity<>(especies, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Especie> getEspecieById(@PathVariable("id") Long id) {
        Optional<Especie> especie = especieService.getEspecieById(id);
        return especie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Especie> createEspecie(@RequestBody Especie especie) {
        Especie createdEspecie = especieService.createEspecie(especie);
        return new ResponseEntity<>(createdEspecie, HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Especie> updateEspecie(@PathVariable("id") Long id, @RequestBody Especie especie) {
        Especie updatedEspecie = especieService.updateEspecie(id, especie);
        if (updatedEspecie != null) {
            return new ResponseEntity<>(updatedEspecie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteEspecie(@PathVariable("id") Long id) {
        especieService.deleteEspecie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

