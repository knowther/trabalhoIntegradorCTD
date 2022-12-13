package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.repository.PacienteRepository;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    static final Logger logger = LogManager.getLogger(EnderecoService.class);
    @Autowired
    PacienteRepository pacienteRepository;



    public PacienteDTO buscar(Long id) throws ResourceNotFoundException {
        logger.info("Pesquisando paciente por Id");
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RG inexistente na base de dados, verifique."));
        logger.info("Retornando paciente pesquisado");
        return mapper.convertValue(paciente, PacienteDTO.class);
    }


    @Override
    public List<PacienteDTO> buscarTodos() {
        logger.info("Buscando lista com todos os pacientes");
        List<Paciente> pacienteList = pacienteRepository.findAll();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Paciente p : pacienteList){
            pacienteDTOList.add(mapper.convertValue(p, PacienteDTO.class));
        }
        logger.info("Retornando lista de pacientes");
        return pacienteDTOList;
    }

    @Override
    public ResponseEntity deletar(Long id) throws ResourceNotFoundException {
        logger.info("Procurando paciente para deletar");
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado, verifique."));

       pacienteRepository.deleteById(id);
       logger.info("Paciente deletado com sucesso");
        return new ResponseEntity("Paciente "+ paciente.getNome() + "excluído com sucesso.", HttpStatus.OK);
    }

    public Paciente salvar(Paciente paciente) throws CadastroInvalidoException, ResourceNotFoundException {
        logger.info("Procurando paciente por RG");
        Optional<Paciente> pacienteExists = pacienteRepository.findByRg(paciente.getRg());
        Paciente pacienteSalvo = null;
        if(pacienteExists.isEmpty()){
            logger.info("Salvando novo paciente");
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            pacienteSalvo = pacienteRepository.save(paciente);
        }else{
            logger.error("Paciente já existente, não foi possível efetuar novo cadastro");
            throw new CadastroInvalidoException("Rg já existente na base de dados.");
        }
            return pacienteSalvo;
    }

    public PacienteDTO buscarPorRg(String rg) throws ResourceNotFoundException {
        logger.info("Busanco paciente por RG");
        ObjectMapper mapper = new ObjectMapper();
        Paciente paciente = pacienteRepository.findByRg(rg).orElseThrow(() -> {
            logger.error("Paciente não existe na base de dados");
            return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");
        });
        logger.info("Retornando paciente salvo");
        return mapper.convertValue(paciente, PacienteDTO.class);


    }

    public PacienteDTO alteracaoPacial(PacienteDTO pacienteDTO){
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Procurando paciente para alteracao");
        Optional<Paciente> pacienteOptional = pacienteRepository.findByRg(pacienteDTO.getRg());
        if(pacienteOptional.isEmpty()){
            logger.error("Paciente nao existe, não foi possivel efetuar alteracao");
            return null;
        }
        Paciente paciente = pacienteOptional.get();
        logger.info("Paciente encontrado, efetuando alteracao");
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
        logger.info("Salvando paciente alterado");
        return mapper.convertValue(pacienteRepository.save(paciente), PacienteDTO.class);
    }

    public PacienteDTO alteracaoTotal(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        logger.info("Procurando por paciente para efetuar alteracao");
        Paciente paciente = pacienteRepository.findByRg(pacienteDTO.getRg()).orElseThrow(() -> {
            logger.error("Paciente não encontrado");
            return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");});

        Paciente pacienteUpdate = paciente;
        pacienteUpdate.setNome(pacienteDTO.getNome());
        pacienteUpdate.setSobrenome(pacienteDTO.getSobrenome());
        pacienteUpdate.setEndereco(pacienteDTO.getEndereco());
        pacienteUpdate.setRg(pacienteDTO.getRg());
        logger.info("Salvando paciente alterado");
        pacienteRepository.save(pacienteUpdate);
        return pacienteDTO;
    }
}
