package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.model.Consulta;
import com.dh.trabalhoIntegrador.model.dto.ConsultaDTO;
import com.dh.trabalhoIntegrador.service.impl.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ConsultaDTO consulta) throws CadastroInvalidoException {
        consultaService.salvar(consulta);
        return new ResponseEntity(consulta, HttpStatus.CREATED);


    }

    @GetMapping
    public List<ConsultaDTO> buscarTodos(){
        return consultaService.buscarTodos();
    }

    @GetMapping("/buscarConsulta/{codConsulta}")
    public ResponseEntity buscarCodConsulta(@PathVariable String codConsulta){
        return consultaService.buscarCodConsulta(codConsulta);
    }

    @DeleteMapping()
    public ResponseEntity deletar(@RequestParam("codConsulta") String codConsulta){
        return consultaService.deletar(codConsulta);
    }
}
