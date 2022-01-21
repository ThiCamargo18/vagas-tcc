package com.example.apicandidato.habilidades.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadesEntrada {
    private String resumoProfissional;
    private String objetivos;
    private Long idCandidato;
}
