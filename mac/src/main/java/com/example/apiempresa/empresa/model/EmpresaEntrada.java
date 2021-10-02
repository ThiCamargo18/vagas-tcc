package com.example.apiempresa.empresa.model;

import com.example.security.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaEntrada {
    private String login;
    private String senha;
    private String confirmarSenha;
    private String cnpj;
    private String razaoSocial;
    private String representante;
    private String telefone;
    private String rua;
    private String bairro;
    private List<RoleEntity> roles;
}
