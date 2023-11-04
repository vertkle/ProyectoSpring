package com.codigo.proyectospringcodigo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empresa_id;
    @Column(nullable = false)
    private String nombre_empresa;
    @Column(nullable = false, unique = true)
    private String ruc_empresa;
    private String logo_empresa;
    private String latitud;
    private String longitud;
    private String descripcion;
    private String celular;
    private String direccion;
    private String referencia_direccion;
    private Date created_at;
    @Column(length = 1)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Usuario>usuarioModels = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<CalificacionEmpresa> calificacionEmpresas = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<ProductoEmpresa>productoEmpresas = new ArrayList<>();

}
