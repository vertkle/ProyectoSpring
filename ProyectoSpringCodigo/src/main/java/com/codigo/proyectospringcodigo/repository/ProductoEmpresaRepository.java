package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.ProductoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoEmpresaRepository extends JpaRepository<ProductoEmpresa, Integer> {
}
