package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Endereco;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.model.dto.UsuarioDTO;
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
    void findById() throws ResourceNotFoundException, CadastroInvalidoException {
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
       assertEquals(false, pacienteService.buscar(1L) == null);
//       pacienteService.deletar(1L);
    }

    @Test
    void findByRG() throws ResourceNotFoundException, CadastroInvalidoException {
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
        assertTrue(pacienteService.buscarPorRg(paciente.getRg()) != null);
        pacienteService.deletar(1L);
    }

    @Test
    void findAll(){
        assertTrue(pacienteService.buscarTodos().size() > 0);
    }

}