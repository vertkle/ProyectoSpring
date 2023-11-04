package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.CalificacionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionEmpresaRepository extends JpaRepository<CalificacionEmpresa, Integer> {
}
