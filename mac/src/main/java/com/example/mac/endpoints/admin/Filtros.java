package com.example.mac.endpoints.admin;

import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.enums.CategoriaEnum;
import com.example.mac.habilidades.model.HabilidadesEntity;
import com.example.mac.habilidades.model.HabilidadesSaida;
import com.example.mac.habilidades.service.HabilidadesService;
import com.example.mac.vaga.model.VagaEntity;
import com.example.mac.vaga.repository.VagaRepository;
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
    DadosPessoaisService dadosPessoaisService;
    @Autowired
    HabilidadesService habilidadesService;
    @Autowired
    VagaRepository vagaRepository;

    @GetMapping("/selecionar") //chamará o endpoint /Buscar
    public ModelAndView buscarCandidato(){
        return new ModelAndView("/admin/candidato/selecionar");
    }

    @GetMapping("/buscarNome") //chamará o endpoint BuscarCandidato
    public ModelAndView selecionarClienteParaBuscar(){
        return new ModelAndView("/admin/candidato/buscarNome");
    }

    @GetMapping("/buscarCandidato")
    public ModelAndView buscarCandidatoPorNomeOuCpf(@RequestParam String nome){
        List<DadosPessoaisEntity> dadosPessoaisEntity = dadosPessoaisService.buscarPorNome(nome);
        ModelAndView mv = new ModelAndView("/admin/candidato/exibirCandidato");
        mv.addObject("cliente",dadosPessoaisEntity);

        return mv;
    }

    @GetMapping("/buscar")
    public ModelAndView filtrar(@RequestParam(value = "formacao", required = false) String formacao,
                                            @RequestParam(value = "endereco", required = false) String endereco,
                                            @RequestParam(value = "categoria", required = false) CategoriaEnum categoria){

        DadosPessoaisEntity entity = new DadosPessoaisEntity();
        entity.setFormacao(formacao);
        entity.setCategoria(categoria);
        entity.setCidade(endereco);

        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        ModelAndView mv = new ModelAndView("/admin/candidato/perfil");
        mv.addObject("cliente",listaSaida);

        return mv;
    }

    @GetMapping("/palavraChave/{id}")
    public ModelAndView filtrarPorPalavraChaveResumoProfissional(@RequestParam(value = "chave1",required = false) String chave1,
                                                                 @PathVariable(value = "id",required = true) Long idVaga) throws Exception {
        String parametro=null;

        if(chave1 != ""){
            parametro = chave1;
        }

        Optional<VagaEntity> vagaEntityOptional = vagaRepository.findById(idVaga);

        if(!vagaEntityOptional.isPresent()){
            throw new Exception("Vaga não encontrada, busque novamente!");
        }

        List<HabilidadesSaida> cliente = habilidadesService.filtrarPorResumoProfissional(idVaga,parametro,vagaEntityOptional.get());

        if(cliente == null){
            HabilidadesSaida clienteNull = new HabilidadesSaida();
            clienteNull.setNomeUsuario("Nenhum candidato encontrado");
            ModelAndView mv = new ModelAndView("/admin/candidato/buscarPorPalavraChave");
            mv.addObject("cliente",clienteNull);
            mv.addObject("vaga",vagaEntityOptional.get());
            return mv;
        }

        ModelAndView mv = new ModelAndView("/admin/candidato/buscarPorPalavraChave");
        mv.addObject("cliente",cliente);
        mv.addObject("vaga",vagaEntityOptional.get());
        return mv;
    }
}
