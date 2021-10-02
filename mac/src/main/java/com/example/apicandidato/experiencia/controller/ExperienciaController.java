package com.example.apicandidato.experiencia.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.clienteCadastro.controller.ClienteCadastroController;
import com.example.apicandidato.experiencia.model.ExperienciaEntrada;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "experiencia", produces = "application/json")
@Configuration
@CrossOrigin
public class ExperienciaController {
    @Autowired
    ExperienciaService experienciaService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid ExperienciaEntrada experienciaEntrada, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }
        ExperienciaSaida saida = experienciaService.atualizar(candidatoSessao.getId(),experienciaEntrada);

        return clienteCadastroController.procurar(request);
    }
}
