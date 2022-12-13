package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.repository.DentistaRepository;
import com.dh.trabalhoIntegrador.model.Dentista;
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
public class DentistaService implements IService<Dentista, DentistaDTO> {
    static final Logger logger = LogManager.getLogger(DentistaService.class);
    @Autowired
    DentistaRepository dentistaRepository;

    @Override
    public DentistaDTO buscar(Long id) throws ResourceNotFoundException {
        logger.info("Busanco dentista por ID");
        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = dentistaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Id inexistente na base de dados, verifique."));
        logger.info("Retornando dentista pesquisado");
        return mapper.convertValue(dentista, DentistaDTO.class);
    }


    @Override
    public Dentista salvar(Dentista dentista) throws ResourceNotFoundException {
        logger.info("Verificando se matricula do dentista existe, antes de salvar");
        Dentista dentistaExists = dentistaRepository.findByNumMatricula(dentista.getNumMatricula()).orElseThrow(() -> new ResourceNotFoundException("RG já encontra-se cadastrado na base de dados."));
        logger.info("Salvando dentista");
        Dentista dentistaSalvo = dentistaRepository.save(dentista);
        logger.info("Retornando dentista salvo");
        return dentistaSalvo;
    }


    @Override
    public List<DentistaDTO> buscarTodos() {
        logger.info("Buscando todos os dentistas");
        List<Dentista> dentistaList = dentistaRepository.findAll();
        List<DentistaDTO> dentistaDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for (Dentista d : dentistaList) {
            dentistaDTOList.add(mapper.convertValue(d, DentistaDTO.class));
        }
        logger.info("Retornando lista de dentistas cadastrados");
        return dentistaDTOList;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        logger.info("Buscando dentista para deletar");
        Optional<Dentista> dentista = dentistaRepository.findById(id);
        if (dentista.isEmpty()) {
            logger.error("Id informado para o dentista não existe");
            return new ResponseEntity("Id informado não existe", HttpStatus.BAD_REQUEST);
        }
        dentistaRepository.deleteById(id);
        logger.info("Dentista excluido com sucesso");
        return new ResponseEntity("Dentista excluido com sucesso!", HttpStatus.OK);
    }


    public ResponseEntity buscarPorNumMatricula(String numMatricula) {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Procurando dentista por numero de matricula");
        Optional<Dentista> dentista = dentistaRepository.findByNumMatricula(numMatricula);
        if (dentista.isEmpty()) {
            logger.error("Numero de matricula informado não existe, retorno vazio");
            return new ResponseEntity("Matricula inexistente, Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaProcurado = dentista.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentistaProcurado, DentistaDTO.class);
        logger.info("Retornando dentista pesquisado");
        return new ResponseEntity(dentistaDTO, HttpStatus.OK);
    }


    public ResponseEntity alteracaoPacial(DentistaDTO dentistaDTO) {
        ObjectMapper mapper = new ObjectMapper();
        logger.info("Pesquisando dentista para alteracao");
        Optional<Dentista> dentistaOptional = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula());
        if (dentistaOptional.isEmpty()) {
            logger.error("Matricula de dentista informado não existe");
            return new ResponseEntity("A matricula informada não existe", HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();
        logger.info("Procedendo com alteracao de dentista");
        if (dentistaDTO.getNome() != null) {
            dentista.setNome(dentistaDTO.getNome());
        }
        if (dentista.getSobrenome() != null) {
            dentista.setSobrenome(dentistaDTO.getSobrenome());
        }
        logger.info("Salvando alteracao de dentista");
        DentistaDTO dentistaAlterado = mapper.convertValue(dentistaRepository.save(dentista), DentistaDTO.class);
        logger.info("Retornando Dentista alterado com sucesso");
        return new ResponseEntity(dentistaAlterado, HttpStatus.OK);
    }

    public DentistaDTO alteracaoTotal(DentistaDTO dentistaDTO) throws ResourceNotFoundException {

        Dentista dentista = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula()).orElseThrow(() -> {
            logger.error("Matricula de dentista informado não existe");
            return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");
        });

        Dentista dentistaUpdate = dentista;
        dentistaUpdate.setNome(dentistaDTO.getNome());
        dentistaUpdate.setSobrenome(dentistaDTO.getSobrenome());
        dentistaUpdate.setNumMatricula(dentistaDTO.getNumMatricula());
        logger.info("Salvando alteracao de dentista");
        dentistaRepository.save(dentistaUpdate);
        logger.info("Retornando Dentista alterado com sucesso");
        return dentistaDTO;
    }
}
