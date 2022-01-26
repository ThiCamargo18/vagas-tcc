package com.example.apicandidato.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    private String remetente;
    private List<String> destinatarios;
    private String assunto;
    private String corpo;
}