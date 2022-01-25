package com.example.apiempresa.candidatoFiltro.controller;

import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.clienteCadastro.service.ClienteCadastroService;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apiempresa.candidatoFiltro.service.CandidatoFiltroService;
import com.example.apiempresa.model.FiltroEntrada;
import com.example.apiempresa.vaga.model.VagaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "filtro", produces = "application/json")
@Configuration
@CrossOrigin
public class Filtros {
    @Autowired
    private DadosPessoaisService dadosPessoaisService;
    @Autowired
    private ClienteCadastroService clienteCadastroService;
    @Autowired
    private CandidatoFiltroService candidatoFiltroService;
    @Autowired
    private CargoService cargoService;

    @GetMapping("/selecionar") //chamará o endpoint /Buscar
    public ModelAndView buscarCandidato() {
        ModelAndView modelAndView = new ModelAndView("/admin/candidato/selecionar");

        modelAndView.addObject("filtro", new FiltroEntrada());
        modelAndView.addObject("cargo", cargoService.buscarEMapear(null));

        return modelAndView;
    }

    @GetMapping("/buscarNome") //chamará o endpoint BuscarCandidato
    public ModelAndView selecionarClienteParaBuscar() {
        return new ModelAndView("/admin/candidato/buscarNome");
    }

    @GetMapping("/buscarCandidato")
    public ModelAndView buscarCandidatoPorNomeOuCpf(@RequestParam String nome) {
        List<DadosPessoaisEntity> dadosPessoaisEntity = dadosPessoaisService.buscarPorNome(nome);
        ModelAndView mv = new ModelAndView("/admin/candidato/exibirCandidato");
        mv.addObject("cliente", dadosPessoaisEntity);

        return mv;
    }

    @GetMapping("/buscarCargo")
    public ModelAndView buscarCargo(@RequestParam(value = "idCargo", required = false) Long idCargo) {
        ModelAndView modelAndView = new ModelAndView("/admin/candidato/selecionar");

        FiltroEntrada filtroEntrada = new FiltroEntrada();
        filtroEntrada.setIdCargo(idCargo);

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));
        modelAndView.addObject("filtro", filtroEntrada);

        return modelAndView;
    }

    @GetMapping("/buscarCargoVaga")
    public ModelAndView buscarCargo2(@RequestParam(value = "idCargo", required = false) Long idCargo, @RequestParam Long idVaga) {
        ModelAndView modelAndView = new ModelAndView("/admin/vaga/filtroInscritos");

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setId(idVaga);

        FiltroEntrada filtroEntrada = new FiltroEntrada();
        filtroEntrada.setIdCargo(idCargo);

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));
        modelAndView.addObject("filtro", filtroEntrada);
        modelAndView.addObject("vaga", vagaSaida);

        return modelAndView;
    }

    @PostMapping("/buscar")
    public ModelAndView filtrar(@ModelAttribute FiltroEntrada filtroEntrada) {
        return candidatoFiltroService.filtrar(filtroEntrada);
    }

    @PostMapping("/filtroInscritos")
    public ModelAndView filtrarInscritos(@ModelAttribute FiltroEntrada filtroEntrada, @RequestParam Long idVaga) throws Exception {
        return candidatoFiltroService.filtrarInscritos(filtroEntrada, idVaga);
    }

    @GetMapping("/buscarCandidato/{id}")
    public ModelAndView buscarCadastroCompleto(@PathVariable Long id) throws Exception {

        ModelAndView mv = new ModelAndView("/admin/candidato/cadastroCompleto");

        ClienteCadastroSaida saida = clienteCadastroService.buscar(id);

        mv.addObject("cliente", saida.getCliente());
        mv.addObject("dadosPessoais", saida.getDadosPessoais());
        mv.addObject("dadosAdicionais", saida.getDadosAdicionais());
        mv.addObject("registro", saida.getRegistro());

        return mv;
    }

}
