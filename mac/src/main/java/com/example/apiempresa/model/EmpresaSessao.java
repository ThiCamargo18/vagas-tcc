package com.example.apiempresa.model;

import com.example.apicandidato.candidato.model.CandidatoSessao;
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
public class EmpresaSessao {
    private Long id;
    private String nome;

    public static Long getId(HttpServletRequest request){
        HttpSession session = request.getSession();

        EmpresaSessao empresaSessao = (EmpresaSessao) session.getAttribute("empresaLogada");

        return empresaSessao.getId();
    }
}
