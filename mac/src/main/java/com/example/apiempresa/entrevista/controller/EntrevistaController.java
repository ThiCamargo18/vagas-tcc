package com.example.apiempresa.entrevista.controller;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apiempresa.entrevista.model.EntrevistaEntrada;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import com.example.apiempresa.entrevista.service.EntrevistaService;
import com.example.apicandidato.mail.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class EntrevistaController {
    @Autowired
    EntrevistaService entrevistaService;
    @Autowired
    CandidatoService candidatoService;
    @Autowired
    DadosPessoaisService dadosPessoaisService;

    @RequestMapping("/selecionar")
    public ModelAndView selecionar(){
        return new ModelAndView("/admin/entrevista/selecionarNova");
    }

    @RequestMapping("/nova")
    public ModelAndView novaEntrevista(@RequestParam String cpf) throws Exception {
        CandidatoEntity cliente = dadosPessoaisService.buscarEVerificarExistenciaClientePorCpf(cpf);

        if(cliente==null){
            throw new Exception("Candidato não encontrado");
        }

        String emailUsuario = dadosPessoaisService.buscarEmailUsuarioPorIdNovaVaga(cliente.getId());
        Mensagem dadosEmail = new Mensagem(null, Arrays.asList(emailUsuario),null,null);

        ModelAndView mv = new ModelAndView("/admin/entrevista/nova"); //Tem 2 métodos iguais, mas esse é para navbar
        mv.addObject("cliente",cliente);
        mv.addObject("email",dadosEmail);
        return mv;
    }

    @PostMapping("/novaVaga")
    public HttpStatus nova(@RequestBody EntrevistaEntrada entrevistaEntrada,HttpServletResponse response) throws Exception {
        entrevistaService.novaEntrevistaPelaNavbar(entrevistaEntrada);

        return HttpStatus.OK;
    }

    @RequestMapping("/criar/{id}") //criar
    public ModelAndView criar(@PathVariable Long id) throws Exception {
        EntrevistaEntrada entrevistaEntrada = new EntrevistaEntrada();
        ModelAndView mv = new ModelAndView("/admin/entrevista/criar");
        CandidatoEntity clienteSaida = candidatoService.buscarEVerificarExistenciaClientePorIdVaga(id);
        String emailUsuario = dadosPessoaisService.buscarEmailUsuarioPorId(clienteSaida.getId());
        Mensagem dadosEmail = new Mensagem(null, Arrays.asList(emailUsuario),null,null);
        mv.addObject("cliente",clienteSaida);
        mv.addObject("entrevista",entrevistaEntrada);
        mv.addObject("email",dadosEmail);
        return mv;
    }

    @PostMapping("/criar")
    public HttpStatus criar(@RequestBody EntrevistaEntrada entrevistaEntrada, HttpServletResponse response) throws Exception {
        EntrevistaSaida entrevistaSaida = entrevistaService.criar(entrevistaEntrada);

        return HttpStatus.OK;
    }

    @GetMapping("/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/admin/entrevista/listar");
        List<EntrevistaSaida> entrevistaSaidas = entrevistaService.listar();
        mv.addObject("entrevista",entrevistaSaidas);
        return mv;
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        String resultado = entrevistaService.deletar(id);

        return resultado;
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable long id) throws Exception {
        EntrevistaSaida entrevistaSaida = entrevistaService.buscar(id);
        ModelAndView modelAndView = new ModelAndView("/admin/entrevista/atualizar");
        modelAndView.addObject("entrevista",entrevistaSaida);
        return modelAndView;
    }

    @PostMapping("/atualizar/{id}")
    public ModelAndView atualizar(@PathVariable long id,HttpServletResponse response,@Valid EntrevistaEntrada entrevistaEntrada) throws Exception {
        entrevistaService.atualizar(id,entrevistaEntrada);

        return listar();
    }
}
