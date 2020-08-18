package com.example.mac.habilidades.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "habilidades")
public class HabilidadesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resumoProfissional")
    private String resumoProfissional;

    @Column(name = "objetivos")
    private String objetivos;

    @Column(name = "descricao")
    @ElementCollection(targetClass=String.class)
    private List<String> descricao;

    @Column(name = "nomeUsuario")
    private String nomeUsuario;

    @Column(name = "idUsuario")
    private Long idUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public List<String> getDescricao() {
        return descricao;
    }

    public void setDescricao(List<String> descricao) {
        this.descricao = descricao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
