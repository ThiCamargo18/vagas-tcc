package com.example.mac.candidato.model;

import com.example.mac.security.model.RoleEntity;
import com.example.mac.vaga.model.VagaEntity;
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
@Table(name = "cliente")
public class CandidatoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Transient
    @Column(name = "senha")
    private String senha;

    @Column(name = "login")
    private String login;

    @Column(name = "nome")
    private String nome;

    @Column(name = "primeiroAcesso")
    private Boolean primeiroAcesso;

    @Column(name = "situacao")
    private String situacao;

    @ManyToMany(mappedBy = "clientes")
    private List<VagaEntity> vagas;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<RoleEntity> roles;
}
