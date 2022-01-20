package com.example.apiempresa.vaga.controller;

import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.dadosPessoais.model.DadosPessoaisSaida;
import com.example.apicandidato.exception.MyException;
import com.example.apiempresa.model.EmpresaSessao;
import com.example.apiempresa.vaga.model.VagaEntity;
import com.example.apiempresa.vaga.model.VagaEntrada;
import com.example.apiempresa.vaga.model.VagaSaida;
import com.example.apiempresa.vaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "vaga", produces = "application/json")
@Configuration
@CrossOrigin
public class VagaController {
    @Autowired
    private VagaService vagaService;
    @Autowired
    private CargoService cargoService;

    @RequestMapping("/criar")
    public ModelAndView criar() {
        return new ModelAndView("/admin/vaga/criar");
    }

    @PostMapping("/criar")
    public String criar(@ModelAttribute VagaEntrada entrada, HttpServletRequest request, RedirectAttributes redirectAttributes) throws MyException {
        VagaEntity vagaEntity = vagaService.criar(entrada, EmpresaSessao.getId(request));

        redirectAttributes.addAttribute("idVaga", vagaEntity.getId());

        return "redirect:/vaga/criar/criarAdiconal";
    }

    @GetMapping("/criar/criarAdiconal")
    public ModelAndView criarCadastroCargo(@RequestParam(value = "idCargo", required = false) Long idCargo, @RequestParam(value = "idVaga") Long idVaga) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/admin/vaga/criarAdicional");

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));
        modelAndView.addObject("vaga", vagaService.buscarVaga(idVaga));

        return modelAndView;
    }

    @PostMapping("/criar/criarAdiconal")
    public String salvarCadastroCargo(@ModelAttribute VagaEntrada vagaEntrada, @RequestParam(value = "idVaga") Long idVaga, HttpServletRequest request) throws Exception {
        vagaService.validarSePertenceEmpresa(idVaga, EmpresaSessao.getId(request));

        vagaService.atualizarInfomacoesAdicionais(vagaEntrada, idVaga);

        return "redirect:/vaga/listar";
    }

    @GetMapping("/listar")
    public ModelAndView listar(HttpServletRequest request) {
        VagaSaida vagaSaida = new VagaSaida();

        List<VagaSaida> vagaSaidaList = vagaService.listar(EmpresaSessao.getId(request));

        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());

        ModelAndView mv = new ModelAndView("/admin/vaga/listar");
        mv.addObject("vagaFiltro", vagaSaida);
        mv.addObject("vaga", vagaSaidaList);

        return mv;
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        VagaSaida vagaSaida = vagaService.buscarVaga(id);

        ModelAndView mv = new ModelAndView("/admin/vaga/buscar");

        mv.addObject("vaga", vagaSaida);
        mv.addObject("listaHabilidades", vagaSaida.getDescricaoHabilidades());
        mv.addObject("beneficios", vagaSaida.getBeneficios());
        return mv;
    }

    @GetMapping("/inscritos/{id}")
    public ModelAndView inscritos(@PathVariable Long id) throws Exception {
        List<DadosPessoaisSaida> dadosPessoaisSaida = vagaService.buscarInscritosPorVaga(id);
        VagaSaida vagaSaida = new VagaSaida();
        vagaSaida.setId(id);

        ModelAndView mv = new ModelAndView("/admin/vaga/filtroInscritos");
        mv.addObject("cliente", dadosPessoaisSaida);
        mv.addObject("vaga", vagaSaida);
        return mv;
    }

    @GetMapping("/deletar")
    public String deletar(@RequestParam Long vaga) throws Exception {
        String resultado = vagaService.deletar(vaga);

        if (resultado.equals("concluido")) {
            return "redirect:/vaga/listar";
        } else {
            throw new Exception("Não foi possivel excluir a vaga, tente novamente");
        }
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome(@RequestParam String nome) throws Exception {
        VagaSaida vagaSaida = new VagaSaida();

        ModelAndView mv = new ModelAndView("/admin/vaga/buscarPorNome");
        List<VagaSaida> vagaSaidaList = vagaService.buscarPorNome(nome);

        vagaSaida.setNumeroVagasEncontradas(vagaSaidaList.size());
        mv.addObject("vagaFiltro", vagaSaida);
        mv.addObject("vaga", vagaSaidaList);
        return mv;
    }

    @GetMapping("/status/{id}")
    public String atualizarStatus(@PathVariable Long id) throws Exception {
        vagaService.atualizarStatus(id);

        return "redirect:/vaga/listar";
    }

//    @RequestMapping("/atualizar/{id}")
//    public ModelAndView atualizar(@PathVariable Long id) throws Exception {
//        VagaSaida vagaSaida = vagaService.buscarVaga(id);
//        ModelAndView mv = new ModelAndView("/admin/vaga/atualizar");
//        mv.addObject("vaga", vagaSaida);
//        return mv;
//    }
//
//    @PostMapping("/atualizar")
//    public HttpStatus atualizar(@RequestBody VagaEntrada vagaEntrada) { //Id ja vem do front
//        vagaService.atualizar(vagaEntrada);
//
//        return HttpStatus.OK;
//    }
}
