package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.repository.PacienteRepository;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente, PacienteDTO> {

    @Autowired
    PacienteRepository pacienteRepository;


    public Optional<Paciente> buscar(Long id) {
   return pacienteRepository.findById(id);
    }

    @Override
    public ResponseEntity atualizar(Long id) {

        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if(paciente.isEmpty()){
            return new ResponseEntity("Paciente inexistente.", HttpStatus.BAD_REQUEST);
        }



        return null;
    }


    @Override
    public List<PacienteDTO> buscarTodos() {
        List<Paciente> pacienteList = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Paciente p : pacienteList){
            pacienteDTOList.add(mapper.convertValue(p, PacienteDTO.class))      ;
        }
        return pacienteDTOList;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        Optional<Paciente> paciente = buscar(id);

       if(paciente.isEmpty()){
           return new ResponseEntity("Paciente inexistente.", HttpStatus.BAD_REQUEST);
       }
       pacienteRepository.deleteById(id);
        return new ResponseEntity("Paciente "+ paciente.get().getNome() + "excluído com sucesso.", HttpStatus.OK);
    }

    public ResponseEntity salvar(Paciente paciente){
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = pacienteRepository.save(paciente);
            return new ResponseEntity( "Paciente "+pacienteSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar paciente", HttpStatus.BAD_REQUEST);
        }
    }

    public Paciente buscarPorRg(String rg) {
        return pacienteRepository.findByRg(rg);
    }
}
