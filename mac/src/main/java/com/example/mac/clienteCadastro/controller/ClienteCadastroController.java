package com.example.mac.clienteCadastro.controller;

import com.example.mac.candidato.model.CandidatoSaida;
import com.example.mac.candidato.model.CandidatoSessao;
import com.example.mac.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.mac.clienteCadastro.model.ClienteCadastroSaida;
import com.example.mac.clienteCadastro.service.ClienteCadastroService;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.experiencia.model.ExperienciaSaida;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.registroNacional.model.RegistroSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "cadastro", produces = "application/json")
public class ClienteCadastroController {
    @Autowired
    ClienteCadastroService clienteCadastroService;

    @RequestMapping(value = "/criar")
    public ModelAndView criar(){
        return new ModelAndView("/candidato/cadastro/cadastroCompleto");
    }

    @PostMapping("/criar")
    public void criar(@Valid ClienteCadastroEntrada entrada, HttpServletRequest request,HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        ClienteCadastroSaida saida = clienteCadastroService.criar(entrada, candidatoSessao.getId());

        candidatoSessao.setPrimeiroAcesso(saida.getCliente().getPrimeiroAcesso());

        session.setAttribute("usuarioLogado", candidatoSessao);

        response.sendRedirect("http://localhost:8088/inicio");
    }

    @RequestMapping("/gerenciar")
    public void gerenciarCadastro(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao == null){
            throw new Exception("Você ainda não possui uma conta! Vá até o menu e cadastre uma.");
        }

        switch (candidatoSessao.getPrimeiroAcesso().toString()){
            case "true" : response.sendRedirect("http://localhost:8088/cadastro/criar");
                break;
            case "false" : response.sendRedirect("http://localhost:8088/cadastro/procurar");
                break;
        }
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/candidato/cadastroCompleto");
        ClienteCadastroSaida saida = clienteCadastroService.buscar(id);
        CandidatoSaida cliente = saida.getCliente();
        DadosPessoaisSaida dadosPessoais = saida.getDadosPessoais();
        DadosAdicionaisSaida dadosAdicionais = saida.getDadosAdicionais();
        RegistroSaida registro = saida.getRegistro();
        HabilidadesSaida habilidades = saida.getHabilidades();
        ExperienciaSaida experiencia = saida.getExperiencia();
        List<String> listaHabilidades = habilidades.getDescricao();

        mv.addObject("cliente",cliente);
        mv.addObject("dadosPessoais",dadosPessoais);
        mv.addObject("dadosAdicionais",dadosAdicionais);
        mv.addObject("registro",registro);
        mv.addObject("habilidades",habilidades);
        mv.addObject("listaHabilidades",listaHabilidades);
        mv.addObject("experiencia",experiencia);

        return mv;
    }

    @GetMapping("/procurar")
    public ModelAndView procurar(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        ModelAndView mv = new ModelAndView("/candidato/cadastro/buscar");
        ClienteCadastroSaida saida = clienteCadastroService.buscar(candidatoSessao.getId());
        CandidatoSaida cliente = saida.getCliente();
        DadosPessoaisSaida dadosPessoais = saida.getDadosPessoais();
        DadosAdicionaisSaida dadosAdicionais = saida.getDadosAdicionais();
        RegistroSaida registro = saida.getRegistro();
        HabilidadesSaida habilidades = saida.getHabilidades();
        ExperienciaSaida experiencia = saida.getExperiencia();
        List<String> listaHabilidades = habilidades.getDescricao();

        mv.addObject("cliente",cliente);
        mv.addObject("dadosPessoais",dadosPessoais);
        mv.addObject("dadosAdicionais",dadosAdicionais);
        mv.addObject("registro",registro);
        mv.addObject("habilidades",habilidades);
        mv.addObject("listaHabilidades",listaHabilidades);
        mv.addObject("experiencia",experiencia);

        return mv;
    }
}
