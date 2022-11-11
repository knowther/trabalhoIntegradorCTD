package com.dh.trabalhoIntegrador.model;

public class Dentista {
    private String nome;
    private String sobrenome;
    private String numMatricula;

    public Dentista(String nome, String sobrenome, String numMatricula) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numMatricula = numMatricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(String numMatricula) {
        this.numMatricula = numMatricula;
    }
}
