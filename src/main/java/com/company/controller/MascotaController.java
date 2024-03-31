package com.company.controller;

import com.company.model.Especie;
import com.company.model.Mascota;
import com.company.model.Usuario;
import com.company.service.EspecieService;
import com.company.service.MascotaService;
import com.company.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin("*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EspecieService especieService;

    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> getAllMascotas() {
        List<Mascota> mascotas = mascotaService.getAllMascotas();
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable("id") Long id) {
        Optional<Mascota> mascota = mascotaService.getMascotaById(id);
        return mascota.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<List<Mascota>> getMascotasByUsuarioId(@PathVariable("idUsuario") Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(idUsuario);
        if (usuarioOptional.isPresent()) {
            List<Mascota> mascotas = usuarioOptional.get().getMascotas();
            return new ResponseEntity<>(mascotas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMascota(@RequestParam("img") String imgString64,
                                                 @RequestParam("nombre") String nombre,
                                                 @RequestParam("sexo") Boolean sexo,
                                                 @RequestParam("edad") Integer edad,
                                                 @RequestParam("especieId") Long especieId,
                                                 @RequestParam("usuarioId") Long usuarioId) {
        try {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId).orElseThrow();
        if (usuario == null) {
            return ResponseEntity.badRequest().body("El usuario con ID " + usuarioId + " no existe");
        }

        Especie especie = especieService.getEspecieById(especieId).orElseThrow();
        if (especie == null) {
            return ResponseEntity.badRequest().body("El usuario con ID " + usuarioId + " no existe");
        }

        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setSexo(sexo);
        mascota.setEdad(edad);
        mascota.setImg(imgString64);
        mascota.setEspecie(especie);
        mascota.setUsuario(usuario);
        mascotaService.createMascota(mascota);
        return ResponseEntity.ok().body("{\"message\": \"Mascota guardada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateMascota(@PathVariable Long id,
                                                 @RequestParam("img") String imgString64,
                                                 @RequestParam("nombre") String nombre,
                                                 @RequestParam("sexo") Boolean sexo,
                                                 @RequestParam("edad") Integer edad,
                                                 @RequestParam("especieId") Long especieId,
                                                 @RequestParam("usuarioId") Long usuarioId) {

        try {

        Mascota mascotaExistente = mascotaService.getMascotaById(id).orElseThrow();
        if (mascotaExistente == null) {
            return ResponseEntity.badRequest().body("La propuesta a editar no existe");
        }

        Usuario usuario = usuarioService.getUsuarioById(usuarioId).orElseThrow();
        if (usuario == null) {
            return ResponseEntity.badRequest().body("El usuario con ID " + usuarioId + " no existe");
        }

        Especie especie = especieService.getEspecieById(especieId).orElseThrow();
        if (especie == null) {
            return ResponseEntity.badRequest().body("El usuario con ID " + usuarioId + " no existe");
        }

        mascotaExistente.setNombre(nombre);
        mascotaExistente.setSexo(sexo);
        mascotaExistente.setEdad(edad);
        mascotaExistente.setImg(imgString64);
        mascotaExistente.setEspecie(especie);
        mascotaExistente.setUsuario(usuario);
        mascotaService.createMascota(mascotaExistente);

            return ResponseEntity.ok().body("{\"message\": \"Mascota editada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable("id") Long id) {
        mascotaService.deleteMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

