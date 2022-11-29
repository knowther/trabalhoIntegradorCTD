package com.dh.trabalhoIntegrador.service;

import com.dh.trabalhoIntegrador.model.Paciente;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IService<T, A> {

    public ResponseEntity salvar (T t);

    public Optional<T> buscar (Long id);

    public List<A> buscarTodos();

    public  ResponseEntity deletar(Long id);

}
