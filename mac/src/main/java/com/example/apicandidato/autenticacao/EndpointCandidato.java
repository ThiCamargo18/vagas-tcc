package com.example.apicandidato.autenticacao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EndpointCandidato {
    @RequestMapping("/")
    public String paginaInicial(HttpServletRequest request){
        return "/candidato/index";
    }

    @RequestMapping("/config")
    public String configuracoes(){
        return "Config";
    }

    @RequestMapping("/multiple")
    public String multiple(){
        return "teste";
    }
}
