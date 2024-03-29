package com.example.apiempresa.entrevista.model;

import com.example.apicandidato.enums.Presenca;
import com.example.apiempresa.empresa.model.EmpresaSaida;
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
    private String nomeCandidato;
    private String emailCandidato;
    private String horario;
    private String data;
    private String local;
    private Presenca presenca;
    private Long idCliente;
    private Long idCandidato;
    private Long idEmpresa;
    private EmpresaSaida empresaSaida;
}
