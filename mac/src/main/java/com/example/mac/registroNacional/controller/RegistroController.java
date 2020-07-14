package com.example.mac.registroNacional.controller;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.registroNacional.model.RegistroEntrada;
import com.example.mac.registroNacional.model.RegistroSaida;
import com.example.mac.registroNacional.service.RegistroNacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/atualizar")
    public RegistroSaida atualizar(@Valid RegistroEntrada registroEntrada, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }
        RegistroSaida saida = registroNacionalService.atualizar(clienteSessao.getId(),registroEntrada);

        return saida;
    }
}
