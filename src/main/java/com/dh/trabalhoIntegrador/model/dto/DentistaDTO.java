package com.dh.trabalhoIntegrador.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DentistaDTO {
    @JsonIgnore
    private int Id;
    private String nome;
    private String sobrenome;
    private String numMatricula;




}
