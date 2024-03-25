package com.company.controller;

import com.company.model.Servicio;
import com.company.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        List<Servicio> servicios = servicioService.getAllServicios();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable("id") Long id) {
        Optional<Servicio> servicio = servicioService.getServicioById(id);
        return servicio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createServicio(@RequestParam("img") String imgString64,
                                            @RequestParam("titulo") String titulo,
                                            @RequestParam("descripcion") String descripcion
                                            ) {
        try{
        Servicio servicio = new Servicio();
        servicio.setImg(imgString64);
        servicio.setTitulo(titulo);
        servicio.setDescripcion(descripcion);
        servicioService.createServicio(servicio);
            return ResponseEntity.ok().body("{\"message\": \"Propuesta guardada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateServicio(@PathVariable Long id,
                                                   @RequestParam("img") String imgString64,
                                                   @RequestParam("titulo") String titulo,
                                                   @RequestParam("descripcion") String descripcion) {
        try{
        Servicio servicioExistente = servicioService.getServicioById(id).orElseThrow();
        servicioExistente.setImg(imgString64);
        servicioExistente.setTitulo(titulo);
        servicioExistente.setDescripcion(descripcion);
        servicioService.createServicio(servicioExistente);
            return ResponseEntity.ok().body("{\"message\": \"servicio editado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable("id") Long id) {
        servicioService.deleteServicio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

