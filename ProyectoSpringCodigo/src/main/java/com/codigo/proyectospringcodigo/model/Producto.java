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
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int producto_id;
    private String nombre_producto;
    private String descripcion;
    private String imagen_producto;
    @Column(length = 1)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<ProductoEmpresa> productoEmpresas = new ArrayList<>();


}
