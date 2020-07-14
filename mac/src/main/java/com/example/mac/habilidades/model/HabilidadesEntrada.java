package com.example.mac.habilidades.model;

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
    private Long id;
    private String resumoProfissional; //
    private String objetivos; //
    private List<String> descricao; //
    private Long idUsuario;
}