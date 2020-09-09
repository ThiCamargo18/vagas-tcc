package com.example.mac.dadosPessoais.controller;


import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.clienteCadastro.controller.ClienteCadastroController;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntrada;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.enums.CategoriaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "dadosPessoais", produces = "application/json")
@Configuration
@CrossOrigin
public class DadosPessoaisController {
    @Autowired
    DadosPessoaisService dadosPessoaisService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid DadosPessoaisEntrada dadosPessoaisEntrada,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça por gentileza!");
        }

        DadosPessoaisSaida saida = dadosPessoaisService.atualizar(dadosPessoaisEntrada,clienteSessao.getId());

        return clienteCadastroController.procurar(request);
    }

}
