package com.example.apiempresa.model;

import com.example.apicandidato.enums.CategoriaEnum;
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
public class FiltoEntrada {
    private String formacao;
    private String endereco;
    private CategoriaEnum categoria;
    private Long idCargo;
    private List<Long> tecnologias = new ArrayList<>();
    private List<Long> frameworks = new ArrayList<>();
    private List<Long> ferramentas = new ArrayList<>();
}
