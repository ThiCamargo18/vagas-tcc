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
public class CargoSaida2 {
    private List<CargoId> cargo = new ArrayList<>();
    private List<TecnologiaEntity> tecnologias = new ArrayList<>();
    private List<FrameworkEntity> frameworks = new ArrayList<>();
    private List<FerramentaEntity> ferramentas = new ArrayList<>();
}
