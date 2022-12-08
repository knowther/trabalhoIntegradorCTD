package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
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


    public PacienteDTO buscar(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RG inexistente na base de dados, verifique."));
        return mapper.convertValue(paciente, PacienteDTO.class);
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
    public ResponseEntity deletar(Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado, verifique."));

       pacienteRepository.deleteById(id);
        return new ResponseEntity("Paciente "+ paciente.getNome() + "excluído com sucesso.", HttpStatus.OK);
    }

    public ResponseEntity salvar(Paciente paciente) throws ResourceNotFoundException {
        Paciente pacienteSalvo = null;
            
            if(this.buscarPorRg(paciente.getRg()) == null){
                paciente.setDataCadastro(Timestamp.from(Instant.now()));
                pacienteSalvo = pacienteRepository.save(paciente);
            }else{
                return new ResponseEntity("RG já existente na base de dados.", HttpStatus.BAD_REQUEST);
            }
         
            return new ResponseEntity( "Paciente "+pacienteSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);

    }

    public PacienteDTO buscarPorRg(String rg) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteRepository.findByRg(rg).orElseThrow(() -> {
            return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");
        });

        return mapper.convertValue(paciente, PacienteDTO.class);


    }

    public PacienteDTO alteracaoPacial(PacienteDTO pacienteDTO){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Paciente> pacienteOptional = pacienteRepository.findByRg(pacienteDTO.getRg());
        if(pacienteOptional.isEmpty()){
            return null;
        }
        Paciente paciente = pacienteOptional.get();

        if(pacienteDTO.getNome() != null){
            paciente.setNome(pacienteDTO.getNome());
        }
        if(pacienteDTO.getRg() != null){
            paciente.setRg(pacienteDTO.getRg());
        }
        if(pacienteDTO.getEndereco() != null){
            paciente.setEndereco(pacienteDTO.getEndereco());
        }
        if(pacienteDTO.getSobrenome() != null){
            paciente.setSobrenome(pacienteDTO.getSobrenome());
        }

        return mapper.convertValue(pacienteRepository.save(paciente), PacienteDTO.class);
    }

    public PacienteDTO alteracaoTotal(PacienteDTO pacienteDTO) throws ResourceNotFoundException {

        Paciente paciente = pacienteRepository.findByRg(pacienteDTO.getRg()).orElseThrow(() -> {return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");});

        Paciente pacienteUpdate = paciente;
        pacienteUpdate.setNome(pacienteDTO.getNome());
        pacienteUpdate.setSobrenome(pacienteDTO.getSobrenome());
        pacienteUpdate.setEndereco(pacienteDTO.getEndereco());
        pacienteUpdate.setRg(pacienteDTO.getRg());
        pacienteRepository.save(pacienteUpdate);
        return pacienteDTO;
    }
}
