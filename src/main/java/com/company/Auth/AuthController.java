package com.company.Auth;

import com.company.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Verificar si el nombre de usuario y la contraseña no están vacíos
        if (request.getUsername() == null || request.getPassword() == null ||
                request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre de usuario y contraseña son obligatorios");
        }

        try {
            // Realizar la autenticación
            AuthResponse authResponse = authService.login(request);
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            // Manejar casos en los que la autenticación falle
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales no válidas");
        }
    }

    @PostMapping(value = "register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestParam("img") String imgString64,
                                      @RequestParam("nombre") String nombre,
                                      @RequestParam("apellido") String apellido,
                                      @RequestParam("email") String email,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam("rolId") Long rolId)
    {
        RegisterRequest request = RegisterRequest.builder()
                .username(username)
                .password(password)
                .nombre(nombre)
                .apellido(apellido)
                .email(email)
                .img(imgString64)
                .rolId(rolId)
                .build();

        return ResponseEntity.ok(authService.register(request));
    }



    /**
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
    }**/


}
