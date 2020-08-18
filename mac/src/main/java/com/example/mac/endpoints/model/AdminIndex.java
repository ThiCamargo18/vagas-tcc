package com.example.mac.endpoints.model;

public class AdminIndex {
    Long numeroClientes;
    Long numeroEntrevistas;
    Long numeroVagas;

    public Long getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(Long numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public Long getNumeroEntrevistas() {
        return numeroEntrevistas;
    }

    public void setNumeroEntrevistas(Long numeroEntrevistas) {
        this.numeroEntrevistas = numeroEntrevistas;
    }

    public Long getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(Long numeroVagas) {
        this.numeroVagas = numeroVagas;
    }
}
