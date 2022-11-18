package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente> {

    @Override
    public Paciente salvar(Paciente paciente) {
        return null;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        return Optional.of(new Paciente("Johnny", "Wesley", "123.123.134", LocalDate.now()));
    }

    @Override
    public List<Paciente> buscarTodos() {
        return Arrays.asList(new Paciente("Johnny", "Wesley", "123.123.134", LocalDate.now()));
    }
}
