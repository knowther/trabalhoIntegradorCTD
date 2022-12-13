package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Endereco;
import com.dh.trabalhoIntegrador.repository.EnderecoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    static final Logger logger = LogManager.getLogger(EnderecoService.class);
    @Autowired
    EnderecoRepository repository;

    public ResponseEntity salvarEndereco(Endereco endereco) {
        logger.info("Iniciando salvamento de endereco");
        try {
            logger.info("Salvando novo endereco");
            Endereco enderecoSalvo = repository.save(endereco);
            return new ResponseEntity("Endereco com id " + enderecoSalvo.getId() + " foi cadastrado com sucesso.", HttpStatus.CREATED);
        }catch (Exception e) {
            logger.error("Erro ao salvar endereco");
            return new ResponseEntity("Erro ao cadastrar endereço, tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity buscarEnderecoPorId(Long id){
        Optional<Endereco> endereco = repository.findById((id));
        logger.info("Iniciando busca de endereco por Id");
        if (endereco.isEmpty()) {
            logger.error("Id de endereco informado não existe");
            return new ResponseEntity("O endereço informado não existe.", HttpStatus.BAD_REQUEST);
        }
        repository.findById(id);
        logger.info("Endereco encontrado com sucesso");
        return new ResponseEntity("Endereço encontrado com sucesso", HttpStatus.OK);
    }


    public ResponseEntity deletarEndereco(Long id) {
        logger.info("Pesquisando endereco para deletar");
        Optional<Endereco> endereco = repository.findById(id);
        if(endereco.isEmpty()) {
            logger.error("Endereco não encontrado");
            return new ResponseEntity("O endereço informado não existe.", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        logger.info("Endereco excluido com sucesso");
        return new ResponseEntity("Endereço excluído com sucesso.", HttpStatus.OK);
    }


}
