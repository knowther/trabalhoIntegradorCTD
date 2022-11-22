package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController implements Serializable {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping()
    public List<Paciente> getAllPacientes(){
        return pacienteService.buscarTodos();
    }

//    @GetMapping()
//    public Paciente buscar(@RequestParam("numPaciente") int numPaciente){
//        return pacienteService.buscar(numPaciente);
//    }

    @PostMapping()
    public String salvar(@RequestBody Paciente paciente){
        return pacienteService.salvar(paciente);
    }


}
