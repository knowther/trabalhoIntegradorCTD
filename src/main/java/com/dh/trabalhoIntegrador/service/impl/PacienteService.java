package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;

import java.util.Optional;

public class PacienteService implements IService<Paciente> {

    @Override
    public Paciente salvar(Paciente paciente) {
        return null;
    }

    @Override
    public Optional<Paciente> buscar(Integer id) {
        return Optional.empty();
    }
}
