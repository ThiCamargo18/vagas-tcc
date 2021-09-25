package com.example.mac.endpoints.admin;

import com.example.mac.candidato.repository.CandidatoRepository;
import com.example.mac.endpoints.model.AdminIndex;
import com.example.mac.entrevista.repository.EntrevistaRepository;
import com.example.mac.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CandidatoRepository candidatoRepository;
    @Autowired
    EntrevistaRepository entrevistaRepository;
    @Autowired
    VagaRepository vagaRepository;

    @RequestMapping("/admin")
    public ModelAndView index(){
        long numeroClientes = candidatoRepository.count();
        long numeroEntrevistas = entrevistaRepository.count();
        long numeroVagas = vagaRepository.count();
        ModelAndView mv = new ModelAndView("/admin/index");
        AdminIndex adminIndex = new AdminIndex();
        adminIndex.setNumeroEntrevistas(numeroEntrevistas);
        adminIndex.setNumeroVagas(numeroVagas);
        adminIndex.setNumeroClientes(numeroClientes);
        mv.addObject("filtro",adminIndex);
        return mv;
    }

    @RequestMapping("/sair")
    public String sair(){
        return "Sair";
    }
}
