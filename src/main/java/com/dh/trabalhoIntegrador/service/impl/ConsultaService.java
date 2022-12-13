package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.repository.ConsultaRepository;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService implements IService<Consulta, ConsultaDTO> {
    static final Logger logger = LogManager.getLogger(ConsultaService.class);
    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public Consulta salvar(Consulta consulta){
        logger.info("Salvando uma consulta");
        Consulta consultaSalva = consultaRepository.save(consulta);
        return consultaSalva;
    }

    @Override
    public ConsultaDTO buscar(Long id) throws ResourceNotFoundException {
        logger.info("Buscando um consulta por Id");
        ObjectMapper mapper = new ObjectMapper();
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> {return new ResourceNotFoundException("");
        });

        return mapper.convertValue(consulta, ConsultaDTO.class);
    }

    @Override
    public ConsultaDTO alteracaoTotal(ConsultaDTO consultaDTO) throws ResourceNotFoundException {
        logger.info("Buscando consulta para alteracao");
        Consulta consulta = consultaRepository.findByCodConsulta(consultaDTO.getCodConsulta()).orElseThrow(() -> new ResourceNotFoundException("Consulta inexistente na base de dados, verifique."));
        logger.info("Efetuando alteracao de consulta");
        Consulta consultaUpdate = consulta;
        consultaUpdate.setCodConsulta(consultaDTO.getCodConsulta());
        consultaUpdate.setDataConsulta(consultaDTO.getDataConsulta());
        consultaUpdate.setDentista(consultaDTO.getDentista());
        consultaUpdate.setPaciente(consultaDTO.getPaciente());
        logger.info("Salvando consulta alterada com sucesso");
        consultaRepository.save(consultaUpdate);
        logger.info("Retornando lista de consulta");
        return consultaDTO;
    }


    @Override
    public List<ConsultaDTO> buscarTodos() {
        logger.info("Buscando todas as Consultas");
        List<Consulta> listConsulta = consultaRepository.findAll();
        List<ConsultaDTO> consultasDTOList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for(Consulta c: listConsulta){
            consultasDTOList.add(mapper.convertValue(c, ConsultaDTO.class));
        }
        logger.info("Retornando lista de consultas");
        return consultasDTOList ;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        return null;
    }

    public ResponseEntity deletar(String codConsulta){
        logger.info("Buscando consulta pelo código de consulta para deletar");
        Optional<Consulta> consulta = consultaRepository.findByCodConsulta(codConsulta);
        if (consulta.isEmpty()){
            logger.error("Retorno da pesquisa vazio, código de consulta não existe ");
            return new ResponseEntity("Codigo de Consulta não existe", HttpStatus.BAD_REQUEST);
        }
        consultaRepository.deleteById(consulta.get().getId());
        logger.info("Consulta deletada com sucesso");
        return new ResponseEntity("Consulta excluida com sucesso com sucesso", HttpStatus.OK);

    }

    public ResponseEntity buscarCodConsulta(String codConsulta){
        logger.info("Buscar consulta por código de consulta");
        ObjectMapper mapper = new ObjectMapper();
        Optional<Consulta> consulta = consultaRepository.findByCodConsulta(codConsulta);
        if (consulta.isEmpty()){
            logger.error("Código de consulta não encontrado");
            return new ResponseEntity("Consulta não encontrada", HttpStatus.BAD_REQUEST);
        }
        Consulta consultaPesquisada = consulta.get();
        ConsultaDTO consultaDTO = mapper.convertValue(consultaPesquisada, ConsultaDTO.class);
        logger.info("Consulta realizada com sucesso");
        return new ResponseEntity(consultaDTO,HttpStatus.OK);
    }

}
