package com.example.apicandidato.autenticacao;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoEntrada;
import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.security.CandidatoAutenticacaoService;
import com.example.apicandidato.security.CandidatoValidacao;
import com.example.security.model.RoleEntity;
import com.example.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class LoginCandidato {
    @Autowired
    private CandidatoAutenticacaoService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CandidatoValidacao userValidator;

    @GetMapping("/registrar")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new CandidatoEntrada());

        return "/candidato/login/registrar";
    }

    @PostMapping("/registrar")
    public String registration(@ModelAttribute("userForm") CandidatoEntrada userForm, BindingResult bindingResult, HttpServletRequest request) {
        //userValidator.validate(userForm, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }

        userForm.setRoles(Collections.singletonList(new RoleEntity("CANDIDATO")));

        CandidatoEntity save = userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getSenha());

        CandidatoSessao candidatoSessao = new CandidatoSessao();
        candidatoSessao.setId(save.getId());
        candidatoSessao.setNome(userForm.getNome());

        HttpSession session = request.getSession();
        session.setAttribute("usuarioLogado", candidatoSessao);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) return "redirect:/";

        if (error != null)
            model.addAttribute("error", "Usuario ou senha invalidos.");

        if (logout != null)
            model.addAttribute("message", "Loggout realizado com sucesso.");

        return "/candidato/login/login";
    }
}
