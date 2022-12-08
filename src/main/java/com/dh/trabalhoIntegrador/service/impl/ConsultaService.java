package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.repository.ConsultaRepository;
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
public class ConsultaService implements IService<Consulta, ConsultaDTO> {

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public Consulta salvar(Consulta consulta){
        Consulta consultaSalva = consultaRepository.save(consulta);
        return consultaSalva;
    }

    @Override
    public ConsultaDTO buscar(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> {return new ResourceNotFoundException("");
        });

        return mapper.convertValue(consulta, ConsultaDTO.class);
    }

    @Override
    public ConsultaDTO alteracaoTotal(ConsultaDTO consultaDTO) throws ResourceNotFoundException {
        Consulta consulta = consultaRepository.findByCodConsulta(consultaDTO.getCodConsulta()).orElseThrow(() -> new ResourceNotFoundException("Consulta inexistente na base de dados, verifique."));

        Consulta consultaUpdate = consulta;
        consultaUpdate.setCodConsulta(consultaDTO.getCodConsulta());
        consultaUpdate.setDataConsulta(consultaDTO.getDataConsulta());
        consultaUpdate.setDentista(consultaDTO.getDentista());
        consultaUpdate.setPaciente(consultaDTO.getPaciente());
        consultaRepository.save(consultaUpdate);
        return consultaDTO;
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

    public ResponseEntity deletar(String codConsulta){
        Optional<Consulta> consulta = consultaRepository.findByCodConsulta(codConsulta);
        if (consulta.isEmpty()){
            return new ResponseEntity("Codigo de Consulta não existe", HttpStatus.BAD_REQUEST);
        }
        consultaRepository.deleteById(consulta.get().getId());
        return new ResponseEntity("Consulta excluida com sucesso com sucesso", HttpStatus.OK);

    }

    public ResponseEntity buscarCodConsulta(String codConsulta){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consulta = consultaRepository.findByCodConsulta(codConsulta);
        if (consulta.isEmpty()){
            return new ResponseEntity("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
        Consulta consultaPesquisada = consulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(consultaPesquisada, ConsultaDTO.class);
        return new ResponseEntity(consultaDTO,HttpStatus.OK);
    }

}
