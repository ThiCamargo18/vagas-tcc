package com.example.mac.empresa.controller;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.empresa.model.EmpresaEntity;
import com.example.mac.empresa.model.EmpresaEntrada;
import com.example.mac.empresa.model.EmpresaSaida;
import com.example.mac.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "empresa", produces = "application/json")
@Configuration
@CrossOrigin
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @RequestMapping("/gerenciar")
    public void gerenciarCadastro(HttpServletResponse response) throws Exception {
        boolean empresaEntityOptional = empresaService.verificarSeJaFoiCriadoAEmpresa();
        if (empresaEntityOptional == true) {
            response.sendRedirect("http://localhost:8088/empresa/obterAtualizar");
        } else if (empresaEntityOptional == false) {
            response.sendRedirect("http://localhost:8088/empresa/obterCriar");
        }
    }

    @RequestMapping("/obterCriar")
    public ModelAndView obterCriar(){
        return new ModelAndView("/admin/empresa/criar");
    }


    @PostMapping("/nova")
    public EmpresaSaida criar(@RequestBody EmpresaEntrada empresaEntrada) throws Exception {
        EmpresaSaida empresaSaida = empresaService.criar(empresaEntrada);

        return empresaSaida;
    }

    @RequestMapping("/obterAtualizar")
    public ModelAndView obterAtualizar(){
        ModelAndView mv = new ModelAndView("/admin/empresa/atualizar");
        EmpresaSaida empresaSaida = empresaService.listar();
        mv.addObject("empresa",empresaSaida);
        return mv;
    }

    @PostMapping("/atualizar")
    public EmpresaSaida atualizar(@RequestBody EmpresaEntrada empresaEntrada){
        EmpresaSaida empresaSaida = empresaService.atualizar(empresaEntrada);

        return empresaSaida;
    }

    @GetMapping("/listar")
    public EmpresaSaida listar(){
        EmpresaSaida empresaSaida = empresaService.listar();

        return empresaSaida;
    }
}
