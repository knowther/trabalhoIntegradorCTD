package com.dh.trabalhoIntegrador.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Paciente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, length = 15, unique = true)
    private String rg;
    private Timestamp dataCadastro;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
