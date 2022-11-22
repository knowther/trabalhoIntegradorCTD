package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.service.impl.DentistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {

//    @GetMapping("/buscar/{numPedido}/{nomeUsuario}")
//    public String buscar(@PathVariable int numPedido, @PathVariable String nomeUsuario){
//        return "Numero pedido: " + numPedido + " - nome Usuario: " + nomeUsuario;
//    }

//    @GetMapping("/buscar")
//    public String buscar(@RequestParam("numPedido") int numPedido,
//                         @RequestParam("nomeUsuario") String nomeUsuario){
//        return "Numero pedido: " + numPedido + " - nome Usuario: " + nomeUsuario;
//    }

    DentistaService service = new DentistaService();

    @GetMapping("/buscar")
    public List<Dentista> buscarTodos(){
        return service.buscarTodos();
    }

    @PostMapping()
    public String salvar(@RequestBody Dentista dentista){
        return service.salvar(dentista);
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
