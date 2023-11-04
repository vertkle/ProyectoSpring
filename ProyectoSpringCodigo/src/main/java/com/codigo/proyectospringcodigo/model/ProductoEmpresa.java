package com.codigo.proyectospringcodigo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "producto_empresa")
public class ProductoEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodemp_id;
    private double precio;
    private int cantidad;
    @Column(length = 1)
    private int estado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonIgnore
    private Empresa empresa;
}
