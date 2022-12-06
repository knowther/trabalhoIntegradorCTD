package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Endereco;
import com.dh.trabalhoIntegrador.model.dto.EnderecoDTO;
import com.dh.trabalhoIntegrador.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public ResponseEntity salvarEndereco(Endereco endereco) {
        try {
            Endereco enderecoSalvo = repository.save(endereco);
            return new ResponseEntity("Endereco com id " + enderecoSalvo.getId() + " foi cadastrado com sucesso.", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity("Erro ao cadastrar endereço, tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity buscarEnderecoPorId(Long id){
        Optional<Endereco> endereco = repository.findById((id));
        if (endereco.isEmpty()) {
            return new ResponseEntity("O endereço informado não existe.", HttpStatus.BAD_REQUEST);
        }
        repository.findById(id);
        return new ResponseEntity("Endereço encontrado com sucesso", HttpStatus.OK);
    }


    public ResponseEntity deletarEndereco(Long id) {
        Optional<Endereco> endereco = repository.findById(id);
        if(endereco.isEmpty()) {
            return new ResponseEntity("O endereço informado não existe.", HttpStatus.BAD_REQUEST);
        }
        repository.deleteById(id);
        return new ResponseEntity("Endereço excluído com sucesso.", HttpStatus.OK);
    }


}
