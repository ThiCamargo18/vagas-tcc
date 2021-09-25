package com.example.mac.candidato.controller;

import com.example.mac.candidato.model.CandidatoEntrada;
import com.example.mac.candidato.model.CandidatoSaida;
import com.example.mac.candidato.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "cliente", produces = "application/json")
@Configuration
@CrossOrigin
public class CandidatoController {
    @Autowired
    CandidatoService candidatoService;

    @PutMapping("/atualizar/{id}")
    public CandidatoSaida atualizar(@PathVariable Long id, @RequestBody CandidatoEntrada candidatoEntrada) throws Exception {
        CandidatoSaida saida = candidatoService.atualizar(candidatoEntrada,id);

        return saida;
    }

    @PutMapping("/atualizarSenha/{id}")
    public CandidatoSaida atualizarSenha(@PathVariable Long id, @RequestBody CandidatoEntrada candidatoEntrada) throws Exception {
        CandidatoSaida saida = candidatoService.atualizarSenha(candidatoEntrada,id);

        return saida;
    }

}
