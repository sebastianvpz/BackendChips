package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcita;

    @ManyToOne
    @JoinColumn(name = "idmascota")
    @JsonIgnore
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "idmedico")
    @JsonIgnore
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    private Servicio servicio;

    private String motivo;

    @Temporal(TemporalType.DATE)
    private String fecha;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Historial> historiales;
}
