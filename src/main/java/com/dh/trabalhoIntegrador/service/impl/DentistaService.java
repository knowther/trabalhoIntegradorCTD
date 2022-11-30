package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.repository.DentistaRepository;
import com.dh.trabalhoIntegrador.model.Dentista;
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
public class DentistaService implements IService<Dentista, DentistaDTO> {


    @Autowired
    DentistaRepository dentistaRepository;

    @Override
    public ResponseEntity salvar(Dentista dentista) {
        dentistaRepository.save(dentista);
        return new ResponseEntity("", HttpStatus.CREATED);
    }

    @Override
    public Optional<Dentista> buscar(Long id) {
        return dentistaRepository.findById(id);

    }

    @Override
    public ResponseEntity atualizar(Long id) {
        return null;
    }

    @Override
    public List<DentistaDTO> buscarTodos() {
        List<Dentista> dentistaList = dentistaRepository.findAll();
        List<DentistaDTO> dentistaDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Dentista d: dentistaList){
            dentistaDTOList.add(mapper.convertValue(d, DentistaDTO.class));
        }

        return dentistaDTOList ;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        return null;
    }

}
