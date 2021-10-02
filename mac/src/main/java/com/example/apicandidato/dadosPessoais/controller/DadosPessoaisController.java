package com.example.apicandidato.dadosPessoais.controller;


import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.clienteCadastro.controller.ClienteCadastroController;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "dadosPessoais", produces = "application/json")
@Configuration
@CrossOrigin
public class DadosPessoaisController {
    @Autowired
    DadosPessoaisService dadosPessoaisService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid DadosPessoaisEntrada dadosPessoaisEntrada,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça por gentileza!");
        }

        DadosPessoaisSaida saida = dadosPessoaisService.atualizar(dadosPessoaisEntrada, candidatoSessao.getId());

        return clienteCadastroController.procurar(request);
    }

}
