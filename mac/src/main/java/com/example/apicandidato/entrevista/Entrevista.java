package com.example.apicandidato.entrevista;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apiempresa.empresa.service.EmpresaService;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import com.example.apiempresa.entrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "candidato/entrevista", produces = "application/json")
@Configuration
@CrossOrigin
public class Entrevista {
    @Autowired
    private EntrevistaService entrevistaService;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/buscar")
    public ModelAndView buscar(HttpServletRequest request) throws Exception {
        List<EntrevistaSaida> entrevistaSaida = entrevistaService.buscarPorIdCandidato(CandidatoSessao.getId(request));

        int index = 0;

        for (EntrevistaSaida entrevista: entrevistaSaida) {
            entrevistaSaida.get(index).setEmpresaSaida(empresaService.buscarPorId(entrevista.getIdEmpresa()));

            index++;
        }

        ModelAndView mv = new ModelAndView("/candidato/entrevista/buscar");

        mv.addObject("entrevista",entrevistaSaida);


        return mv;
    }
}
