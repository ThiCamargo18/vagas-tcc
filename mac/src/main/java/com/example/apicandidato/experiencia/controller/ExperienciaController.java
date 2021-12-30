package com.example.apicandidato.experiencia.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "experiencia", produces = "application/json")
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;

    @PostMapping("/atualizar")
    public String atualizar(@Valid ExperienciaEntrada experienciaEntrada, HttpServletRequest request) throws Exception {
        experienciaService.atualizar(experienciaEntrada, CandidatoSessao.getId(request));

        return "redirect:/cadastro/gerenciar";
    }
}
