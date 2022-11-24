package com.dh.trabalhoIntegrador.repository;

import com.dh.trabalhoIntegrador.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
