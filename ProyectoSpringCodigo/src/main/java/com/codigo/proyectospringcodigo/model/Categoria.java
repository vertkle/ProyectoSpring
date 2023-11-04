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
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoria_id;
    @Column(nullable = false)
    private String nombre_categoria;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Producto>productos = new ArrayList<>();

}
