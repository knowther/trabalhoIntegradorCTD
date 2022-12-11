package com.dh.trabalhoIntegrador;

import com.dh.trabalhoIntegrador.model.Usuario;
import com.dh.trabalhoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class CreateUserRun implements ApplicationRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Usuario usuario = new Usuario();
        usuario.setUsername("johnny");
        usuario.setPassword(encoder.encode("123456"));

        usuarioRepository.save(usuario);

    }
}
