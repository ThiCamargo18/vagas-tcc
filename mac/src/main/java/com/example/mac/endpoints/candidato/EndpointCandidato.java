package com.example.mac.endpoints.candidato;

import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.security.service.ClienteAutenticacaoService;
import com.example.mac.cliente.service.ClienteService;
import com.example.mac.security.model.RoleEntity;
import com.example.mac.security.service.ClienteValidacao;
import com.example.mac.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
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
    @Autowired
    private ClienteValidacao clienteValidacao;
    @Autowired
    private SecurityService securityService;

    @RequestMapping("/")
    public ModelAndView paginaInicial(){
        return new ModelAndView("/index");
    }

    @GetMapping("/login")
    public ModelAndView login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return null;
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        ModelAndView mv = new ModelAndView("/login/login");

        return mv;
    }

//    @PostMapping("/login")
//    public ModelAndView efetuaLogin(@Valid ClienteEntrada clienteEntrada, HttpServletRequest request) throws Exception {
//        if (securityService.isAuthenticated()) {
//            return null;
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
        if (securityService.isAuthenticated()) {
            return null;
        }

        return new ModelAndView("/login/registrar");
    }

    @PostMapping("/registrar")
    public ModelAndView registrar(@Valid ClienteEntrada clienteEntrada, BindingResult result) throws Exception {
        clienteValidacao.validate(clienteEntrada, result);

        if (result.hasErrors()) {
            return new ModelAndView("/login/registrar");
        }

        clienteEntrada.setRoles(Arrays.asList(new RoleEntity("CANDIDATO")));

        clienteAutenticacaoService.save(clienteEntrada);

        securityService.autoLogin(clienteEntrada.getEmail(), clienteEntrada.getRepetirSenha());

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
