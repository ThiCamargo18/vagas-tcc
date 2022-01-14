package com.example.apicandidato.ferramenta.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.cargo.model.CargoEntity;
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
@Entity(name = "ferramenta")
public class FerramentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idCandidato")
    private Long idCandidato;

    @JsonIgnore
    @ManyToMany(mappedBy = "ferramentas")
    private List<CargoEntity> cargos;

    @JsonIgnore
    @ManyToMany(mappedBy = "ferramentas")
    private List<CandidatoEntity> candidato;
}
