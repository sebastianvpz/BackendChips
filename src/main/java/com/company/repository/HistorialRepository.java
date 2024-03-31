package com.company.repository;

import com.company.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    @Query("SELECT h FROM Historial h INNER JOIN h.cita c INNER JOIN c.mascota m INNER JOIN m.usuario u WHERE u.idusuario = :usuarioId")
    List<Historial> findByUsuarioId(Long usuarioId);
}

