package com.company.controller;

import com.company.model.Medico;
import com.company.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
@CrossOrigin("*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Medico>> getAllMedicos() {
        List<Medico> medicos = medicoService.getAllMedicos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable("id") Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        return medico.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMedico(@RequestParam("img") String imgString64,
                                               @RequestParam("nombre") String nombre,
                                               @RequestParam("horario") String horario,
                                               @RequestParam("fechaNacimiento") String fechaNacimiento) {
        try{
        Medico medico = new Medico();
        medico.setImg(imgString64);
        medico.setNombre(nombre);
        medico.setHorario(horario);
        medico.setFecha_nacimiento(fechaNacimiento);
        medicoService.createMedico(medico);
            return ResponseEntity.ok().body("{\"message\": \"Propuesta guardada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMedico(@PathVariable Long id,
                                               @RequestParam("img") String imgString64,
                                               @RequestParam("nombre") String nombre,
                                               @RequestParam("horario") String horario,
                                               @RequestParam("fechaNacimiento") String fechaNacimiento) {
        try{
        Medico medicoExistente = medicoService.getMedicoById(id).orElseThrow();
        medicoExistente.setImg(imgString64);
        medicoExistente.setNombre(nombre);
        medicoExistente.setHorario(horario);
        medicoExistente.setFecha_nacimiento(fechaNacimiento);
        medicoService.createMedico(medicoExistente);

            return ResponseEntity.ok().body("{\"message\": \"Médico editado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) {
        medicoService.deleteMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
