package com.example.apiempresa.entrevista.model;

import com.example.apicandidato.enums.Presenca;
import com.example.apicandidato.mail.Mensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrevistaEntrada {
    private Long id;
    private String nomeCliente;
    private String horario;
    private String data;
    private String local;
    private Presenca presenca;
    private String cpfCliente;
    private Long idCliente;
    private Long idCandidato;
    private Mensagem mensagemEmail;
}
