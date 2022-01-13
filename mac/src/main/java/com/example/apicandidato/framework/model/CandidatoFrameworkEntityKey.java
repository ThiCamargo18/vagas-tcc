package com.example.apicandidato.framework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoFrameworkEntityKey implements Serializable {
    @Column(name = "idCandidato")
    private Long idCandidato;

    @Column(name = "idFramework")
    private Long idFramework;
}
