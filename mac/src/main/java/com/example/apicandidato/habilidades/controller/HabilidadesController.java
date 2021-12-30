package com.example.apicandidato.habilidades.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.habilidades.model.HabilidadesEntrada;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "habilidades", produces = "application/json")
public class HabilidadesController {
    @Autowired
    private HabilidadesService habilidadesService;

    @PostMapping("/atualizar")
    public String atualizar(@Valid HabilidadesEntrada habilidadesEntrada, HttpServletRequest request) throws Exception {
        habilidadesService.atualizar(habilidadesEntrada, CandidatoSessao.getId(request));

        return "redirect:/cadastro/gerenciar";
    }
}
