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
@Table(name = "distrito")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int distrito_id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ubigeo;

    @OneToMany(mappedBy = "distrito")
    @JsonIgnore
    private List<Empresa> empresaModels = new ArrayList<>();

}
