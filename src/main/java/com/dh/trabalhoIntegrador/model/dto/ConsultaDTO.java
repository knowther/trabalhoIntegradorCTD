package com.dh.trabalhoIntegrador.model.dto;

import com.dh.trabalhoIntegrador.model.Dentista;
import com.dh.trabalhoIntegrador.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaDTO {
    @JsonIgnore
    private Long Id;
    private String codConsulta;
    private Timestamp dataConsulta;
    private PacienteDTO paciente;
    private DentistaDTO dentista;
}
