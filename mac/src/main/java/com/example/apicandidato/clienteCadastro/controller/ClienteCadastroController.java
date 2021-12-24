package com.example.apicandidato.clienteCadastro.controller;

import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.clienteCadastro.service.ClienteCadastroService;
import com.example.apicandidato.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.experiencia.model.ExperienciaSaida;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.registroNacional.model.RegistroSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "cadastro", produces = "application/json")
public class ClienteCadastroController {
    @Autowired
    private ClienteCadastroService clienteCadastroService;
    @Autowired
    private CandidatoService candidatoService;

    @RequestMapping(value = "/criar")
    public ModelAndView criar() {
        return new ModelAndView("/candidato/cadastro/cadastroCompleto");
    }

    @PostMapping("/criar")
    public String criar(@Valid ClienteCadastroEntrada entrada, HttpServletRequest request) throws Exception {
        clienteCadastroService.criar(entrada, CandidatoSessao.getId(request));

        return "redirect:gerenciar";
    }

    @RequestMapping("/gerenciar")
    public String gerenciarCadastro(HttpServletRequest request) throws Exception {
        switch (candidatoService.isPrimeiroAcesso(CandidatoSessao.getId(request))) {
            case "true":
                return "redirect:criar";
            case "false":
                return "redirect:procurar";
        }

        return "redirect:/";
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/candidato/cadastroCompleto");

        ClienteCadastroSaida saida = clienteCadastroService.buscar(id);

        mv.addObject("cliente", saida.getCliente());
        mv.addObject("dadosPessoais", saida.getDadosPessoais());
        mv.addObject("dadosAdicionais", saida.getDadosAdicionais());
        mv.addObject("registro", saida.getRegistro());
        mv.addObject("habilidades", saida.getHabilidades());
        mv.addObject("listaHabilidades", saida.getHabilidades().getDescricao());
        mv.addObject("experiencia", saida.getExperiencia());

        return mv;
    }

    @GetMapping("/procurar")
    public ModelAndView procurar(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/candidato/cadastro/buscar");

        ClienteCadastroSaida saida = clienteCadastroService.buscar(CandidatoSessao.getId(request));

        mv.addObject("cliente", saida.getCliente());
        mv.addObject("dadosPessoais", saida.getDadosPessoais());
        mv.addObject("dadosAdicionais", saida.getDadosAdicionais());
        mv.addObject("registro", saida.getRegistro());
        mv.addObject("habilidades", saida.getHabilidades());
        mv.addObject("listaHabilidades", saida.getHabilidades().getDescricao());
        mv.addObject("experiencia", saida.getExperiencia());

        return mv;
    }
}
