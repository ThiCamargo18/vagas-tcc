package com.example.apicandidato.cargo.controller;

import com.example.apicandidato.cargo.model.CargoEntity;
import com.example.apicandidato.cargo.model.CargoSaida2;
import com.example.apicandidato.cargo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "cargo", produces = "application/json")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @PostMapping("/criar")
    public CargoEntity criar(@RequestBody CargoEntity cargoEntity, HttpServletRequest request){
        return cargoService.criar(cargoEntity, 1L);
    }

    @GetMapping("/buscar")
    public CargoEntity buscar(@RequestParam("id") Long idCargo){
        return cargoService.buscar(idCargo);
    }

    @GetMapping("/teste")
    public CargoSaida2 teste(){
        return cargoService.buscarEMapear(1L);
    }
}
