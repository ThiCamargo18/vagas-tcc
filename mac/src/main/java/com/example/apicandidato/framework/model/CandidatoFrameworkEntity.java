package com.example.apicandidato.framework.model;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoFrameworkEntity {
    @EmbeddedId
    private CandidatoFrameworkEntityKey candidatoFrameworkEntityKey;

    @ManyToOne
    @JsonIgnore
    @MapsId("idCandidato")
    @JoinColumn(name = "idCandidato")
    private CandidatoEntity candidatoEntity;

    @ManyToOne
    @JsonIgnore
    @MapsId("idFramework")
    @JoinColumn(name = "idFramework")
    private FrameworkEntity frameworkEntity;

    public CandidatoFrameworkEntity(CandidatoFrameworkEntityKey key) {
        this.candidatoFrameworkEntityKey = key;
    }
}
