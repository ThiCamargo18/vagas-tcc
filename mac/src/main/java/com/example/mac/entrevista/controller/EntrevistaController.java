package com.example.mac.entrevista.controller;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.entrevista.model.EntrevistaEntrada;
import com.example.mac.entrevista.model.EntrevistaSaida;
import com.example.mac.entrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class EntrevistaController {
    @Autowired
    EntrevistaService entrevistaService;
    @Autowired
    ClienteService clienteService;

    @RequestMapping("/selecionar")
    public ModelAndView selecionar(){
        return new ModelAndView("/admin/entrevista/selecionarNova");
    }

    @RequestMapping("/nova")
    public ModelAndView novaEntrevista(@RequestParam String cpf) throws Exception {
        ClienteEntity cliente = clienteService.buscarEVerificarExistenciaClientePorCpf(cpf);

        if(cliente==null){
            throw new Exception("Candidato não encontrado");
        }
        ModelAndView mv = new ModelAndView("/admin/entrevista/nova"); //Tem 2 métodos iguais, mas esse é para navbar
        mv.addObject("cliente",cliente);
        return mv;
    }

    @RequestMapping("/criar/{id}")
    public ModelAndView criar(@PathVariable Long id) throws Exception {
        EntrevistaEntrada entrevistaEntrada = new EntrevistaEntrada();
        ModelAndView mv = new ModelAndView("/admin/entrevista/criar");
        ClienteEntity clienteSaida = clienteService.buscarEVerificarExistenciaClientePorIdVaga(id);
        mv.addObject("cliente",clienteSaida);
        mv.addObject("entrevista",entrevistaEntrada);
        return mv;
    }

    @PostMapping("/criar/{id}")
    public void criar(@Valid EntrevistaEntrada entrevistaEntrada, @PathVariable Long id,HttpServletResponse response) throws Exception {
        EntrevistaSaida entrevistaSaida = entrevistaService.criar(entrevistaEntrada,id);

        response.sendRedirect("http://localhost:8088/admin");
    }

    @PostMapping("/nova")
    public void nova(@Valid EntrevistaEntrada entrevistaEntrada,HttpServletResponse response) throws Exception {
        entrevistaService.novaEntrevistaPelaNavbar(entrevistaEntrada);

        response.sendRedirect("http://localhost:8088/admin");
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
    public void atualizar(@PathVariable long id,HttpServletResponse response,@Valid EntrevistaEntrada entrevistaEntrada) throws Exception {
        entrevistaService.atualizar(id,entrevistaEntrada);

        response.sendRedirect("http://localhost:8088/entrevista/listar");
    }
}
