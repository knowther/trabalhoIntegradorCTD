package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Endereco;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService ;

    @BeforeAll
    static void doBefore(){


    }

    @BeforeEach
    void doEach(){

    }

    @Test
    void findById(){
        Endereco endereco = new Endereco(1, "R.", "C", "P" );
        Paciente paciente = new Paciente(1L, "Johnny", "Wesley", "123.456.122", new Timestamp(System.currentTimeMillis()), endereco);
        pacienteService.salvar(paciente);
//       assertEquals(false, pacienteService.buscar(1L) == null);
//       pacienteService.deletar(1L);
    }

    @Test
    void findByRG() throws ResourceNotFoundException {
        Endereco endereco = new Endereco(1, "R.", "C", "P" );
        Paciente paciente = new Paciente(1L, "Johnny", "Wesley", "123.456.122", new Timestamp(System.currentTimeMillis()), endereco);
        pacienteService.salvar(paciente);
//        assertTrue(!pacienteService.buscarPorRg(paciente.getRg()));
        pacienteService.deletar(1L);
    }

    @Test
    void findAll(){
        assertTrue(pacienteService.buscarTodos().size() > 0);
    }

}