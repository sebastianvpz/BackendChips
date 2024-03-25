package com.company.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    private String nombre;
    private String apellido;
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String img;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "idrol")
    @JsonIgnore
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Mascota> mascotas;
}