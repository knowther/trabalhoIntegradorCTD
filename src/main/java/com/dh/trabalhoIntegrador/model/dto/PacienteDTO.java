package com.dh.trabalhoIntegrador.model.dto;

import com.dh.trabalhoIntegrador.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteDTO {
    @JsonIgnore
    private int Id;
    private String nome;
    private String sobrenome;
    private String rg;
    private LocalDate dataCadastro;

    private Endereco endereco;

    private UsuarioDTO usuario;

}
