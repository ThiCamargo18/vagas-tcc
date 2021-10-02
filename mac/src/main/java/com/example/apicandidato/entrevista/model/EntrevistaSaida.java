package com.example.apicandidato.entrevista.model;

import com.example.apicandidato.enums.Presenca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrevistaSaida {
    private Long id;
    private String nomeCliente;
    private String horario;
    private String data;
    private String local;
    private Presenca presenca;
    private Long idCliente;
}
