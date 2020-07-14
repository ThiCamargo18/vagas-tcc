package com.example.mac.endpoints.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
@Configuration
@CrossOrigin
public class EndpointAdmin {

    @RequestMapping("/admin")
    public ModelAndView index(){
        return new ModelAndView("/admin/index");
    }

    @RequestMapping("/sair")
    public String sair(){
        return "Sair";
    }
}
