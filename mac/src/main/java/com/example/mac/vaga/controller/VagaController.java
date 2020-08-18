package com.example.mac.vaga.controller;

import com.example.mac.exception.MyException;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.vaga.model.VagaEntrada;
import com.example.mac.vaga.model.VagaSaida;
import com.example.mac.vaga.service.VagaService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/criarVaga")
    public HttpStatus criar(@RequestBody VagaEntrada entrada) throws MyException{
        vagaService.criar(entrada);

        return HttpStatus.OK;
    }

    @RequestMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable Long id){
        VagaSaida vagaSaida = vagaService.buscarVaga(id);
        ModelAndView mv = new ModelAndView("/admin/vaga/atualizar");
        mv.addObject("vaga",vagaSaida);
        return mv;
    }

    @PostMapping("/atualizar")
    public HttpStatus atualizar(@RequestBody VagaEntrada vagaEntrada){
        vagaService.atualizar(vagaEntrada);

        return HttpStatus.OK;
    }

    @GetMapping("/listar")
    public ModelAndView listar(){
        List<VagaSaida> saida = vagaService.listar();
        ModelAndView mv = new ModelAndView("/admin/vaga/listar");
        mv.addObject("vaga",saida);
        return mv;
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws MyException {
        VagaSaida vagaSaida = vagaService.buscarVaga(id);
        ModelAndView mv = new ModelAndView("/admin/vaga/buscar");
        List<String> listaHabilidades = vagaSaida.getDescricaoHabilidades();
        List<String> beneficios = vagaSaida.getBeneficios();
        mv.addObject("vaga",vagaSaida);
        mv.addObject("listaHabilidades",listaHabilidades);
        mv.addObject("beneficios",beneficios);
        return mv;
    }

    @GetMapping("/inscritos/{id}")
    public ModelAndView inscritos(@PathVariable Long id){
        List<DadosPessoaisSaida> dadosPessoaisSaida = vagaService.buscarInscritosPorVaga(id);
        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setId(id);

        ModelAndView mv = new ModelAndView("/admin/vaga/filtroInscritos");
        mv.addObject("cliente",dadosPessoaisSaida);
        mv.addObject("vaga",vagaSaida);
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
