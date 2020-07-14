package com.example.mac.endpoints.admin;

import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.dadosPessoais.service.DadosPessoaisService;
import com.example.mac.enums.CategoriaEnum;
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
    DadosPessoaisService dadosPessoaisService;


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
    public ModelAndView filtrar(@RequestParam(value ="experiencia" , required = false) String experiencia,
                                            @RequestParam(value = "formacao", required = false) String formacao,
                                            @RequestParam(value = "endereco", required = false) String endereco,
                                            @RequestParam(value = "categoria", required = false) CategoriaEnum categoria){

        DadosPessoaisEntity entity = new DadosPessoaisEntity();
        entity.setExperiencia(experiencia);
        entity.setFormacao(formacao);
        entity.setCategoria(categoria);
        entity.setCidade(endereco);

        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        ModelAndView mv = new ModelAndView("/admin/candidato/perfil");
        mv.addObject("cliente",listaSaida);

        return mv;
    }
}
