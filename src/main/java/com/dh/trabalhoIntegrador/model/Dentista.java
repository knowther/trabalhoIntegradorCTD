package com.dh.trabalhoIntegrador.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Dentista {
    private int Id;
    private String nome;
    private String sobrenome;
    private String numMatricula;


}
