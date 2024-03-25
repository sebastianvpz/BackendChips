package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idservicio;

    private String titulo;
    private String descripcion;

    @Column(columnDefinition = "LONGTEXT")
    private String img;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cita> citas;
}
