package com.dh.trabalhoIntegrador.service;

import java.util.List;

public interface IService<T> {

    public String salvar (T t);

    public T buscar (Integer id);

    public List<T> buscarTodos();

}
