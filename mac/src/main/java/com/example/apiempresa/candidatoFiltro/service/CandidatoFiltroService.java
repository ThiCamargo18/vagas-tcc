package com.example.apiempresa.candidatoFiltro.service;

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
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import com.example.apiempresa.model.FiltroEntrada;
import com.example.apiempresa.vaga.mapper.VagaMapper;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.repository.VagaRepository;
import com.example.apiempresa.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidatoFiltroService {
    @Autowired
    private DadosPessoaisService dadosPessoaisService;
    @Autowired
    private VagaService vagaService;
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

    public ModelAndView filtrar(FiltroEntrada filtroEntrada) {
        DadosPessoaisEntity entity = new DadosPessoaisEntity(filtroEntrada.getFormacao(), filtroEntrada.getCategoria(), filtroEntrada.getEndereco());

        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        List<Long> idCandidatos = this.buscarIdCandidatos(listaSaida);

        listaSaida = this.filtroTecnologias(filtroEntrada, idCandidatos,  null);

        ModelAndView mv;

        if (filtroEntrada.getIdCargo() == null)
            mv = new ModelAndView("/admin/candidato/perfil");
        else
            mv = new ModelAndView("/admin/candidato/selecionar");

        CargoSaida2 cargoSaida = cargoService.buscarEMapear(filtroEntrada.getIdCargo());

        mv.addObject("cliente", listaSaida);
        mv.addObject("filtro", filtroEntrada);
        mv.addObject("cargo", cargoSaida);

        return mv;
    }

    public ModelAndView filtrarInscritos(FiltroEntrada filtroEntrada, Long idVaga) throws Exception {
        DadosPessoaisEntity entity = new DadosPessoaisEntity(filtroEntrada.getFormacao(), filtroEntrada.getCategoria(), filtroEntrada.getEndereco());

        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        VagaEntity vagaEntity = vagaService.buscarVagaEntity(idVaga);

        List<CandidatoEntity> candidatosVaga = vagaEntity.getClientes();

        List<Long> idCandidatos = this.buscarIdCandidatos(listaSaida);

        listaSaida = this.filtroTecnologias(filtroEntrada, idCandidatos, candidatosVaga);

        ModelAndView mv = new ModelAndView("/admin/vaga/filtroInscritos");

        CargoSaida2 cargoSaida = cargoService.buscarEMapear(filtroEntrada.getIdCargo());

        mv.addObject("cliente", listaSaida);
        mv.addObject("filtro", filtroEntrada);
        mv.addObject("cargo", cargoSaida);
        mv.addObject("vaga", VagaMapper.INSTANCE.mapToSaida(vagaEntity));

        return mv;
    }

    private List<Long> buscarIdCandidatos(List<DadosPessoaisSaida> listaSaida) {
        List<Long> idCandidatos = new ArrayList<>();

        for (DadosPessoaisSaida dadosPessoaisSaida : listaSaida)
            idCandidatos.add(dadosPessoaisSaida.getIdCandidato());

        return idCandidatos;
    }

    private List<DadosPessoaisSaida> filtroTecnologias(FiltroEntrada filtroEntrada, List<Long> idCandidatosFiltrados, List<CandidatoEntity> candidatosVaga) {
        if (!filtroEntrada.getTecnologia().isEmpty() || !filtroEntrada.getFramework().isEmpty() || !filtroEntrada.getFerramenta().isEmpty()) {
            List<CandidatoEntity> filtro;

            List<CandidatoEntity> listaCandidatosFiltrados = candidatoService.findAllById(idCandidatosFiltrados);
            List<CandidatoEntity> candidatoTecnologia = tecnologiaService.buscarCandidatos(filtroEntrada.getTecnologia());
            List<CandidatoEntity> candidatoFramework = frameworkService.buscarCandidatos(filtroEntrada.getFramework());
            List<CandidatoEntity> candidatoFerramenta = ferramentaService.buscarCandidatos(filtroEntrada.getFerramenta());

            if (candidatosVaga != null && !candidatosVaga.isEmpty()) {
                filtro = candidatoService.filtrar(candidatosVaga, listaCandidatosFiltrados);
                filtro = candidatoService.filtrar(filtro, candidatoTecnologia);
                filtro = candidatoService.filtrar(filtro, candidatoFramework);
                filtro = candidatoService.filtrar(filtro, candidatoFerramenta);
            } else {
                filtro = candidatoService.filtrar(listaCandidatosFiltrados, candidatoTecnologia);
                filtro = candidatoService.filtrar(filtro, candidatoFramework);
                filtro = candidatoService.filtrar(filtro, candidatoFerramenta);
            }

            idCandidatosFiltrados = new ArrayList<>();

            for (CandidatoEntity candidatoEntity : filtro) idCandidatosFiltrados.add(candidatoEntity.getId());

            return dadosPessoaisService.findAllByIdCandidato(idCandidatosFiltrados);
        }

        return new ArrayList<>();
    }
}
