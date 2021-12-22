package com.example.apiempresa.entrevista.model;

import com.example.apicandidato.enums.Presenca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "entrevista")
public class EntrevistaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "horario")
    private String horario;

    @Column(name = "data")
    private String data;

    @Column(name = "local")
    private String local;

    @Column(name = "presenca")
    @Enumerated(EnumType.STRING)
    private Presenca presenca;

    @Column(name = "idCliente")
    private Long idCliente;
}
