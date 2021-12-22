package com.example.apicandidato.autenticacao;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.security.CandidatoAutenticacaoService;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.security.CandidatoValidacao;
import com.example.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EndpointCandidato {
    @RequestMapping("/")
    public ModelAndView paginaInicial(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/inicio")
    public ModelAndView inicio(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");
        ModelAndView mv = new ModelAndView("/candidato/index");
        mv.addObject("candidato", candidatoSessao);

        return mv;
    }

    @RequestMapping("/config")
    public String configuracoes(){
        return "Config";
    }

    @RequestMapping("/logout")
    public String sair(){
        return "Sair";
    }
}
