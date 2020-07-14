package com.example.mac.clienteCadastro.model;

import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.mac.experiencia.model.ExperienciaEntrada;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.registroNacional.model.RegistroEntrada;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
public class ClienteCadastroEntrada {
    ClienteEntrada cliente;
    DadosAdicionaisEntrada dadosAdicionais;
    DadosPessoaisEntrada dadosPessoais;
    ExperienciaEntrada experiencia;
    HabilidadesEntrada habilidades;
    RegistroEntrada registro;

    public ClienteEntrada getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntrada cliente) {
        this.cliente = cliente;
    }

    public DadosAdicionaisEntrada getDadosAdicionais() {
        return dadosAdicionais;
    }

    public void setDadosAdicionais(DadosAdicionaisEntrada dadosAdicionais) {
        this.dadosAdicionais = dadosAdicionais;
    }

    public DadosPessoaisEntrada getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoaisEntrada dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public ExperienciaEntrada getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaEntrada experiencia) {
        this.experiencia = experiencia;
    }

    public HabilidadesEntrada getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(HabilidadesEntrada habilidades) {
        this.habilidades = habilidades;
    }

    public RegistroEntrada getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroEntrada registro) {
        this.registro = registro;
    }
}
