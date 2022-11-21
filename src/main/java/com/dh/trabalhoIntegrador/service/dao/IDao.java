package com.dh.trabalhoIntegrador.service.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    public void salvar (T t);

    public T buscar (Integer id);

    public List<T> buscarTodos();
}
