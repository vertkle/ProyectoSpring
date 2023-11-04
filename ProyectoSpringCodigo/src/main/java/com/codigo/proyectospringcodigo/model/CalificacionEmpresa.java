package com.codigo.proyectospringcodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "calificacion_empresa")
public class CalificacionEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int calificacion_id;
    private String ip_registro;
    @Column(nullable = false)
    private int puntuacion;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonIgnore
    private Empresa empresa;
}
