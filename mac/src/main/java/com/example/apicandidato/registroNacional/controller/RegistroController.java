package com.example.apicandidato.registroNacional.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.clienteCadastro.controller.ClienteCadastroController;
import com.example.apicandidato.registroNacional.model.RegistroEntrada;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import com.example.apicandidato.registroNacional.service.RegistroNacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "registro", produces = "application/json")
@Configuration
@CrossOrigin
public class RegistroController {
    @Autowired
    RegistroNacionalService registroNacionalService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid RegistroEntrada registroEntrada, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }
        RegistroSaida saida = registroNacionalService.atualizar(candidatoSessao.getId(),registroEntrada);

        return clienteCadastroController.procurar(request);
    }
}
