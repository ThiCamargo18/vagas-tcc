package com.example.apicandidato.registroNacional.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroSaida {
    private Long id;
    private String orgaoEmissor;
    private String estado;
    private String dataExpedicao;
    private String numero;
    private Long idCandidato;
}
