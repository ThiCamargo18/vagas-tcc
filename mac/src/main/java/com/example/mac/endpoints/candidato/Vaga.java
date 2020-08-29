package com.example.mac.endpoints.candidato;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.enums.CategoriaEnum;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.vaga.mapper.VagaMapper;
import com.example.mac.vaga.model.VagaEntity;
import com.example.mac.vaga.model.VagaSaida;
import com.example.mac.vaga.service.VagaService;
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
        List<VagaSaida> vagaSaidaList = vagaService.listar();
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
        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.getPrimeiroAcesso().equals(true)){
            throw new Exception("Você deve realizar seu cadastro completo antes de se candidatar a uma vaga!");
        }

        VagaSaida vagaSaida = vagaService.inscrever(clienteSessao.getId(),idVaga);

        response.sendRedirect("http://localhost:8088/inicio");
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        VagaSaida vagaSaida = vagaService.buscarVaga(id);
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
