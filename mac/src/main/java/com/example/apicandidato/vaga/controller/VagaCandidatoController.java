package com.example.apicandidato.vaga.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.vaga.service.VagaCandidatoService;
import com.example.apiempresa.vaga.mapper.VagaMapper;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "candidato/vaga", produces = "application/json")
public class VagaCandidatoController {
    @Autowired
    private VagaCandidatoService vagaCandidatoService;
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping("/listar")
    public ModelAndView listar(){
        List<VagaSaida> vagaSaidaList = vagaCandidatoService.listarVagasAtivas();

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());

        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vaga",vagaSaidaList);
        mv.addObject("vagaFiltro",vagaSaida);
        mv.addObject("filtro", new VagaSaida());

        return mv;
    }

    @GetMapping("/selecionar") //chamará o endpoint /filtrar
    public ModelAndView buscarCandidato(){
        return new ModelAndView("/candidato/vaga/selecionar");
    }

    @GetMapping("/filtrar")
    public ModelAndView filtrar(@RequestParam(value = "cidade", required = false) String cidade,
                                @RequestParam(value = "formacao", required = false) String formacao,
                                @RequestParam(value = "categoria", required = false) CategoriaEnum categoria) {

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setCategoriaFiltro(String.valueOf(categoria));
        vagaSaida.setFormacao(formacao);
        vagaSaida.setCidade(cidade);


        VagaEntity vagaEntity = new VagaEntity();
        vagaEntity.setCategoria(categoria);
        vagaEntity.setFormacao(formacao);
        vagaEntity.setCidade(cidade);
        vagaEntity.setStatus("ATIVA");

        List<VagaEntity> listaEntity = vagaCandidatoService.filtrar(vagaEntity);

        List<VagaSaida> listSaida = VagaMapper.INSTANCE.mapToSaidaList(listaEntity);

        int numeroVagasEncontradas = listSaida.size();
        vagaSaida.setNumeroVagasEncontradas(numeroVagasEncontradas);
        
        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");

        mv.addObject("vaga",listSaida);
        mv.addObject("vagaFiltro",vagaSaida);
        mv.addObject("filtro", vagaSaida);

        return mv;
    }

    @GetMapping("/inscrever/{idVaga}")
    public String inscrever(@PathVariable Long idVaga, HttpServletRequest request, Model model) throws Exception {
        int nivelCadastro = candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request));

        if (nivelCadastro < 3) {
            VagaSaida vagaSaida = vagaCandidatoService.buscarVaga(idVaga);

            vagaSaida = vagaCandidatoService.validarInscricao(vagaSaida, CandidatoSessao.getId(request));

            model.addAttribute("vaga",vagaSaida);
            model.addAttribute("message", "Você precisar completar todo seu cadastro antes de se inscrever nesse vaga.");

            return "/candidato/vaga/vagaCompleta";
        }

        vagaCandidatoService.inscrever(CandidatoSessao.getId(request),idVaga);

        return "redirect:/candidato/vaga/listar";
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id,HttpServletRequest request) throws Exception {
        VagaSaida vagaSaida = vagaCandidatoService.buscarVaga(id);

        vagaSaida = vagaCandidatoService.validarInscricao(vagaSaida, CandidatoSessao.getId(request));

        ModelAndView mv = new ModelAndView("/candidato/vaga/vagaCompleta");
        mv.addObject("vaga",vagaSaida);

        return mv;
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome (@RequestParam String nome) throws Exception {
        List<VagaSaida> vagaSaidaList = vagaCandidatoService.buscarPorNome(nome);

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());

        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vagaFiltro",vagaSaida);
        mv.addObject("vaga",vagaSaidaList);
        mv.addObject("filtro", vagaSaida);

        return mv;
    }
}
