package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmedico;

    private String nombre;

    @Temporal(TemporalType.TIME)
    private String horario;

    @Column(columnDefinition = "LONGTEXT")
    private String img;

    @Temporal(TemporalType.DATE)
    private String fecha_nacimiento;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cita> citas;
}

