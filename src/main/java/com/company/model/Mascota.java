package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmascota;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idespecie")
    private Especie especie;

    private String nombre;
    private Boolean sexo;
    private int edad;

    @Column(columnDefinition = "LONGTEXT")
    private String img;
}

