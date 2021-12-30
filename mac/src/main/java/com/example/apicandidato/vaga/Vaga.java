package com.example.apicandidato.vaga;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apiempresa.vaga.mapper.VagaMapper;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "candidato/vaga", produces = "application/json")
public class Vaga {
    @Autowired
    private VagaService vagaService;
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping("/listar")
    public ModelAndView listar(){
        List<VagaSaida> vagaSaidaList = vagaService.listarVagasAtivas();

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());

        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vaga",vagaSaidaList);
        mv.addObject("vagaFiltro",vagaSaida);
        return mv;
    }

    @GetMapping("/selecionar") //chamará o endpoint /filtrar
    public ModelAndView buscarCandidato(){
        return new ModelAndView("/candidato/vaga/selecionar");
    }

    @GetMapping("/filtrar")
    public ModelAndView filtrar(
                                @RequestParam(value = "formacao", required = false) String formacao,
                                @RequestParam(value = "categoria", required = false) CategoriaEnum categoria){
        VagaSaida vagaSaida = new VagaSaida();
        VagaEntity vagaEntity = new VagaEntity();
        vagaEntity.setCategoria(categoria);
        vagaEntity.setFormacao(formacao);
        vagaEntity.setStatus("ATIVA");

        List<VagaEntity> listaEntity = vagaService.filtrar(vagaEntity);
        List<VagaSaida> listSaida = VagaMapper.INSTANCE.mapToSaidaList(listaEntity);

        int numeroVagasEncontradas = listSaida.size();
        vagaSaida.setNumeroVagasEncontradas(numeroVagasEncontradas);
        
        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vaga",listSaida);
        mv.addObject("vagaFiltro",vagaSaida);
        return mv;
    }

    @GetMapping("/inscrever/{idVaga}")
    public String inscrever(@PathVariable Long idVaga, HttpServletRequest request) throws Exception {
        boolean primeiroAcesso = candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request));

        if (!primeiroAcesso)
            return "redirect:cadastro/gerenciar";

        vagaService.inscrever(CandidatoSessao.getId(request),idVaga);

        return "redirect:/";
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id,HttpServletRequest request) throws Exception {
        VagaSaida vagaSaida = vagaService.buscarVaga(id);

        vagaSaida = vagaService.validarInscricao(vagaSaida, CandidatoSessao.getId(request));

        List<String> listaHabilidades = vagaSaida.getDescricaoHabilidades();
        List<String> beneficios = vagaSaida.getBeneficios();

        ModelAndView mv = new ModelAndView("/candidato/vaga/vagaCompleta");
        mv.addObject("vaga",vagaSaida);
        mv.addObject("listaHabilidades",listaHabilidades);
        mv.addObject("beneficios",beneficios);
        return mv;
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome (@RequestParam String nome) throws Exception {
        List<VagaSaida> vagaSaidaList = vagaService.buscarPorNome(nome);

        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());

        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vagaFiltro",vagaSaida);
        mv.addObject("vaga",vagaSaidaList);
        return mv;
    }
}
