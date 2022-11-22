package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.service.impl.DentistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController implements Serializable {

    DentistaService dentistaService = new DentistaService();

    @GetMapping("/buscarTodos")
    public List<DentistaDTO> buscarTodos(){
        return dentistaService.buscarTodos();
    }

    @PostMapping()
    public ResponseEntity salvar(@RequestBody Dentista dentista){
        return dentistaService.salvar(dentista);
    }

    @DeleteMapping
    public String deletar(){
        return "Entrou delete";
    }

    @PatchMapping
    public String alteracaoParcial(){
        return "Entrou path";
    }


    @PutMapping
    public String alteracaototal(){
        return "Entrou put";
    }



}
