package com.example.mac.experiencia.model;

import com.example.mac.enums.CategoriaEnum;
import com.example.mac.enums.Experiencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "experiencia")
public class ExperienciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "possui")
    @Enumerated(EnumType.STRING)
    private Experiencia possui;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "dataInicio")
    private String dataInicio;

    @Column(name = "dataFim")
    private String dataFim;

    @Column(name = "salario")
    private String salario;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;

    @Column(name = "idUsuario")
    private Long idUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Experiencia getPossui() {
        return possui;
    }

    public void setPossui(Experiencia possui) {
        this.possui = possui;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }
}
