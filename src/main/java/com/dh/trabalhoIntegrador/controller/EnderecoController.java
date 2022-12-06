package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Endereco;
import com.dh.trabalhoIntegrador.model.dto.EnderecoDTO;
import com.dh.trabalhoIntegrador.service.impl.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping()
    public ResponseEntity salvar(@RequestBody Endereco endereco) {
        return enderecoService.salvarEndereco(endereco);
    }

    @GetMapping()
    public ResponseEntity buscarPorId(@RequestParam("id") Long id){
        return enderecoService.buscarEnderecoPorId(id);
    }

    @DeleteMapping()
    public ResponseEntity deletarEndereco(@RequestParam("id") Long id){
        return enderecoService.deletarEndereco(id);
    }
}
