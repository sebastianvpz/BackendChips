package com.company.controller;

import com.company.model.Rol;
import com.company.model.Usuario;
import com.company.service.RolService;
import com.company.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createUsuario(@RequestParam("img") String imgString64,
                                           @RequestParam("nombre") String nombre,
                                           @RequestParam("apellido") String apellido,
                                           @RequestParam("email") String email,
                                           @RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("rolId") Long rolId) {

        try{
        Rol rol = rolService.getRolById(rolId).orElseThrow();
        Usuario usuario = new Usuario();
        usuario.setImg(imgString64);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRol(rol);
        usuarioService.createUsuario(usuario);
            return ResponseEntity.ok().body("{\"message\": \"Mascota guardada exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PutMapping(value = "/editar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUsuario(@PathVariable Long id,
                                                 @RequestParam("img") String imgString64,
                                                 @RequestParam("nombre") String nombre,
                                                 @RequestParam("apellido") String apellido,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("username") String username,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("rolId") Long rolId) {

        try{
        Usuario usuarioExistente = usuarioService.getUsuarioById(id).orElseThrow();
        Rol rol = rolService.getRolById(rolId).orElseThrow();
        usuarioExistente.setImg(imgString64);
        usuarioExistente.setNombre(nombre);
        usuarioExistente.setApellido(apellido);
        usuarioExistente.setEmail(email);
        usuarioExistente.setUsername(username);
        usuarioExistente.setPassword(password);
        usuarioExistente.setRol(rol);
        usuarioService.createUsuario(usuarioExistente);
            return ResponseEntity.ok().body("{\"message\": \"usuario editado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

