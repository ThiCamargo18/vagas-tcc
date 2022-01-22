package com.example.apiempresa.autenticacao;

import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.empresa.model.EmpresaEntrada;
import com.example.apiempresa.model.EmpresaSessao;
import com.example.apiempresa.security.EmpresaAutenticacaoService;
import com.example.security.model.RoleEntity;
import com.example.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class LoginEmpresa {
    @Autowired
    private EmpresaAutenticacaoService empresaAutenticacaoService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/registrarEmpresa")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/admin";
        }

        model.addAttribute("userForm", new EmpresaEntrada());

        return "/admin/login/registrar";
    }

    @PostMapping("/registrarEmpresa")
    public String registration(@ModelAttribute("userForm") EmpresaEntrada empresaEntrada, HttpServletRequest request) {
        empresaEntrada.setRoles(Collections.singletonList(new RoleEntity("EMPRESA")));

        EmpresaEntity empresaEntity = empresaAutenticacaoService.save(empresaEntrada);

        securityService.autoLogin(empresaEntrada.getLogin(), empresaEntrada.getSenha());

        EmpresaSessao empresaSessao = new EmpresaSessao(empresaEntity.getId(), empresaEntity.getLogin(), empresaEntity.getRazaoSocial());

        HttpSession session = request.getSession();
        session.setAttribute("empresaLogada", empresaSessao);

        return "redirect:/admin";
    }

    @GetMapping("/loginEmpresa")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/admin";
        }

        if (error != null)
            model.addAttribute("error", "Usuario ou senha invalidos.");

        if (logout != null)
            model.addAttribute("message", "Loggout realizado com sucesso.");

        return "/admin/login/login";
    }
}
