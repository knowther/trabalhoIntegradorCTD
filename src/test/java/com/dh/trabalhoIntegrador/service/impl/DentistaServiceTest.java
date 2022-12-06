package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Dentista;
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
    void salvar(){
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("MM554433");
        dentista.setNome("Joao");
        dentista.setSobrenome("Alguem");
        dentistaService.salvar(dentista);

        Assertions.assertTrue(dentistaService.dentistaRepository.findByNumMatricula("MM554433").isPresent());
    }

    //Teste do método buscarPorCro
    @Test
    void buscarPorMatricula(){
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("NA5533");
        dentista.setNome("Ayrton");
        dentista.setSobrenome("Senna");
        dentistaService.salvar(dentista);

        Assertions.assertTrue(dentistaService.buscarPorNumMatricula("NA5533").getStatusCodeValue() == 200);
    }

    //Testando o método deletar
    @Test
    void deletar(){
        Dentista dentista = new Dentista();
        dentista.setNumMatricula("JJ543210");
        dentista.setNome("Fernao");
        dentista.setSobrenome("Dias");
        dentistaService.salvar(dentista);

        dentistaService.deletar(dentistaService.dentistaRepository.findByNumMatricula("JJ543210").get().getId());
        //Verificando se o dentista foi apagado
        Assertions.assertTrue(dentistaService.buscarPorNumMatricula("JJ543210").getStatusCodeValue() != 200);
    }


}