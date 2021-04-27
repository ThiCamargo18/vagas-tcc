package com.example.mac.clienteCadastro.model;

import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.registroNacional.model.RegistroSaida;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClienteCadastroSaida {
    ClienteSaida cliente;
    DadosAdicionaisSaida dadosAdicionais;
    DadosPessoaisSaida dadosPessoais;
    ExperienciaSaida experiencia;
    HabilidadesSaida habilidades;
    RegistroSaida registro;


    public ClienteSaida getCliente() {
        return cliente;
    }

    public void setCliente(ClienteSaida cliente) {
        this.cliente = cliente;
    }

    public DadosAdicionaisSaida getDadosAdicionais() {
        return dadosAdicionais;
    }

    public void setDadosAdicionais(DadosAdicionaisSaida dadosAdicionais) {
        this.dadosAdicionais = dadosAdicionais;
    }

    public DadosPessoaisSaida getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoaisSaida dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public ExperienciaSaida getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaSaida experiencia) {
        this.experiencia = experiencia;
    }

    public HabilidadesSaida getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(HabilidadesSaida habilidades) {
        this.habilidades = habilidades;
    }

    public RegistroSaida getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroSaida registro) {
        this.registro = registro;
    }
}
