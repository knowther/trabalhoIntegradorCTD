package com.dh.trabalhoIntegrador.service.impl;

import com.dh.trabalhoIntegrador.service.dao.impl.DentistaDAO;
import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.service.IService;

import java.util.List;

public class DentistaService implements IService<Dentista> {


    private DentistaDAO dentistaDAO = new DentistaDAO();

    @Override
    public String salvar(Dentista dentista) {
        dentistaDAO.salvar(dentista);
        return "Dentista " +dentista.getNome()+ " salvo com sucesso!";
    }

    @Override
    public Dentista buscar(Integer id) {
        return dentistaDAO.buscar(id);
    }

    @Override
    public List<Dentista> buscarTodos() {
        return dentistaDAO.buscarTodos();
    }
}
