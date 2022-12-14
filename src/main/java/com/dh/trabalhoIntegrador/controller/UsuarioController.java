package com.dh.trabalhoIntegrador.controller;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.dto.UsuarioDTO;
import com.dh.trabalhoIntegrador.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping()
    public ResponseEntity novoUsuario(@RequestBody @Valid UsuarioDTO usuarioNovoDto){
        return usuarioService.novoUsuario(usuarioNovoDto);
    }

    @GetMapping("/perfil/{username}")
    public ResponseEntity buscarPerfil(@PathVariable String username) throws ResourceNotFoundException {
        return usuarioService.buscarPerfil(username);
    }

}
