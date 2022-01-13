package com.example.apicandidato.candidato.model;

import com.example.apicandidato.framework.model.CandidatoFrameworkEntity;
import com.example.security.model.RoleEntity;
import com.example.apiempresa.vaga.model.VagaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cliente")
public class CandidatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nivelCadastroRealizado")
    private Integer nivelCadastroRealizado;

    @Column(name = "situacao")
    private String situacao;

    @ManyToMany(mappedBy = "clientes")
    private List<VagaEntity> vagas;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "candidatoEntity")
    private List<CandidatoFrameworkEntity> frameworks;
}
