package com.example.apicandidato.autenticacao;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.entrevista.model.EntrevistaSaida;
import com.example.apicandidato.entrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "candidato/entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class Entrevista {
    @Autowired
    EntrevistaService entrevistaService;

    @GetMapping("/buscar")
    public ModelAndView buscar(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");
        EntrevistaSaida entrevistaSaida = entrevistaService.buscarPorIdCandidato(candidatoSessao.getId());

        ModelAndView mv = new ModelAndView("/candidato/entrevista/buscar");

        mv.addObject("entrevista",entrevistaSaida);

        return mv;
    }
}
