package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.ProductoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoEmpresaRepository extends JpaRepository<ProductoEmpresa, Integer> {

    @Query("SELECT pe FROM ProductoEmpresa pe WHERE pe.empresa.empresa_id = :empresaId")
    List<ProductoEmpresa> findByEmpresa_EmpresaId(int empresaId);
}
