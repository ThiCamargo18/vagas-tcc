package com.example.mac.habilidades.controller;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.clienteCadastro.controller.ClienteCadastroController;
import com.example.mac.habilidades.model.HabilidadesEntrada;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.habilidades.service.HabilidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "habilidades", produces = "application/json")
@Configuration
@CrossOrigin
public class HabilidadesController {
    @Autowired
    HabilidadesService habilidadesService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid HabilidadesEntrada habilidadesEntrada, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }
        HabilidadesSaida saida = habilidadesService.atualizar(clienteSessao.getId(),habilidadesEntrada);

        return clienteCadastroController.procurar(request);
    }
}
