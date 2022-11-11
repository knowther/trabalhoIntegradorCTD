package com.dh.trabalhoIntegrador.service;

import java.util.Optional;

public interface IService<T> {

    public T salvar (T t);

    public Optional<T> buscar (Integer id);
}
