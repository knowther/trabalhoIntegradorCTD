package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.repository.ConsultaRepository;
import com.dh.trabalhoIntegrador.repository.DentistaRepository;
import com.dh.trabalhoIntegrador.repository.PacienteRepository;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService{

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    DentistaRepository dentistaRepository;


    public Consulta salvar(ConsultaDTO consulta){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Consulta consulta1 = mapper.convertValue(consulta, Consulta.class);

        Optional<Paciente> paciente = pacienteRepository.findByRg(consulta.getPaciente().getRg());
        Optional<Dentista> dentista = dentistaRepository.findByNumMatricula(consulta.getDentista().getNumMatricula());
        consulta1.setPaciente(paciente.get());
        consulta1.setDentista(dentista.get());
        consultaRepository.save(consulta1);
        return consulta1;
    }


    public ConsultaDTO buscar(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> {return new ResourceNotFoundException("");
        });

        return mapper.convertValue(consulta, ConsultaDTO.class);
    }

    public ConsultaDTO alteracaoTotal(ConsultaDTO consultaDTO) throws ResourceNotFoundException {
        Consulta consulta = consultaRepository.findByCodConsulta(consultaDTO.getCodConsulta()).orElseThrow(() -> new ResourceNotFoundException("Consulta inexistente na base de dados, verifique."));
        ObjectMapper mapper = new ObjectMapper();
        ConsultaDTO dto = mapper.convertValue(consulta, ConsultaDTO.class);
        ConsultaDTO consultaUpdate = dto;
        consultaUpdate.setCodConsulta(consultaDTO.getCodConsulta());
        consultaUpdate.setDataConsulta(consultaDTO.getDataConsulta());
        consultaUpdate.setDentista(consultaDTO.getDentista());
        consultaUpdate.setPaciente(consultaDTO.getPaciente());
        Consulta consulta1 = mapper.convertValue(consultaUpdate, Consulta.class);
        consultaRepository.save(consulta1);
        return consultaDTO;
    }



    public List<ConsultaDTO> buscarTodos() {
        List<Consulta> listConsulta = consultaRepository.findAll();
        List<ConsultaDTO> consultasDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Consulta c: listConsulta){
            consultasDTOList.add(mapper.convertValue(c, ConsultaDTO.class));
        }

        return consultasDTOList ;
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
        mapper.registerModule(new JavaTimeModule());
        Optional<Consulta> consulta = consultaRepository.findByCodConsulta(codConsulta);
        if (consulta.isEmpty()){
            return new ResponseEntity("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
        ConsultaDTO consultaDTO = mapper.convertValue(consulta.get(), ConsultaDTO.class);
        ConsultaDTO consultaPesquisada = consultaDTO;
        return new ResponseEntity(consultaDTO,HttpStatus.OK);
    }

}
