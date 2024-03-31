package com.company.repository;

import com.company.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsernameAndPassword(String username, String password);
    Usuario findByUsername(String username);
}
