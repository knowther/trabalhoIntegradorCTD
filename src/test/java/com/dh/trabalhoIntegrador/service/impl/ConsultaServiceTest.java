package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.*;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.model.dto.UsuarioDTO;
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
    void agendarConsulta() throws ResourceNotFoundException, CadastroInvalidoException {
        //Paciente
        UsuarioDTO usuario = new UsuarioDTO();
        PacienteDTO paciente = new PacienteDTO();
        paciente.setRg("11.111.11-1");
        usuario.setUsername(paciente.getRg());
        usuario.setPassword("123456");
        paciente.setNome("Lucas");
        paciente.setSobrenome("Ramalho");
        paciente.setUsuario(usuario);
        //Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Das Giestas");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        paciente.setEndereco(endereco);
        pacienteService.salvar(paciente);




        UsuarioDTO usuario1 = new UsuarioDTO();
        //Dentista
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("AB124");
        usuario1.setUsername(dentista.getNumMatricula());
        usuario1.setPassword("123456");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Abreu");
        dentista.setUsuario(usuario1);
        dentistaService.salvar(dentista);

        //Consulta
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("CO998811");
        consultaService.salvar(consulta);


        Assertions.assertTrue(consultaService.consultaRepository.findByCodConsulta("CO998811").isPresent());
    }

    @Test
    void buscarCodConsulta() throws ResourceNotFoundException, CadastroInvalidoException {
        //Paciente
        UsuarioDTO usuario = new UsuarioDTO();
        PacienteDTO paciente = new PacienteDTO();
        paciente.setRg("12346548");
        usuario.setUsername(paciente.getRg());
        usuario.setPassword("123456");
        paciente.setNome("Lucas");
        paciente.setSobrenome("Ramalho");
        paciente.setUsuario(usuario);
        //Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Das Giestas");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        paciente.setEndereco(endereco);
        pacienteService.salvar(paciente);




        UsuarioDTO usuario1 = new UsuarioDTO();
        //Dentista
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("DE14587");
        usuario1.setUsername(dentista.getNumMatricula());
        usuario1.setPassword("123456");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Abreu");
        dentista.setUsuario(usuario1);
        dentistaService.salvar(dentista);

        //Consulta
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("CO48536");
        consultaService.salvar(consulta);

        Assertions.assertTrue(consultaService.buscarCodConsulta("CO998811").getStatusCodeValue() == 200);
    }
//
    //Deletando consulta
    @Test
    void deletarConsulta() throws ResourceNotFoundException, CadastroInvalidoException {
        //Paciente
        UsuarioDTO usuario = new UsuarioDTO();
        PacienteDTO paciente = new PacienteDTO();
        paciente.setRg("15002987");
        usuario.setUsername(paciente.getRg());
        usuario.setPassword("123456");
        paciente.setNome("Lucas");
        paciente.setSobrenome("Ramalho");
        paciente.setUsuario(usuario);
        //Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Das Giestas");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        paciente.setEndereco(endereco);
        pacienteService.salvar(paciente);




        UsuarioDTO usuario1 = new UsuarioDTO();
        //Dentista
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("8547AC");
        usuario1.setUsername(dentista.getNumMatricula());
        usuario1.setPassword("123456");
        dentista.setNome("Dentisvaldo");
        dentista.setSobrenome("Abreu");
        dentista.setUsuario(usuario1);
        dentistaService.salvar(dentista);

        //Consulta
        ConsultaDTO consulta = new ConsultaDTO();
        consulta.setDataConsulta(Timestamp.valueOf("2022-12-01 00:00:00"));
        consulta.setDentista(dentista);
        consulta.setPaciente(paciente);
        consulta.setCodConsulta("CO87425");
        consultaService.salvar(consulta);
        //Deletando a consulta criada para o teste
        consultaService.deletar("DT0155247");
        Assertions.assertTrue(consultaService.buscarCodConsulta("DT0155247").getStatusCodeValue() != 200);
    }


}