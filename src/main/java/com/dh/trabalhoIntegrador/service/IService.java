package com.dh.trabalhoIntegrador.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    public void salvar (T t);

    public T buscar (Integer id);

    public List<T> buscarTodos();

}
