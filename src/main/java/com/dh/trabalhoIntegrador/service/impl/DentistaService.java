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
    public Optional<Dentista> buscar(Long id) {
        return dentistaRepository.findById(id);
    }


    @Override
    public ResponseEntity salvar(Dentista dentista) {
        try{
            Dentista dentistaSalvo = dentistaRepository.save(dentista);
            return new ResponseEntity("Dentista Dr. " +dentistaSalvo.getNome()+ " " +dentistaSalvo.getSobrenome()+ " cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar dentista", HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity deletar(Long id){
        Optional<Dentista> dentista = dentistaRepository.findById(id);
        if(dentista.isEmpty()){
            return new ResponseEntity("Id informado não existe", HttpStatus.BAD_REQUEST);
        }
        dentistaRepository.deleteById(id);
        return new ResponseEntity("Dentista excluido com sucesso!", HttpStatus.OK);
    }


    public ResponseEntity buscarPorNumMatricula(String numMatricula) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentista = dentistaRepository.findByNumMatricula(numMatricula);
        if (dentista.isEmpty()){
            return new ResponseEntity("Matricula inexistente, Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaProcurado = dentista.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentistaProcurado, DentistaDTO.class);
        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }


    public ResponseEntity alteracaoPacial(DentistaDTO dentistaDTO){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentistaOptional = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula());
        if(dentistaOptional.isEmpty()){
            return new ResponseEntity("A matricula informada não existe",HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();

        if(dentistaDTO.getNome() != null){
            dentista.setNome(dentistaDTO.getNome());
        }
        if(dentista.getSobrenome() != null){
            dentista.setSobrenome(dentistaDTO.getSobrenome());
        }
        DentistaDTO dentistaAlterado = mapper.convertValue(dentistaRepository.save(dentista), DentistaDTO.class);
        return new ResponseEntity(dentistaAlterado, HttpStatus.OK);
    }

    public ResponseEntity alteracaoTotal(DentistaDTO dentistaDTO) {

        Optional<Dentista> dentista = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula());

        if (dentista.isEmpty()){
            return new ResponseEntity("Matricula informada não existe", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaToUpdate = dentista.get();
        dentistaToUpdate.setNome(dentistaDTO.getNome());
        dentistaToUpdate.setSobrenome(dentistaDTO.getSobrenome());
        dentistaRepository.save(dentistaToUpdate);
        return new ResponseEntity("Alterado com sucesso", HttpStatus.OK);
    }


}
