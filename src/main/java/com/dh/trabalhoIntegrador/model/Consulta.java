package com.dh.trabalhoIntegrador.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @Column(nullable = false, length = 10, unique = true)
    private String codConsulta;

    @Column(nullable = false)
    private Timestamp dataConsulta;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Dentista dentista;


}
