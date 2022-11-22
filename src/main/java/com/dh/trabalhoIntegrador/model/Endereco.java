package com.dh.trabalhoIntegrador.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {


    private int Id;
    private String logradouro;
    private String Cidade;
    private String Estado;
}
