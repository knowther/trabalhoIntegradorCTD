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

    @Autowired
    DentistaService dentistaService;

    @Autowired
    PacienteService pacienteService;

    @BeforeEach
    public void setUp(){

    }

    @Test
    void agendarConsulta() {
        //Paciente
        Paciente paciente = new Paciente();
        paciente.setRg("11.111.11-1");
        paciente.setNome("Lucas");
        paciente.setSobrenome("Ramalho");
        pacienteService.salvar(paciente);

        //Dentista
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("AB124");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Abreu");
        dentistaService.salvar(dentista);

        //Consulta
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("CO998811");
        consultaService.salvar(consulta);


        Assertions.assertTrue(consultaService.consultaRepository.findByCodConsulta("CO998811").isPresent());
    }

    @Test
    void buscarCodConsulta(){
        //Paciente
        Paciente paciente = new Paciente();
        paciente.setRg("22.222.22-2");
        paciente.setNome("Raquel");
        paciente.setSobrenome("Ramalho");
        pacienteService.salvar(paciente);

        //Dentista
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("AC5556");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Fagundes");
        dentistaService.salvar(dentista);

        //Consulta
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("CO559911");
        consultaService.salvar(consulta);

        Assertions.assertTrue(consultaService.buscarCodConsulta("CO559911").getStatusCodeValue() == 200);
    }

    //Deletando consulta
    @Test
    void deletarConsulta(){
        //Paciente
        Paciente paciente = new Paciente();
        paciente.setRg("22.222.22-1");
        paciente.setNome("Raquel");
        paciente.setSobrenome("Ramalho");
        pacienteService.salvar(paciente);

        //Dentista
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("AB555");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Fagundes");
        dentistaService.salvar(dentista);

        //Consulta
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("DT0155247");
        consultaService.salvar(consulta);

        //Deletando a consulta criada para o teste
        consultaService.deletar("DT0155247");
        Assertions.assertTrue(consultaService.buscarCodConsulta("DT0155247").getStatusCodeValue() != 200);
    }


}