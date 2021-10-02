package com.example.apiempresa.empresa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaSaida {
    private String login;
    private String cnpj;
    private String razaoSocial;
    private String representante;
    private String telefone;
    private String rua;
    private String bairro;
}
