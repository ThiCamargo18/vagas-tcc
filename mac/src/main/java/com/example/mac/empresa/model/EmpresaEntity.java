package com.example.mac.empresa.model;

import com.example.mac.recursosHumanos.model.RecursosHumanosEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "empresa")
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razaoSocial")
    private String razaoSocial;

    @Column(name = "representante")
    private String representante;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @OneToMany(cascade = CascadeType.ALL)
    List<RecursosHumanosEntity> recursosHumanos;
}
