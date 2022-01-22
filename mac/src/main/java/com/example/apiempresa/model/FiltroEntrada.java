package com.example.apiempresa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroEntrada {
    private String formacao;
    private String endereco;
    private String categoria;
    private Long idCargo;
    private List<Long> tecnologia = new ArrayList<>();
    private List<Long> framework = new ArrayList<>();
    private List<Long> ferramenta = new ArrayList<>();
}
