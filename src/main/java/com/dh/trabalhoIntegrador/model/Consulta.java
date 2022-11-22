package com.dh.trabalhoIntegrador.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Consulta {

    private Paciente paciente;
    private Dentista dentista;
    private Timestamp dataConsulta;


}
