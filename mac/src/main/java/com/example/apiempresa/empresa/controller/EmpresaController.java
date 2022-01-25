package com.example.apiempresa.empresa.controller;

import com.example.apiempresa.empresa.model.EmpresaEntrada;
import com.example.apiempresa.empresa.model.EmpresaSaida;
import com.example.apiempresa.empresa.service.EmpresaService;
import com.example.apiempresa.model.EmpresaSessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "empresa", produces = "application/json")
@Configuration
@CrossOrigin
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @RequestMapping("/obterAtualizar")
    public ModelAndView obterAtualizar(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/admin/empresa/atualizar");

        mv.addObject("empresa", empresaService.buscarPorId(EmpresaSessao.getId(request)));

        return mv;
    }

    @PostMapping("/obterAtualizar")
    public String atualizar(@ModelAttribute EmpresaEntrada empresaEntrada, HttpServletRequest request) {
        empresaService.atualizar(empresaEntrada, EmpresaSessao.getId(request));

        return "redirect:/empresa/obterAtualizar";
    }
}
