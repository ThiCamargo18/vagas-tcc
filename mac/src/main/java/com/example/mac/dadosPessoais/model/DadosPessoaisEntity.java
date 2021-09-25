package com.example.mac.dadosPessoais.model;

import com.example.mac.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dadosPessoais")
public class DadosPessoaisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeCompleto")
    private String nomeCompleto;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "nomeSocial")
    private String nomeSocial;

    @Column(name = "estadoCivil")
    private String estadoCivil;

    @Column(name = "nomeMae")
    private String nomeMae;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @Column(name = "formacao")
    private String formacao;

    @Column(name = "curso")
    private String curso;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "pretensaoSalarial")
    private String pretensaoSalarial;

    @Column(name = "tipoConducao")
    private String tipoConducao;

    @CPF(message = "Esse CPF não é válido")
    @Column(name = "cpf",unique = true)
    private String cpf;

    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "cidade")
    private String cidade;
}
