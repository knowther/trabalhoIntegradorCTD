package com.dh.trabalhoIntegrador.service.dao;

import com.dh.trabalhoIntegrador.model.Paciente;

import java.util.List;

public interface IDao<T> {
    public T salvar (T t);

    public T buscar (Integer id);

    public List<T> buscarTodos();
}
