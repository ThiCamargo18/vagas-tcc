package com.example.apicandidato.autenticacao;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.enums.CategoriaEnum;
import com.example.apicandidato.vaga.mapper.VagaMapper;
import com.example.apicandidato.vaga.model.VagaEntity;
import com.example.apicandidato.vaga.model.VagaSaida;
import com.example.apicandidato.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(path = "candidato/vaga", produces = "application/json")
@Configuration
@CrossOrigin
public class Vaga {
    @Autowired
    VagaService vagaService;

    @GetMapping("/listar")
    public ModelAndView listar(HttpServletRequest request){
        List<VagaSaida> vagaSaidaList = vagaService.listarVagasAtivas();
        VagaSaida vagaSaida = new VagaSaida();
        int numeroVagasEncontradas = vagaSaidaList.size();
        vagaSaida.setNumeroVagasEncontradas(numeroVagasEncontradas);

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
    public void inscrever(@PathVariable Long idVaga, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        if(candidatoSessao.getPrimeiroAcesso().equals(true)){
            throw new Exception("Você deve realizar seu cadastro completo antes de se candidatar a uma vaga!");
        }

        VagaSaida vagaSaida = vagaService.inscrever(candidatoSessao.getId(),idVaga);

        response.sendRedirect("http://localhost:8088/inicio");
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        CandidatoSessao candidatoSessao = (CandidatoSessao) session.getAttribute("usuarioLogado");

        VagaSaida vagaSaida = vagaService.buscarVaga(id);
        vagaSaida = vagaService.validarInscricao(vagaSaida, candidatoSessao.getId());
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
        int numeroVagasEncontradas = vagaSaidaList.size();
        vagaSaida.setNumeroVagasEncontradas(numeroVagasEncontradas);

        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vagaFiltro",vagaSaida);
        mv.addObject("vaga",vagaSaidaList);
        return mv;
    }
}
