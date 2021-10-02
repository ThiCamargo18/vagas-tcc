package com.example.apicandidato.dadosAdicionais.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.clienteCadastro.controller.ClienteCadastroController;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosAdicionais.service.DadosAdicionaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "dadosAdicionais", produces = "application/json")
@Configuration
@CrossOrigin
public class DadosAdicionaisController {
    @Autowired
    DadosAdicionaisService dadosAdicionaisService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid DadosAdicionaisEntrada dadosAdicionaisEntrada,
                                  HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }

        DadosAdicionaisSaida saida = dadosAdicionaisService.atualizar(dadosAdicionaisEntrada, candidatoSessao.getId());

        return clienteCadastroController.procurar(request);
    }
}
