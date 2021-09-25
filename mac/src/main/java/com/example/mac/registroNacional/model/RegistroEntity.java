package com.example.mac.registroNacional.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="registroNacional")
public class RegistroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orgaoEmissor")
    private String orgaoEmissor;

    @Column(name = "estado")
    private String estado;

    @Column(name = "dataExpedicao")
    private String dataExpedicao;

    @Column(name = "numero",unique = true)
    private String numero;

    @Column(name = "idUsuario")
    private Long idUsuario;
}
