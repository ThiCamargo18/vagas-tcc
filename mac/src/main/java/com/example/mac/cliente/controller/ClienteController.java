package com.example.mac.cliente.controller;

import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "cliente", produces = "application/json")
@Configuration
@CrossOrigin
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PutMapping("/atualizar/{id}")
    public ClienteSaida atualizar(@PathVariable Long id, @RequestBody ClienteEntrada clienteEntrada) throws Exception {
        ClienteSaida saida = clienteService.atualizar(clienteEntrada,id);

        return saida;
    }

    @PutMapping("/atualizarSenha/{id}")
    public ClienteSaida atualizarSenha(@PathVariable Long id, @RequestBody ClienteEntrada clienteEntrada) throws Exception {
        ClienteSaida saida = clienteService.atualizarSenha(clienteEntrada,id);

        return saida;
    }

}
