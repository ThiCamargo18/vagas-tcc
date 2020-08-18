package com.example.mac.dadosPessoais.model;

import com.example.mac.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisSaida {
    private Long id;
    private String nomeCompleto;
    private Integer idade;
    private String dataNascimento;
    private String sexo;
    private String nomeSocial;
    private String estadoCivil;
    private String nomeMae;
    private String naturalidade;
    private String telefone;
    private String celular;
    private String formacao;
    private String experiencia;
    private String curso;
    private CategoriaEnum categoria;
    private Double pretensaoSalarial;
    private String tipoConducao;
    private String email;
    private String cidade;
    private Long idUsuario;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
