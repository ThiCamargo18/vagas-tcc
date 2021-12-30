package com.example.apicandidato.cargo.model;

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
public class CargoSaida {
    private Long id;
    private String nome;
    private String descricao;
    private String nivel;
    private Long idUsuario;
    private List<TecnologiaEntity> tecnologias = new ArrayList<>();
    private List<FrameworkEntity> frameworks = new ArrayList<>();
    private List<FerramentaEntity> ferramentas = new ArrayList<>();
}
