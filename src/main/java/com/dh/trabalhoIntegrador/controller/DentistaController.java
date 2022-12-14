package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.exception.CadastroInvalidoException;
import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.model.dto.DentistaDTO;
import com.dh.trabalhoIntegrador.service.impl.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity salvar(@RequestBody DentistaDTO dentista) throws CadastroInvalidoException, ResourceNotFoundException {

            Dentista dentitaSalvo = dentistaService.salvar(dentista);
            return new ResponseEntity(dentista, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id) throws ResourceNotFoundException {
        DentistaDTO dentistaDTO = dentistaService.buscar(id);
        return new ResponseEntity(dentistaDTO, HttpStatus.FOUND);
    }

    @GetMapping("/buscarPorMatricula/{numMatricula}")
    public ResponseEntity buscarPorMatricula(@PathVariable String numMatricula){
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

    @PutMapping()
    public ResponseEntity alteracaoTotal( @RequestBody DentistaDTO dentistaDTO) throws ResourceNotFoundException {

        DentistaDTO dentistaDTOChange = dentistaService.alteracaoTotal(dentistaDTO);

        return new ResponseEntity(dentistaDTOChange, HttpStatus.OK);
    }

}
