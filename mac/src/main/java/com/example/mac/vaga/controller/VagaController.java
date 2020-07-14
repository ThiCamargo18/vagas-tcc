package com.example.mac.vaga.controller;

import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.vaga.model.VagaEntrada;
import com.example.mac.vaga.model.VagaSaida;
import com.example.mac.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "vaga", produces = "application/json")
@Configuration
@CrossOrigin
public class VagaController {
    @Autowired
    VagaService vagaService;

    @RequestMapping("/criar")
    public ModelAndView criar(){
        return new ModelAndView("/admin/vaga/criar");
    }

    @PostMapping("/criar")
    public ModelAndView criar(@Valid VagaEntrada entrada) throws Exception {
        VagaSaida saida = vagaService.criar(entrada);

        return new ModelAndView("/admin/index");
    }

    @GetMapping("/listar")
    public ModelAndView listar(){
        List<VagaSaida> saida = vagaService.listar();
        ModelAndView mv = new ModelAndView("/admin/vaga/listar");
        mv.addObject("vaga",saida);
        return mv;
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        VagaSaida vagaSaida = vagaService.buscarVaga(id);
        ModelAndView mv = new ModelAndView("/admin/vaga/buscar");
        List<String> listaHabilidades = vagaSaida.getDescricaoHabilidades();
        mv.addObject("vaga",vagaSaida);
        mv.addObject("listaHabilidades",listaHabilidades);
        return mv;
    }

    @GetMapping("/inscritos/{id}")
    public ModelAndView inscritos(@PathVariable Long id){
        List<DadosPessoaisSaida> dadosPessoaisSaida = vagaService.buscarInscritosPorVaga(id);

        ModelAndView mv = new ModelAndView("/admin/vaga/filtroInscritos");
        mv.addObject("cliente",dadosPessoaisSaida);
        return mv;
    }

    @GetMapping("/deletar")
    public void deletar(@RequestParam Long vaga, HttpServletResponse response) throws Exception {
        String resultado = vagaService.deletar(vaga); /// usar response

        if(resultado.equals("concluido")){
            response.sendRedirect("http://localhost:8088/vaga/listar");
        }else{
            throw new Exception("Não foi possivel excluir a vaga, tente novamente");
        }
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome (@RequestParam String nome) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/vaga/buscarPorNome");
        List<VagaSaida> vagaSaidas = vagaService.buscarPorNome(nome);
        int numeroVagasEncontradas = vagaSaidas.size();
        vagaSaidas.get(0).setNumeroVagasEncontradas(numeroVagasEncontradas);
        mv.addObject("vaga",vagaSaidas);
        return mv;
    }
}
