package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
