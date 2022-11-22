package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.service.dao.impl.DentistaDAO;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistaService implements IService<Dentista, DentistaDTO> {


    private DentistaDAO dentistaDAO = new DentistaDAO();

    @Override
    public Dentista buscar(Integer id) {
        return dentistaDAO.buscar(id);
    }

    @Override
    public List<DentistaDTO> buscarTodos() {
        List<Dentista> dentistaList = dentistaDAO.buscarTodos();
        List<DentistaDTO> dentistaDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Dentista d: dentistaList){
            dentistaDTOList.add(mapper.convertValue(d, DentistaDTO.class));
        }

        return dentistaDTOList ;
    }

    @Override
    public ResponseEntity salvar(Dentista dentista) {
        try{
            Dentista dentistaSalvo = dentistaDAO.salvar(dentista);
            return new ResponseEntity("Dentista " +dentistaSalvo.getNome()+ " criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }
    }
}
