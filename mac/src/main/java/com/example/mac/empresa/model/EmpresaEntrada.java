package com.example.mac.empresa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaEntrada {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String representante;
    private String telefone;
    private String rua;
    private String bairro;
}
