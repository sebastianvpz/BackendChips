package com.company.repository;

import com.company.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT c FROM Cita c WHERE c.mascota.usuario.idusuario = ?1")
    List<Cita> findByUsuarioId(Long usuarioId);
}

