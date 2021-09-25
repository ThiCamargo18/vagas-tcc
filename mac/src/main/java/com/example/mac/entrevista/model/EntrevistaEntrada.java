package com.example.mac.entrevista.model;

import com.example.mac.enums.Presenca;
import com.example.mac.mail.Mensagem;
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
    private Mensagem mensagemEmail;
}
