package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import com.dh.trabalhoIntegrador.utils.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
        return pacienteService.salvar(paciente);
    }


    @GetMapping("/{id}")
    public Optional<Paciente> buscar(@PathVariable Long id){
        return pacienteService.buscar(id);
    }

    @GetMapping("/buscarRg/{rg}")
    public Paciente buscarPorRG(@PathVariable String rg){
        return pacienteService.buscarPorRg(rg);
    }


    @GetMapping("/buscarTodos")
    public List<PacienteDTO> getAllPacientes(){
        return pacienteService.buscarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return pacienteService.deletar(id);
    }


    @PutMapping
    // TODO: Implementar alteração completa - Paciente
//    public ResponseEntity alteracaoCompleta(@RequestBody){
//
//    }


    @PatchMapping
    public ResponseEntity alteracaoParcial(@RequestBody @Valid PacienteDTO pacienteDTO){

        return pacienteService.alteracaoPacial(pacienteDTO);

    }


}
