package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import com.dh.trabalhoIntegrador.utils.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/paciente")
public class PacienteController implements Serializable {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity salvar(@RequestBody @Valid Paciente paciente) throws ResourceNotFoundException {

        return pacienteService.salvar(paciente);
    }


    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDTO pacienteDTO = pacienteService.buscar(id);
        return new ResponseEntity(pacienteDTO, HttpStatus.FOUND);
    }

    @GetMapping("/buscarRg/{rg}")
    public ResponseEntity buscarPorRG(@PathVariable String rg) throws ResourceNotFoundException {
        PacienteDTO pacienteDTO = pacienteService.buscarPorRg(rg);

        return new ResponseEntity(pacienteDTO, HttpStatus.FOUND);
    }


    @GetMapping("/buscarTodos")
    public List<PacienteDTO> getAllPacientes(){
        return pacienteService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.deletar(id);
    }


    @PutMapping
    // TODO: Implementar alteração completa - Paciente
    public ResponseEntity alteracaoCompleta(@RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        PacienteDTO pacienteDTOChange = pacienteService.alteracaoTotal(pacienteDTO);
        return new ResponseEntity(pacienteDTOChange, HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity alteracaoParcial(@RequestBody @Valid PacienteDTO pacienteDTO){
        PacienteDTO pacienteDTOChange = pacienteService.alteracaoPacial(pacienteDTO);
        if(pacienteDTOChange == null){
            return new ResponseEntity("Erro ao alterar Paciente", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Paciente alterado com sucesso!", HttpStatus.OK);

    }


}
