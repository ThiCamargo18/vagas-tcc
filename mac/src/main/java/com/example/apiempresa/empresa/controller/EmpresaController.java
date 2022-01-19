package com.example.apiempresa.empresa.controller;

import com.example.apiempresa.empresa.model.EmpresaEntrada;
import com.example.apiempresa.empresa.model.EmpresaSaida;
import com.example.apiempresa.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "empresa", produces = "application/json")
@Configuration
@CrossOrigin
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @RequestMapping("/obterCriar")
    public ModelAndView obterCriar() {
        return new ModelAndView("/admin/empresa/criar");
    }

    @PostMapping("/nova")
    public EmpresaSaida criar(@RequestBody EmpresaEntrada empresaEntrada) throws Exception {
        return empresaService.criar(empresaEntrada);
    }

    @RequestMapping("/obterAtualizar")
    public ModelAndView obterAtualizar() {
        ModelAndView mv = new ModelAndView("/admin/empresa/atualizar");

        mv.addObject("empresa", empresaService.listar());

        return mv;
    }

    @PostMapping("/atualizar")
    public EmpresaSaida atualizar(@RequestBody EmpresaEntrada empresaEntrada) {
        return empresaService.atualizar(empresaEntrada);
    }

    @GetMapping("/listar")
    public EmpresaSaida listar() {
        return empresaService.listar();
    }

    @RequestMapping("/gerenciar")
    public void gerenciarCadastro(HttpServletResponse response) throws Exception {
        boolean empresaEntityOptional = false; //TODO
        if (empresaEntityOptional) {
            response.sendRedirect("http://localhost:8088/empresa/obterAtualizar");
        } else {
            response.sendRedirect("http://localhost:8088/empresa/obterCriar");
        }
    }
}
