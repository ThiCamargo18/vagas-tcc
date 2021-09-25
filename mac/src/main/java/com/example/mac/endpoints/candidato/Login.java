package com.example.mac.endpoints.candidato;

import com.example.mac.candidato.model.CandidatoEntrada;
import com.example.mac.candidato.model.CandidatoSessao;
import com.example.mac.security.model.RoleEntity;
import com.example.mac.security.service.ClienteAutenticacaoService;
import com.example.mac.security.service.ClienteValidacao;
import com.example.mac.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class Login {
    @Autowired
    private ClienteAutenticacaoService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ClienteValidacao userValidator;

    @GetMapping("/registrar")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new CandidatoEntrada());

        return "/login/registrar";
    }

    @PostMapping("/registrar")
    public ModelAndView registration(@ModelAttribute("userForm") CandidatoEntrada userForm, BindingResult bindingResult, HttpServletRequest request) {
        //userValidator.validate(userForm, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }

        userForm.setRoles(Collections.singletonList(new RoleEntity("CANDIDATO")));

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getSenha());

        CandidatoSessao candidatoSessao = new CandidatoSessao();
        candidatoSessao.setId(1L);
        candidatoSessao.setNome(userForm.getNome());
        candidatoSessao.setPrimeiroAcesso(true);

        HttpSession session = request.getSession(); //TODO caso nao tenha uma sessão eu instancio ooutra
        session.setAttribute("usuarioLogado", candidatoSessao);

        ModelAndView mv = new ModelAndView("/candidato/index");
        mv.addObject("candidato", candidatoSessao);

        return mv;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "/login/login";
    }
}
