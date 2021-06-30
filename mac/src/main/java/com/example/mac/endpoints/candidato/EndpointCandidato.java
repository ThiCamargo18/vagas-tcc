package com.example.mac.endpoints.candidato;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.cliente.service.ClienteAutenticacaoService;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.role.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping
@Configuration
@CrossOrigin
public class EndpointCandidato {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteAutenticacaoService clienteAutenticacaoService;

    @RequestMapping("/")
    public ModelAndView paginaInicial(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login/login");
    }

//    @PostMapping("/efetuaLogin")
//    public ModelAndView efetuaLogin(@Valid ClienteEntrada clienteEntrada, HttpServletRequest request) throws Exception {
//        ClienteEntity clienteEntity = clienteService.buscarEVerificarExistenciaClientePorEmail(clienteEntrada.getEmail());
//
//        if(clienteEntity == null){
//            throw new Exception("E-mail não cadastrado");
//        }
//        if(!clienteEntrada.getSenha().equals(clienteEntity.getSenha())){
//            throw new Exception("Senha incorreta");
//        }
//
//        ClienteSessao clienteSessao = new ClienteSessao();
//        clienteSessao.setId(clienteEntity.getId());
//        clienteSessao.setNome(clienteEntity.getNome());
//        clienteSessao.setPrimeiroAcesso(clienteEntity.getPrimeiroAcesso());
//
//        HttpSession session = request.getSession(); //TODO caso nao tenha uma sessão eu instancio ooutra
//        session.setAttribute("usuarioLogado",clienteSessao);
//
//        ModelAndView mv = new ModelAndView("/candidato/index");
//        mv.addObject("candidato",clienteSessao);
//
//        return mv;
//    }

    @RequestMapping("/registrar")
    public ModelAndView registrar(){
        return new ModelAndView("/login/registrar");
    }

    @PostMapping("/registrar")
    public ModelAndView registrar(@Valid ClienteEntrada clienteEntrada, BindingResult result) throws Exception {
        ClienteEntity cliente = clienteAutenticacaoService.findByEmail(clienteEntrada.getEmail());
        if (cliente != null){
            result.rejectValue("email", null, "Já existe um candidato cadastrado com esse mesmo e-mail!");
        }

        if (result.hasErrors()){
            return new ModelAndView("/login/registrar");
        }

        clienteEntrada.setRoles(Arrays.asList(new RoleEntity("CANDIDATO")));
        clienteAutenticacaoService.save(clienteEntrada);

        return new ModelAndView("/login/login");
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
