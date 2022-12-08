package com.dh.trabalhoIntegrador.service;

import com.dh.trabalhoIntegrador.exception.ResourceNotFoundException;
import com.dh.trabalhoIntegrador.model.Paciente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface IService<T, A> {

    public ResponseEntity salvar (T t) throws ResourceNotFoundException;

    public A buscar (Long id) throws ResourceNotFoundException;

//    public A alteracaoParcial(A a);

    public A alteracaoTotal(A a) throws ResourceNotFoundException;

    public List<A> buscarTodos();

    public ResponseEntity deletar(Long id) throws ResourceNotFoundException;

}
