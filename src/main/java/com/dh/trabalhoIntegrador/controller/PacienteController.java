package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController implements Serializable {

    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value = "listartodos", method = RequestMethod.GET)
    public List<Paciente> getAllPacientes(){
        return pacienteService.buscarTodos();
    }

}
