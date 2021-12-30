package com.example.apicandidato.dadosPessoais.model;

import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisSaida {
    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private Integer idade;
    private String sexo;
    private String nomeSocial;
    private String naturalidade;
    private String celular;
    private String formacao;
    private String curso;
    private CategoriaEnum categoria;
    private String pretensaoSalarial;
    private String cpf;
    private String cidade;
    private Long idUsuario;
}
