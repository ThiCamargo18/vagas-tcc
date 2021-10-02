package com.example.apicandidato.experiencia.model;

import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienciaSaida {
    private Long id;
    private Experiencia possui;
    private String cargo;
    private String dataInicio;
    private String dataFim;
    private String salario;
    private CategoriaEnum categoria;
    private Long idUsuario;
}
