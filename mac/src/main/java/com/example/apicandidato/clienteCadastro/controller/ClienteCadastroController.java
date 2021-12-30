package com.example.apicandidato.clienteCadastro.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.clienteCadastro.service.ClienteCadastroService;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.projetos.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "cadastro", produces = "application/json")
public class ClienteCadastroController {
    @Autowired
    private ClienteCadastroService clienteCadastroService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private HabilidadesService habilidadesService;
    @Autowired
    private ProjetoService projetoService;

    @RequestMapping(value = "/criar")
    public ModelAndView criar() {
        return new ModelAndView("/candidato/cadastro/cadastroCompleto");
    }

    @PostMapping("/criar")
    public String criar(@Valid ClienteCadastroEntrada entrada, HttpServletRequest request) throws Exception {
        clienteCadastroService.criar(entrada, CandidatoSessao.getId(request));

        return "redirect:gerenciar";
    }

    @RequestMapping("/criar/cadastroAdicional")
    public ModelAndView criarCadastroAdicional(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/candidato/cadastro/cadastroAdicional");

        modelAndView.addObject("experiencia", experienciaService.buscarPorIdCliente(CandidatoSessao.getId(request)));
        modelAndView.addObject("habilidade", habilidadesService.buscarPorIdCLiente(CandidatoSessao.getId(request)));
        modelAndView.addObject("projeto", projetoService.buscarPorIdCLiente(CandidatoSessao.getId(request)));

        return modelAndView;
    }

    @PostMapping("/criar/cadastroAdicional")
    public String criarCadastroAdicional(@Valid ClienteCadastroEntrada entrada, HttpServletRequest request) throws Exception {
        clienteCadastroService.criar(entrada, CandidatoSessao.getId(request));

        return "redirect:gerenciar";
    }

    @RequestMapping("/gerenciar")
    public String gerenciarCadastro(HttpServletRequest request) throws Exception {
        Long idCandidato = CandidatoSessao.getId(request);

        boolean cadastroBasicoRealizado = candidatoService.cadastroBasicoRealizado(idCandidato);

        if (!cadastroBasicoRealizado) return "redirect:criar";

        boolean cadastroAdicionalRealizado = candidatoService.cadastroAdicionalRealizado(idCandidato);

        if (!cadastroAdicionalRealizado) return "redirect:criar/cadastroAdicional";

        return "redirect:procurar";
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
        mv.addObject("experiencia", saida.getExperiencia());

        return mv;
    }
}
