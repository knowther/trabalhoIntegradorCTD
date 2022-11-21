package com.dh.trabalhoIntegrador.service.dao.impl;

import com.dh.trabalhoIntegrador.service.dao.IDao;
import com.dh.trabalhoIntegrador.model.Dentista;

import java.util.ArrayList;
import java.util.List;

public class DentistaDAO implements IDao<Dentista> {

    public static List<Dentista> dentistas = new ArrayList<>();

    @Override
    public void salvar(Dentista dentista) {
        dentistas.add(dentista);

    }

    @Override
    public Dentista buscar(Integer id) {
        return dentistas.get(id);
    }

    @Override
    public List<Dentista> buscarTodos() {
        return dentistas;
    }
}
