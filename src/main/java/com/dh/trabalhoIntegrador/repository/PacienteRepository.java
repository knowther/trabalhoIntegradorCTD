package com.dh.trabalhoIntegrador.repository;

import com.dh.trabalhoIntegrador.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
