package com.codigo.proyectospringcodigo.repository;

import com.codigo.proyectospringcodigo.model.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
}
