package com.company.controller;

import com.company.model.Historial;
import com.company.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historiales")
@CrossOrigin("*")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping
    public ResponseEntity<List<Historial>> getAllHistoriales() {
        List<Historial> historiales = historialService.getAllHistoriales();
        return new ResponseEntity<>(historiales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> getHistorialById(@PathVariable("id") Long id) {
        Optional<Historial> historial = historialService.getHistorialById(id);
        return historial.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Historial> createHistorial(@RequestBody Historial historial) {
        Historial createdHistorial = historialService.createHistorial(historial);
        return new ResponseEntity<>(createdHistorial, HttpStatus.CREATED);
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Historial> updateHistorial(@PathVariable("id") Long id, @RequestBody Historial historial) {
        Historial updatedHistorial = historialService.updateHistorial(id, historial);
        if (updatedHistorial != null) {
            return new ResponseEntity<>(updatedHistorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteHistorial(@PathVariable("id") Long id) {
        historialService.deleteHistorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
