package com.example.apicandidato.cargo.controller;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.framework.model.CandidatoFrameworkEntity;
import com.example.apicandidato.framework.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "cargo", produces = "application/json")
public class CargoController {
    @Autowired
    private CargoService cargoService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private FrameworkService frameworkService;

    @PostMapping("/criar")
    public CargoEntity criar(@RequestBody CargoEntity cargoEntity, HttpServletRequest request){
        return cargoService.criar(cargoEntity, 1L);
    }

    @GetMapping("/buscar")
    public CargoEntity buscar(@RequestParam("id") Long idCargo){
        return cargoService.buscar(idCargo);
    }

    @GetMapping("/teste")
    public List<CandidatoFrameworkEntity> teste(@RequestParam(value = "param1", required = false) Long param1, @RequestParam(value = "param2", required = false) Long param2){
        List<Long> entrada = new ArrayList<>();
        entrada.add(param1);
        entrada.add(param2);

        List<CandidatoFrameworkEntity> filtrar = frameworkService.filtrar(entrada);

        return filtrar;
    }
}
