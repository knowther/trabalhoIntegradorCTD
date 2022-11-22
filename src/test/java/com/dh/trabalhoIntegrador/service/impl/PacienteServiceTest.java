package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Paciente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class PacienteServiceTest {
    static PacienteService pacienteService;

    @BeforeAll
    static void doBefore(){
        pacienteService = new PacienteService();
    }

    @BeforeEach
    void doEach(){
//        Paciente paciente = new Paciente("Johnny", "Wesley", "123.456.122", "");
//        pacienteService.salvar(paciente);
    }

    @Test
    void findById(){
       assertEquals(false, pacienteService.buscar(0) == null);
    }

    @Test
    void findAll(){
        assertTrue(pacienteService.buscarTodos().size() > 0);
    }

}