package com.dh.trabalhoIntegrador.service.dao.impl;

import com.dh.trabalhoIntegrador.service.dao.IDao;
import com.dh.trabalhoIntegrador.model.Dentista;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DentistaDAO implements IDao<Dentista> {

//    public List<Dentista> listDentistas = Arrays.asList(
//            new Dentista("Lucas","Ramalho","19101994"),
//            new Dentista("Jonny", "Ctd", "13548514")
//    );

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
