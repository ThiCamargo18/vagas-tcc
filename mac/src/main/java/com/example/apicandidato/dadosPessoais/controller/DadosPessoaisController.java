package com.example.apicandidato.dadosPessoais.controller;


import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.clienteCadastro.controller.ClienteCadastroController;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "dadosPessoais", produces = "application/json")
@Configuration
@CrossOrigin
public class DadosPessoaisController {
    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @PostMapping("/atualizar")
    public String atualizar(@Valid DadosPessoaisEntrada dadosPessoaisEntrada, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        dadosPessoaisService.atualizar(dadosPessoaisEntrada, candidatoSessao.getId());

        return "redirect:/cadastro/procurar";
    }
}
