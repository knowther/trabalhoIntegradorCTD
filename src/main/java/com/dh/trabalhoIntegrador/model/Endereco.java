package com.dh.trabalhoIntegrador.model;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(nullable = false, length = 50)
    private String logradouro;
    @Column(nullable = false, length = 20)
    private String cidade;
    @Column(nullable = false, length = 20)
    private String estado;

}
