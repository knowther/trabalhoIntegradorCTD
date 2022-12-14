package com.dh.trabalhoIntegrador.service.impl;


import com.dh.trabalhoIntegrador.model.Usuario;
import com.dh.trabalhoIntegrador.model.dto.UsuarioDTO;
import com.dh.trabalhoIntegrador.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    ObjectMapper mapper = new ObjectMapper();

    public ResponseEntity<UsuarioDTO> novoUsuario(UsuarioDTO usuarioNovoDto) {
        log.info("[UsuarioServiceImpl] [novoUsuario]");
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        try {
            Usuario usuario = mapper.convertValue(usuarioNovoDto,Usuario.class);
            usuario.setPassword(enc.encode(usuarioNovoDto.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(usuarioRepository.save(usuario),UsuarioDTO.class));
        }catch (Exception e){
            log.error("[UsuarioServiceImpl] [novoUsuario] Não foi possível criar novo usuario.");
            return new ResponseEntity("Não foi possível criar novo usuario ",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity buscarPerfil(String username) {
        System.out.println(username);
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if(usuario.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado");
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(usuario.get(), UsuarioDTO.class));
    }
}