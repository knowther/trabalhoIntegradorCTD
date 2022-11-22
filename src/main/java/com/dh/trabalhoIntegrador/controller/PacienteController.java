package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.PacienteDTO;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import com.dh.trabalhoIntegrador.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController implements Serializable {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/buscarTodosgit st")
    public List<PacienteDTO> getAllPacientes(){
        return pacienteService.buscarTodos();
    }

    @PostMapping()
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){
       return pacienteService.salvar(paciente);
    }


}
