package com.example.apicandidato.framework.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "frameworks")
    private List<CandidatoEntity> candidato;

    @JsonIgnore
    @ManyToMany(mappedBy = "frameworks")
    private List<VagaEntity> vagas;
}
