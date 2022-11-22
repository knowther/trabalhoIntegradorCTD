package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.service.dao.impl.PacienteDAO;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IService<Paciente, PacienteDTO> {


   private PacienteDAO pacienteDAO = new PacienteDAO();

    @Override
    public Paciente buscar(Integer id) {
       return pacienteDAO.buscar(id);
    }


    @Override
    public List<PacienteDTO> buscarTodos() {
        List<Paciente> pacienteList = pacienteDAO.buscarTodos();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for(Paciente p : pacienteList){
            pacienteDTOList.add(mapper.convertValue(p, PacienteDTO.class))      ;
        }
        return pacienteDTOList;
    }

    public ResponseEntity salvar(Paciente paciente){
        try{
            paciente.setDataCadastro(Timestamp.from(Instant.now()));
            Paciente pacienteSalvo = pacienteDAO.salvar(paciente);
            return new ResponseEntity( "Paciente "+pacienteSalvo.getNome()+" criado com sucesso", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Erro ao cadastrar paciente", HttpStatus.BAD_REQUEST);
        }
    }
}
