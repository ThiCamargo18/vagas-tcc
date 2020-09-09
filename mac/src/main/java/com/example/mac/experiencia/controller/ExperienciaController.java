package com.example.mac.experiencia.controller;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.clienteCadastro.controller.ClienteCadastroController;
import com.example.mac.experiencia.model.ExperienciaEntrada;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.experiencia.service.ExperienciaService;
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

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }
        ExperienciaSaida saida = experienciaService.atualizar(clienteSessao.getId(),experienciaEntrada);

        return clienteCadastroController.procurar(request);
    }
}
