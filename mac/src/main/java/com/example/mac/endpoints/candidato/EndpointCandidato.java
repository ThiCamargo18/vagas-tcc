package com.example.mac.endpoints.candidato;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping
@Configuration
@CrossOrigin
public class EndpointCandidato {
    @Autowired
    ClienteService clienteService;

    @RequestMapping("/")
    public ModelAndView paginaInicial(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login/login");
    }

    @RequestMapping("/registrar")
    public ModelAndView registrar(){
        return new ModelAndView("/login/registrar");
    }

    @PostMapping("/registrar")
    public ModelAndView registrar(@Valid ClienteEntrada clienteEntrada, HttpServletResponse response) throws Exception {
        clienteService.criar(clienteEntrada);

        return new ModelAndView("/login/login");
    }

    @PostMapping("/efetuaLogin")
    public ModelAndView efetuaLogin(@Valid ClienteEntrada clienteEntrada, HttpServletRequest request) throws Exception {
        ClienteEntity clienteEntity = clienteService.buscarEVerificarExistenciaClientePorCpf(clienteEntrada.getCpf());

        if(clienteEntity == null){
            throw new Exception("CPF não encontrado");
        }
        if(!clienteEntrada.getSenha().equals(clienteEntity.getSenha())){
            throw new Exception("Senha incorreta");
        }

        ClienteSessao clienteSessao = new ClienteSessao();
        clienteSessao.setId(clienteEntity.getId());
        clienteSessao.setNome(clienteEntity.getNome());
        clienteSessao.setPrimeiroAcesso(clienteEntity.getPrimeiroAcesso());

        HttpSession session = request.getSession(); //TODO caso nao tenha uma sessão eu instancio ooutra
        session.setAttribute("usuarioLogado",clienteSessao);

        ModelAndView mv = new ModelAndView("/candidato/index");
        mv.addObject("candidato",clienteSessao);

        return mv;
    }

    @RequestMapping("/inicio")
    public ModelAndView inicio(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");
        ModelAndView mv = new ModelAndView("/candidato/index");
        mv.addObject("candidato",clienteSessao);

        return mv;
    }

    @RequestMapping("/config")
    public String configuracoes(){
        return "Config";
    }

    @RequestMapping("/logout")
    public String sair(){
        return "Sair";
    }
}
