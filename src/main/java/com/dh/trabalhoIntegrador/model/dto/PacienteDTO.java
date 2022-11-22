package com.dh.trabalhoIntegrador.model.dto;

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
    private Timestamp dataCadastro;

    public LocalDate getDataCadastro(){
        return dataCadastro.toLocalDateTime().toLocalDate();
    }

    public LocalTime getHoraCadastro(){
        return dataCadastro.toLocalDateTime().toLocalTime();
    }
}
