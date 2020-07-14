package com.example.mac.recursosHumanos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursosHumanosEntrada {
    private Long id;
    private Long login;
    private Long senha;
    private String email;
    private Long idEmpresa;
}
