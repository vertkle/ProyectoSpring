package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query(value = "SELECT * FROM empresa where ruc_empresa = :ruc", nativeQuery = true)
    Optional<Empresa> findByRuc_empresa(@Param("ruc") String ruc);
}
