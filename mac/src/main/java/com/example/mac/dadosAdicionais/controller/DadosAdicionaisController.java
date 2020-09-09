package com.example.mac.dadosAdicionais.controller;

import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSaida;
import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.clienteCadastro.controller.ClienteCadastroController;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisEntrada;
import com.example.mac.dadosAdicionais.model.DadosAdicionaisSaida;
import com.example.mac.dadosAdicionais.service.DadosAdicionaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "dadosAdicionais", produces = "application/json")
@Configuration
@CrossOrigin
public class DadosAdicionaisController {
    @Autowired
    DadosAdicionaisService dadosAdicionaisService;
    @Autowired
    ClienteCadastroController clienteCadastroController;

    @PostMapping("/atualizar")
    public ModelAndView atualizar(@Valid DadosAdicionaisEntrada dadosAdicionaisEntrada,
                                  HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.equals(null)){
            throw new Exception("Você ainda não fez login, faça antes de fazer as alterações!");
        }

        DadosAdicionaisSaida saida = dadosAdicionaisService.atualizar(dadosAdicionaisEntrada,clienteSessao.getId());

        return clienteCadastroController.procurar(request);
    }
}
