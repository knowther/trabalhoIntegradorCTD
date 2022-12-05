package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConsultaServiceTest {

    @Autowired
    ConsultaService consultaService;
    DentistaService dentistaService;
    PacienteService pacienteService;
    ObjectMapper mapper = new ObjectMapper();


    //Criando Dentista e Paciente

    @Test
    void cadastrarConsulta() {
//        Dentista dentista1 = new Dentista();
//        dentista1.setNome("Lucas");
//        dentista1.setSobrenome("Ramalho");
//        dentista1.setNumMatricula("AB1234");
//        //Salvando dentista
//        dentistaService.salvar(dentista1);
//
//        Paciente paciente1 = new Paciente();
//        paciente1.setNome("Joao");
//        paciente1.setSobrenome("Matos");
//        paciente1.setRg("11.111.111-1");
//        // Salvando paciente
//        pacienteService.salvar(paciente1);
//
//        Consulta consulta = new Consulta();
//        consulta.setCodConsulta("12345-5");
//        consulta.setDentista(dentistaService.buscarPorNumMatricula("AB1234"));
//        consulta.setPaciente(pacienteService.buscarPorRg("11.111.111-1"));
//        consulta.setDataConsulta(Timestamp.from(Instant.now()));

    }

}