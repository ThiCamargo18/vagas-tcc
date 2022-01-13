package com.example.apicandidato.framework.model;

import com.example.apicandidato.cargo.model.CargoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "framework")
public class FrameworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "frameworks")
    private List<CargoEntity> cargos;

    @OneToMany(mappedBy = "frameworkEntity")
    Set<CandidatoFrameworkEntity> frameworks;
}
