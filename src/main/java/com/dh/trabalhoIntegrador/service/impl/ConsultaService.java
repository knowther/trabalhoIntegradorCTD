package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.repository.ConsultaRepository;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService implements IService<Consulta, ConsultaDTO> {

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public ResponseEntity salvar(Consulta consulta){
        try {
            Consulta consultaSalva = consultaRepository.save(consulta);
            return new ResponseEntity(consultaSalva, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity("Não foi possível cadastrar a consulta",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<Consulta> buscar(Long id) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity atualizar(Long id) {
        return null;
    }

    @Override
    public List<ConsultaDTO> buscarTodos() {
        List<Consulta> listConsulta = consultaRepository.findAll();
        List<ConsultaDTO> consultasDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Consulta c: listConsulta){
            consultasDTOList.add(mapper.convertValue(c, ConsultaDTO.class));
        }

        return consultasDTOList ;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        return null;
    }

}
