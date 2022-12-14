package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistaServiceTest {

    @Autowired
    DentistaService dentistaService;

    @Test
    void salvar() throws ResourceNotFoundException, CadastroInvalidoException {
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("MM554438");
        dentista.setNome("Joao");
        dentista.setSobrenome("Alguem");
        dentistaService.salvar(dentista);

        Assertions.assertTrue(dentistaService.dentistaRepository.findByNumMatricula("MM554438").isPresent());

        dentistaService.deletar(dentistaService.dentistaRepository.findByNumMatricula("MM554438").get().getId());
    }
//
//    //Teste do método buscarPorCro
    @Test
    void buscarPorMatricula() throws ResourceNotFoundException, CadastroInvalidoException {
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("NA5538");
        dentista.setNome("Ayrton");
        dentista.setSobrenome("Senna");
        dentistaService.salvar(dentista);

        Assertions.assertTrue(dentistaService.buscarPorNumMatricula("NA5538").getStatusCodeValue() == 200);

        dentistaService.deletar(dentistaService.dentistaRepository.findByNumMatricula("NA5538").get().getId());
    }

    //Testando o método deletar
    @Test
    void deletar() throws ResourceNotFoundException, CadastroInvalidoException {
        DentistaDTO dentista = new DentistaDTO();
        dentista.setNumMatricula("JJ543210");
        dentista.setNome("Fernao");
        dentista.setSobrenome("Dias");
        dentistaService.salvar(dentista);

        dentistaService.deletar(dentistaService.dentistaRepository.findByNumMatricula("JJ543210").get().getId());
        //Verificando se o dentista foi apagado
        Assertions.assertTrue(dentistaService.buscarPorNumMatricula("JJ543210").getStatusCodeValue() != 200);
    }


}