package com.dh.trabalhoIntegrador.service.dao.impl;

import com.dh.trabalhoIntegrador.service.dao.IDao;
import com.dh.trabalhoIntegrador.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO implements IDao<Paciente> {

    public static List<Paciente> pacientes = new ArrayList<>();

    @Override
    public Paciente salvar(Paciente paciente) {
        pacientes.add(paciente);
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        return pacientes.get(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacientes;
    }
}
