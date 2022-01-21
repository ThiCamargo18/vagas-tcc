package com.example.apiempresa.endpoints;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSaida;
import com.example.apicandidato.candidato.service.CandidatoService;
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
import com.example.apiempresa.model.FiltoEntrada;
import com.example.apiempresa.vaga.model.VagaEntity;
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
    private CandidatoService candidatoService;
    @Autowired
    private FrameworkService frameworkService;
    @Autowired
    private TecnologiaService tecnologiaService;
    @Autowired
    private FerramentaService ferramentaService;
    @Autowired
    private CargoService cargoService;

    @GetMapping("/selecionar") //chamará o endpoint /Buscar
    public ModelAndView buscarCandidato() {
        ModelAndView modelAndView = new ModelAndView("/admin/candidato/selecionar");

        modelAndView.addObject("filtro", new FiltoEntrada());
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

    @PostMapping("/buscar")
    public ModelAndView filtrar(@ModelAttribute FiltoEntrada filtoEntrada) {
        DadosPessoaisEntity entity = new DadosPessoaisEntity();
        entity.setFormacao(filtoEntrada.getFormacao());
        entity.setCategoria(filtoEntrada.getCategoria());
        entity.setCidade(filtoEntrada.getEndereco());

        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        ModelAndView mv = new ModelAndView("/admin/candidato/perfil");

        if (!filtoEntrada.getTecnologias().isEmpty() && !filtoEntrada.getFrameworks().isEmpty() && !filtoEntrada.getFerramentas().isEmpty()) {
            List<CandidatoEntity> filtro;
            List<Long> idCandidatos = new ArrayList<>();

            for (DadosPessoaisSaida dadosPessoaisSaida : listaSaida) idCandidatos.add(dadosPessoaisSaida.getIdCandidato());

            List<CandidatoEntity> listaCandidatosBase = candidatoService.findAllById(idCandidatos);
            List<CandidatoEntity> candidatoTecnologia = tecnologiaService.buscarCandidatos(filtoEntrada.getTecnologias());
            List<CandidatoEntity> candidatoFramework = frameworkService.buscarCandidatos(filtoEntrada.getFrameworks());
            List<CandidatoEntity> candidatoFerramenta = ferramentaService.buscarCandidatos(filtoEntrada.getFerramentas());

            filtro = candidatoService.filtrar(listaCandidatosBase, candidatoTecnologia);
            filtro = candidatoService.filtrar(filtro, candidatoFramework);
            filtro = candidatoService.filtrar(filtro, candidatoFerramenta);

            idCandidatos = new ArrayList<>();

            for (CandidatoEntity candidatoEntity : filtro) idCandidatos.add(candidatoEntity.getId());

            listaSaida = dadosPessoaisService.findAllByIdCandidato(idCandidatos);
        }

        mv.addObject("cliente", listaSaida);
        mv.addObject("filtro", filtoEntrada);
        mv.addObject("cargo", cargoService.buscarEMapear(filtoEntrada.getIdCargo()));

        return mv;
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
