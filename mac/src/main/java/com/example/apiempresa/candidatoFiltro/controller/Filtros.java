package com.example.apiempresa.candidatoFiltro.controller;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.cargo.model.CargoSaida2;
import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisEntity;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.dadosPessoais.service.DadosPessoaisService;
import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.ferramenta.service.FerramentaService;
import com.example.apicandidato.framework.service.FrameworkService;
import com.example.apicandidato.habilidades.model.HabilidadesSaida;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import com.example.apiempresa.candidatoFiltro.service.CandidatoFiltroService;
import com.example.apiempresa.model.FiltroEntrada;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "filtro", produces = "application/json")
@Configuration
@CrossOrigin
public class Filtros {
    @Autowired
    private DadosPessoaisService dadosPessoaisService;
    @Autowired
    private HabilidadesService habilidadesService;
    @Autowired
    private VagaRepository vagaRepository;
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

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));
        modelAndView.addObject("filtro", new FiltroEntrada());

        return modelAndView;
    }

    @GetMapping("/buscarCargoVaga")
    public ModelAndView buscarCargo2(@RequestParam(value = "idCargo", required = false) Long idCargo, @RequestParam Long idVaga) {
        ModelAndView modelAndView = new ModelAndView("/admin/vaga/filtroInscritos");

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setId(idVaga);

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));
        modelAndView.addObject("filtro", new FiltroEntrada());
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

    @GetMapping("/palavraChave/{id}")
    public ModelAndView filtrarPorPalavraChaveResumoProfissional(@RequestParam(value = "chave1", required = false) String chave1,
                                                                 @PathVariable(value = "id", required = true) Long idVaga) throws Exception {
        String parametro = null;

        if (chave1 != "") {
            parametro = chave1;
        }

        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if (!vagaEntityOptional.isPresent()) {
            throw new Exception("Vaga não encontrada, busque novamente!");
        }

        List<HabilidadesSaida> cliente = habilidadesService.filtrarPorResumoProfissional(idVaga, parametro, vagaEntityOptional.get());

        if (cliente == null) {
            HabilidadesSaida clienteNull = new HabilidadesSaida();
            ModelAndView mv = new ModelAndView("/admin/candidato/buscarPorPalavraChave");
            mv.addObject("cliente", clienteNull);
            mv.addObject("vaga", vagaEntityOptional.get());
            return mv;
        }

        ModelAndView mv = new ModelAndView("/admin/candidato/buscarPorPalavraChave");
        mv.addObject("cliente", cliente);
        mv.addObject("vaga", vagaEntityOptional.get());
        return mv;
    }
}
