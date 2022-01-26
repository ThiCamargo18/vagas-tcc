package com.example.apicandidato.autenticacao;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.clienteCadastro.service.ClienteCadastroService;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import com.example.apiempresa.entrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EndpointCandidato {
    @Autowired
    private EntrevistaService entrevistaService;
    @Autowired
    private CandidatoService candidatoService;

    @RequestMapping("/")
    public String paginaInicial(HttpServletRequest request, Model model) {
        int nivelCadastro = candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request));
        boolean entevistaVizualizada = entrevistaService.validarSeCandidatoVizualizouTodosAgendamentos(CandidatoSessao.getId(request));

        if (nivelCadastro < 3)
            model.addAttribute("alertaCadastro", "Você precisa completar seu cadastro!");

        if (!entevistaVizualizada)
            model.addAttribute("alertaEntrevista", "Você foi selecionado para uma entrevista");

        return "/candidato/index";
    }

    @RequestMapping("/config")
    public String configuracoes(){
        return "Config";
    }
}
