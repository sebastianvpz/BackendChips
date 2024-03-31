package com.company.controller;

import com.company.model.Credenciales;
import com.company.model.Usuario;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credenciales credenciales) {
        String username = credenciales.getUsername();
        String password = credenciales.getPassword();

        Usuario usuarioAutenticado = authService.autenticar(username, password);

        if (usuarioAutenticado != null) {
            if (passwordEncoder.matches(password, usuarioAutenticado.getPassword())) {
                System.out.println("Contraseña válida");
                return ResponseEntity.ok(usuarioAutenticado);
            } else {
                System.out.println("Contraseña no válida");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales no válidas");
            }
        } else {
            System.out.println("Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales no válidas");
        }
    }
}
