package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.service.dao.impl.PacienteDAO;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.dh.trabalhoIntegrador.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IService<Paciente> {


   private PacienteDAO pacienteDAO = new PacienteDAO();

    @Override
    public String salvar(Paciente paciente) {
        pacienteDAO.salvar(paciente);
        return "Paciente " +paciente.getNome()+ " salvo com sucesso!";
    }

    @Override
    public Paciente buscar(Integer id) {
     return pacienteDAO.buscar(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteDAO.buscarTodos();
    }
}
