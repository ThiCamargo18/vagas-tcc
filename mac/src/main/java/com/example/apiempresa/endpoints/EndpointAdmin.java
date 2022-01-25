package com.example.apiempresa.endpoints;

import com.example.apicandidato.candidato.repository.CandidatoRepository;
import com.example.apiempresa.model.AdminIndex;
import com.example.apiempresa.entrevista.repository.EntrevistaRepository;
import com.example.apiempresa.model.EmpresaSessao;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
@Configuration
@CrossOrigin
public class EndpointAdmin {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private VagaRepository vagaRepository;

    @RequestMapping("/admin")
    public ModelAndView index(HttpServletRequest request){
        long numeroClientes = candidatoRepository.count();
        List<VagaEntity> excluida = vagaRepository.findAllByIdEmpresaAndStatusNot(EmpresaSessao.getId(request), "EXCLUIDA");

        ModelAndView mv = new ModelAndView("/admin/index");
        AdminIndex adminIndex = new AdminIndex();

        adminIndex.setNumeroVagas((long) excluida.size());
        adminIndex.setNumeroClientes(numeroClientes);
        mv.addObject("filtro",adminIndex);
        return mv;
    }

    @RequestMapping("/sair")
    public String sair(){
        return "Sair";
    }
}
