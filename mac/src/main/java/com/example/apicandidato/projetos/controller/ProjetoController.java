package com.example.apicandidato.projetos.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.projetos.model.ProjetoEntrada;
import com.example.apicandidato.projetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "projeto", produces = "application/json")
public class ProjetoController {
    @Autowired
    ProjetoService projetoService;

    @PostMapping("/atualizar")
    public String atualizar(@Valid ProjetoEntrada projetoEntrada, HttpServletRequest request) throws Exception {
        projetoService.atualizar(projetoEntrada, CandidatoSessao.getId(request));

        return "redirect:/cadastro/gerenciar";
    }
}
