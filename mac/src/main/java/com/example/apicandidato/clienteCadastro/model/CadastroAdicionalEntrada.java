package com.example.apicandidato.clienteCadastro.model;

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
public class CadastroAdicionalEntrada {
    private List<Long> tecnologia = new ArrayList<>();
    private List<Long> framework = new ArrayList<>();
    private List<Long> ferramenta = new ArrayList<>();
}
