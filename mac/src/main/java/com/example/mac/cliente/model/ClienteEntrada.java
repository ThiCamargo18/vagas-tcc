package com.example.mac.cliente.model;

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
public class ClienteEntrada {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(Boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
