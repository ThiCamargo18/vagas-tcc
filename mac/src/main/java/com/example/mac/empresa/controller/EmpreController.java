package com.example.mac.empresa.controller;

import com.example.mac.empresa.model.EmpresaEntrada;
import com.example.mac.empresa.model.EmpresaSaida;
import com.example.mac.empresa.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "empresa", produces = "application/json")
@Configuration
@CrossOrigin
public class EmpreController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/criar")
    public EmpresaSaida criar(@RequestBody EmpresaEntrada empresaEntrada) throws Exception {
        EmpresaSaida empresaSaida = empresaService.criar(empresaEntrada);

        return empresaSaida;
    }

    @PutMapping("/atualizar/{id}")
    public EmpresaSaida atualizar(@PathVariable Long id,@RequestBody EmpresaEntrada empresaEntrada){
        EmpresaSaida empresaSaida = empresaService.atualizar(id,empresaEntrada);

        return empresaSaida;
    }

    @GetMapping("/listar")
    public EmpresaSaida listar(){
        EmpresaSaida empresaSaida = empresaService.listar();

        return empresaSaida;
    }
}
