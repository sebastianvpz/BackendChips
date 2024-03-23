package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "especies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idespecie;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mascota> mascotas;
}
