package com.example.apicandidato.dadosAdicionais.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dadosAdicionais")
public class DadosAdicionaisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "idCandidato")
    private Long idCandidato;
}
