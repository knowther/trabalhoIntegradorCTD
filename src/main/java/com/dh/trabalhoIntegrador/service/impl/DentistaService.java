package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.repository.DentistaRepository;
import com.dh.trabalhoIntegrador.model.Dentista;
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
public class DentistaService implements IService<Dentista, DentistaDTO> {

    @Autowired
    DentistaRepository dentistaRepository;

    @Override
    public DentistaDTO buscar(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        Dentista dentista = dentistaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id inexistente na base de dados, verifique."));
        return mapper.convertValue(dentista, DentistaDTO.class);
    }


    @Override
    public Dentista salvar(Dentista dentista) throws ResourceNotFoundException {
        Dentista dentistaExists = dentistaRepository.findByNumMatricula(dentista.getNumMatricula()).orElseThrow(() -> new ResourceNotFoundException("RG já encontra-se cadastrado na base de dados."));
        Dentista dentistaSalvo = dentistaRepository.save(dentista);
        return dentistaSalvo;
    }


    @Override
    public List<DentistaDTO> buscarTodos() {
        List<Dentista> dentistaList = dentistaRepository.findAll();
        List<DentistaDTO> dentistaDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for (Dentista d : dentistaList) {
            dentistaDTOList.add(mapper.convertValue(d, DentistaDTO.class));
        }
        return dentistaDTOList;
    }

    @Override
    public ResponseEntity deletar(Long id) {
        Optional<Dentista> dentista = dentistaRepository.findById(id);
        if (dentista.isEmpty()) {
            return new ResponseEntity("Id informado não existe", HttpStatus.BAD_REQUEST);
        }
        dentistaRepository.deleteById(id);
        return new ResponseEntity("Dentista excluido com sucesso!", HttpStatus.OK);
    }


    public ResponseEntity buscarPorNumMatricula(String numMatricula) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentista = dentistaRepository.findByNumMatricula(numMatricula);
        if (dentista.isEmpty()) {
            return new ResponseEntity("Matricula inexistente, Dentista não encontrado", HttpStatus.BAD_REQUEST);
        }
        Dentista dentistaProcurado = dentista.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentistaProcurado, DentistaDTO.class);
        return new ResponseEntity(dentistaDTO, HttpStatus.OK);
    }


    public ResponseEntity alteracaoPacial(DentistaDTO dentistaDTO) {
        ObjectMapper mapper = new ObjectMapper();
        Optional<Dentista> dentistaOptional = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula());
        if (dentistaOptional.isEmpty()) {
            return new ResponseEntity("A matricula informada não existe", HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();

        if (dentistaDTO.getNome() != null) {
            dentista.setNome(dentistaDTO.getNome());
        }
        if (dentista.getSobrenome() != null) {
            dentista.setSobrenome(dentistaDTO.getSobrenome());
        }
        DentistaDTO dentistaAlterado = mapper.convertValue(dentistaRepository.save(dentista), DentistaDTO.class);
        return new ResponseEntity(dentistaAlterado, HttpStatus.OK);
    }

    public DentistaDTO alteracaoTotal(DentistaDTO dentistaDTO) throws ResourceNotFoundException {

        Dentista dentista = dentistaRepository.findByNumMatricula(dentistaDTO.getNumMatricula()).orElseThrow(() -> {
            return new ResourceNotFoundException("RG inexistente na base de dados, verifique.");
        });

        Dentista dentistaUpdate = dentista;
        dentistaUpdate.setNome(dentistaDTO.getNome());
        dentistaUpdate.setSobrenome(dentistaDTO.getSobrenome());
        dentistaUpdate.setNumMatricula(dentistaDTO.getNumMatricula());
        dentistaRepository.save(dentistaUpdate);
        return dentistaDTO;
    }
}
