package com.example.mac.endpoints.candidato;

import com.example.mac.entrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "candidato/entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class Entrevista {
    @Autowired
    EntrevistaService entrevistaService;

    @GetMapping("/buscar")
    public ModelAndView buscar(){
        return new ModelAndView("/candidato/entrevista/buscar");
    }

    @GetMapping("/feedback")
    public ModelAndView feedback(){
        return new ModelAndView("/candidato/entrevista/feedback");
    }
}
