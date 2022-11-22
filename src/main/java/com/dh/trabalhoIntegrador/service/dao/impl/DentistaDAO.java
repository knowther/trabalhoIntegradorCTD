package com.dh.trabalhoIntegrador.service.dao.impl;

import com.dh.trabalhoIntegrador.service.dao.IDao;
import com.dh.trabalhoIntegrador.model.Dentista;

import java.util.ArrayList;
import java.util.List;

public class DentistaDAO implements IDao<Dentista> {

    public static List<Dentista> listDentistas = new ArrayList<>();

    @Override
    public Dentista salvar(Dentista dentista) {
        listDentistas.add(dentista);
        return dentista;
    }

    @Override
    public Dentista buscar(Integer id) {
        return listDentistas.get(id);
    }

    @Override
    public List<Dentista> buscarTodos() {
        return listDentistas;
    }
}
