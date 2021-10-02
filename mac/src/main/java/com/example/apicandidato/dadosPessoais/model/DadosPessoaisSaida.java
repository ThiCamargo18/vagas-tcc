package com.example.apicandidato.dadosPessoais.model;

import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String curso;
    private CategoriaEnum categoria;
    private String pretensaoSalarial;
    private String tipoConducao;
    private String cpf;
    private Long idUsuario;
    private String cidade;
}