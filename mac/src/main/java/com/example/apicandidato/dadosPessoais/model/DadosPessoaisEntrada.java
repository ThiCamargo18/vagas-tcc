package com.example.apicandidato.dadosPessoais.model;

import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisEntrada {
    private String nomeCompleto;
    private LocalDate dataNascimento;
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
    private Long idCandidato;
}
