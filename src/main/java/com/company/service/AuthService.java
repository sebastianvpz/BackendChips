package com.company.service;

import com.company.model.Usuario;
import com.company.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario autenticar(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        } else {
            return null;
        }
    }

}
