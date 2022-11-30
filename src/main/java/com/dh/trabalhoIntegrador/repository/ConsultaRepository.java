package com.dh.trabalhoIntegrador.repository;

import com.dh.trabalhoIntegrador.model.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
