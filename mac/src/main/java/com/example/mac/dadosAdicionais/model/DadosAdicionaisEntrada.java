package com.example.mac.dadosAdicionais.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAdicionaisEntrada {
    private long id;
    private String cep; //
    private String estado;//
    private String cidade;//
    private String bairro;//
    private String endereco;//
    private Integer numero;//
    private String referencia;//
    private Long idUsuario;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
