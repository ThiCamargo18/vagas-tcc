package com.example.apicandidato.dadosPessoais.model;

import com.example.apicandidato.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "nomeSocial")
    private String nomeSocial;

    @Column(name = "naturalidade")
    private String naturalidade;

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

    @CPF(message = "Esse CPF não é válido")
    @Column(name = "cpf",unique = true)
    private String cpf;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "idCandidato")
    private Long idCandidato;

    public DadosPessoaisEntity(String formacao, String categoria, String endereco) {
        this.formacao = formacao;
        if (categoria != null)
            this.categoria = CategoriaEnum.valueOf(categoria);

        this.cidade = endereco;
    }

    public void setIdade(LocalDate dataNascimento) {
        int anoAtual = LocalDate.now().getYear();
        int anoNascimentoCandidato = dataNascimento.getYear();

        this.idade = anoAtual - anoNascimentoCandidato;
    }
}
