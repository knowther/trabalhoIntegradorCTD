package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.service.impl.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Consulta consulta){
        return consultaService.salvar(consulta);
    }

    @GetMapping
    public List<ConsultaDTO> buscarTodos(){
        return consultaService.buscarTodos();
    }
}
