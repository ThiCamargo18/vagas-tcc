package com.example.mac.experiencia.model;

import com.example.mac.enums.CategoriaEnum;
import com.example.mac.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
