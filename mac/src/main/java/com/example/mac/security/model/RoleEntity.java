package com.example.mac.security.model;

import com.example.mac.candidato.model.CandidatoEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<CandidatoEntity> users;

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CandidatoEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<CandidatoEntity> users) {
        this.users = users;
    }
}
