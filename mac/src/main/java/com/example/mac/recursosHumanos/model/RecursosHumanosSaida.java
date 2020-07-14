package com.example.mac.recursosHumanos.model;

import com.example.mac.empresa.model.EmpresaSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecursosHumanosSaida {
    private Long id;
    private Long login;
    private Long senha;
    private String email;
    private EmpresaSaida idEmpresa;
}
