package com.dh.trabalhoIntegrador.repository;

import com.dh.trabalhoIntegrador.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

    Optional<Dentista> findByNumMatricula(String numMatricula);

}

