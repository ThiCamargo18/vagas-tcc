package com.example.apicandidato.cargo.model;

import com.example.apicandidato.ferramenta.model.FerramentaEntity;
import com.example.apicandidato.framework.model.FrameworkEntity;
import com.example.apicandidato.tecnologia.model.TecnologiaEntity;
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
    private List<TecnologiaEntity> tecnologia = new ArrayList<>();
    private List<FrameworkEntity> framework = new ArrayList<>();
    private List<FerramentaEntity> ferramenta = new ArrayList<>();
}
