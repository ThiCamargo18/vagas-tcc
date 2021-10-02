package com.example.apicandidato.candidato.model;

import com.example.security.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoEntrada {
    @Email
    private String email;
    @Email
    private String repetirEmail;
    @NotEmpty
    private String senha;
    @NotEmpty
    private String repetirSenha;
    @NotEmpty
    private String nome;

    private List<RoleEntity> roles;
    private Boolean primeiroAcesso;
    private String situacao;
}
