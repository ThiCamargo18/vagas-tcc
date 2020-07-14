package com.example.mac.endpoints.candidato;

import com.example.mac.cliente.model.ClienteSessao;
import com.example.mac.dadosPessoais.model.DadosPessoaisEntity;
import com.example.mac.dadosPessoais.model.DadosPessoaisSaida;
import com.example.mac.enums.CategoriaEnum;
import com.example.mac.vaga.model.VagaSaida;
import com.example.mac.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
        List<VagaSaida> saida = vagaService.listar();
        ModelAndView mv = new ModelAndView("/candidato/vaga/listar");
        mv.addObject("vaga",saida);
        return mv;
    }

    @GetMapping("/selecionar") //chamará o endpoint /filtrar
    public ModelAndView buscarCandidato(){
        return new ModelAndView("/candidato/vaga/selecionar");
    }

    @GetMapping("/filtrar")
    public ModelAndView filtrar(@RequestParam(value ="experiencia" , required = false) String experiencia,
                                @RequestParam(value = "formacao", required = false) String formacao,
                                @RequestParam(value = "endereco", required = false) String endereco,
                                @RequestParam(value = "categoria", required = false) CategoriaEnum categoria){

        DadosPessoaisEntity entity = new DadosPessoaisEntity();
        entity.setExperiencia(experiencia);
        entity.setFormacao(formacao);
        entity.setCategoria(categoria);
        entity.setCidade(endereco);

//        List<DadosPessoaisSaida> listaSaida = dadosPessoaisService.filtrar(entity);

        ModelAndView mv = new ModelAndView("/admin/candidato/perfil");
//        mv.addObject("cliente",listaSaida);

        return mv;
    }

    @GetMapping("/inscrever/{idVaga}")
    public VagaSaida inscrever(@PathVariable Long idVaga, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ClienteSessao clienteSessao = (ClienteSessao) session.getAttribute("usuarioLogado");

        if(clienteSessao.getPrimeiroAcesso().equals(true)){
            throw new Exception("Você deve realizar seu cadastro completo antes de se candidatar a uma vaga!");
        }

        VagaSaida vagaSaida = vagaService.inscrever(clienteSessao.getId(),idVaga);

        return vagaSaida;
    }

    @GetMapping("/buscar/{id}")
    public String buscar(@PathVariable Long id){
        return "buscado";
    }
}
