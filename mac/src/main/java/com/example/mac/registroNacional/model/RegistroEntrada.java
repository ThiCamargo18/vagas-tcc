package com.example.mac.registroNacional.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEntrada {
    private Long id;
    private String orgaoEmissor;
    private String estado;
    private String dataExpedicao;
    private String numero;
    private Long idUsuario;
}
