package com.dh.trabalhoIntegrador.security;

import com.dh.trabalhoIntegrador.model.Usuario;
import com.dh.trabalhoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuario.get();
    }
}
