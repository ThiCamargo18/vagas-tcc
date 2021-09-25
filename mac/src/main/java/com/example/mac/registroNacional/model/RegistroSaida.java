package com.example.mac.registroNacional.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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
    private Long idUsuario;
}
