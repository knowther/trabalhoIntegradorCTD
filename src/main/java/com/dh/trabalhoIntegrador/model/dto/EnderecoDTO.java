package com.dh.trabalhoIntegrador.model.dto;


import com.dh.trabalhoIntegrador.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {
    private Long Id;
    private String logradouro;
    private String cidade;
    private String estado;

}
