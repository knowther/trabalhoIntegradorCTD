package com.dh.trabalhoIntegrador.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

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
    private  Long id;

    private String codConsulta;
    private Timestamp dataConsulta;

    @ManyToOne
    //@JoinColumn(name = "id")
    private Paciente paciente;

    @ManyToOne
    //@JoinColumn(name="id")
    private Dentista dentista;


}
