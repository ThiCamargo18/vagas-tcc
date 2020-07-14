package com.example.mac.endpoints.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "configuracao", produces = "application/json")
@Configuration
@CrossOrigin
public class Configuracao {

    @RequestMapping("/alterar")
    public String alterar(){
        return "Alterar";
    }

    @RequestMapping("/perfil")
    public String perfil(){
        return "Perfil";
    }


}
