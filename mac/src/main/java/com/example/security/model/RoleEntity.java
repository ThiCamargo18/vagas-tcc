package com.example.security.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<CandidatoEntity> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<EmpresaEntity> empresas;

    public RoleEntity(String role) {
        this.name = role;
    }
}
