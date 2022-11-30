package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.service.impl.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController implements Serializable {

    @Autowired
    DentistaService dentistaService;


    @PostMapping()
    public ResponseEntity salvar(@RequestBody Dentista dentista){
        return dentistaService.salvar(dentista);
    }


    @GetMapping("/{id}")
    public Optional<Dentista> buscar(@PathVariable Long id){
        return dentistaService.buscar(id);
    }

    @GetMapping("/buscarPorMatricula/{numMatricula}")
    public Dentista buscarPorMatricula(@PathVariable String numMatricula){
        return dentistaService.buscarPorNumMatricula(numMatricula);
    }

    @GetMapping("/buscarTodos")
    public List<DentistaDTO> buscarTodos(){
        return dentistaService.buscarTodos();
    }


    @DeleteMapping
    public ResponseEntity deletar(@RequestParam("id") Long id){
        return dentistaService.deletar(id);
    }


    @PatchMapping
    public ResponseEntity alteracaoParcial(@RequestBody @Valid DentistaDTO dentistaDTO){
        return dentistaService.alteracaoPacial(dentistaDTO);
    }

    @PutMapping
    public String alteracaototal(){
        return "Entrou put";
    }


}
