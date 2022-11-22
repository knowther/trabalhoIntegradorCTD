package com.dh.trabalhoIntegrador.model;

import com.dh.trabalhoIntegrador.utils.utils;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente implements Serializable {
    private int Id;
    private String nome;
    private String sobrenome;
    private String rg;
    private Timestamp dataCadastro;

}
