package com.example.apicandidato.candidato.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoSessao {
    private Long id;
    private String nome;

    public static Long getId(HttpServletRequest request){
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        return candidatoSessao.getId();
    }
}
