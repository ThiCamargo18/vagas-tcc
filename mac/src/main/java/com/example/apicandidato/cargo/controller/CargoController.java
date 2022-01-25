package com.example.apicandidato.cargo.controller;

import com.example.apicandidato.candidato.mapper.CandidatoMapper;
import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.clienteCadastro.model.CadastroAdicionalEntrada;
import com.example.apicandidato.ferramenta.service.FerramentaService;
import com.example.apicandidato.framework.service.FrameworkService;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "cargo", produces = "application/json")
public class CargoController {
    @Autowired
    private CargoService cargoService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private FrameworkService frameworkService;
    @Autowired
    private TecnologiaService tecnologiaService;
    @Autowired
    private FerramentaService ferramentaService;

    @PostMapping("/criar")
    public CargoEntity criar(@RequestBody CargoEntity cargoEntity, HttpServletRequest request){
        return cargoService.criar(cargoEntity, 1L);
    }

    @GetMapping("/buscar")
    public CargoEntity buscar(@RequestParam("id") Long idCargo){
        return cargoService.buscar(idCargo);
    }
}
