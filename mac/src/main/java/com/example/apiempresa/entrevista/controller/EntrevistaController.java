package com.example.apiempresa.entrevista.controller;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.mail.Mensagem;
import com.example.apiempresa.entrevista.model.EntrevistaEntrada;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import com.example.apiempresa.entrevista.service.EntrevistaService;
import com.example.apiempresa.model.EmpresaSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class EntrevistaController {
    @Autowired
    private EntrevistaService entrevistaService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private DadosPessoaisService dadosPessoaisService;

    @RequestMapping("/criar/{id}") //criar
    public ModelAndView criar(@PathVariable Long id) throws Exception {
        EntrevistaEntrada entrevistaEntrada = new EntrevistaEntrada();

        ModelAndView mv = new ModelAndView("/admin/entrevista/criar");

        CandidatoEntity clienteSaida = candidatoService.buscarPorId(id);
        String emailUsuario = candidatoService.buscarEmailUsuarioPorId(clienteSaida.getId());

        Mensagem dadosEmail = new Mensagem(null, Collections.singletonList(emailUsuario), null, null);

        mv.addObject("idCandidato", clienteSaida.getId());
        mv.addObject("nomeCandidato", clienteSaida.getNome());
        mv.addObject("entrevista", entrevistaEntrada);
        mv.addObject("email", dadosEmail);

        return mv;
    }

    @PostMapping("/criar")
    public String criar(@ModelAttribute EntrevistaEntrada entrevistaEntrada, HttpServletRequest request) throws Exception {
        EntrevistaSaida entrevistaSaida = entrevistaService.criar(entrevistaEntrada, EmpresaSessao.getId(request));

        return "redirect:/entrevista/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listar(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/admin/entrevista/listar");

        List<EntrevistaSaida> entrevistaSaidas = entrevistaService.listar(EmpresaSessao.getId(request));

        for (EntrevistaSaida entrevista: entrevistaSaidas) {
            CandidatoEntity candidatoEntity = candidatoService.buscarPorId(entrevista.getIdCandidato());

            entrevista.setNomeCandidato(candidatoEntity.getNome());
            entrevista.setEmailCandidato(candidatoEntity.getEmail());
        }

        mv.addObject("entrevista", entrevistaSaidas);

        return mv;
    }
}
