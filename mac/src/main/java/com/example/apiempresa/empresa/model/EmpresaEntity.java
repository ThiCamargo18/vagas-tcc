package com.example.apiempresa.empresa.model;

import com.example.security.model.RoleEntity;
import com.example.apiempresa.recursosHumanos.model.RecursosHumanosEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "empresa")
public class EmpresaEntity {
    @Id
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razaoSocial")
    private String razaoSocial;

    @Column(name = "representante")
    private String representante;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @OneToMany(cascade = CascadeType.ALL)
    List<RecursosHumanosEntity> recursosHumanos;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RoleEntity> roles;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public List<RecursosHumanosEntity> getRecursosHumanos() {
        return recursosHumanos;
    }

    public void setRecursosHumanos(List<RecursosHumanosEntity> recursosHumanos) {
        this.recursosHumanos = recursosHumanos;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
