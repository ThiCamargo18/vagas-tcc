package com.example.mac.candidato.model;

import com.example.mac.constraint.FieldMatch;
import com.example.mac.security.model.RoleEntity;
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
@FieldMatch.List({
        @FieldMatch(first = "senha", second = "confirmacaoSenha", message = "As senhas devem ser iguais"),
        @FieldMatch(first = "email", second = "confirmacaoEmail", message = "Os e-mails devem ser iguais")
})
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
