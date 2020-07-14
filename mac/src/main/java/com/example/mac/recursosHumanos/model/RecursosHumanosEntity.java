package com.example.mac.recursosHumanos.model;

import com.example.mac.empresa.model.EmpresaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformers;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "recursosHumanos")
public class RecursosHumanosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private Long login;

    @Column(name = "senha")
    private Long senha;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    EmpresaEntity idEmpresa;
}
