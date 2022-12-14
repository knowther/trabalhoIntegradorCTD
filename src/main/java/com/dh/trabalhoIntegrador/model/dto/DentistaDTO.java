package com.dh.trabalhoIntegrador.model.dto;


import com.dh.trabalhoIntegrador.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DentistaDTO {
    @JsonIgnore
    private Long Id;
    private String nome;
    private String sobrenome;
    private String numMatricula;
    private UsuarioDTO usuario;




}
