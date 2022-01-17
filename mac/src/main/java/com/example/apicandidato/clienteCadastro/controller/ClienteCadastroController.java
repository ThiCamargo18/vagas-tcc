package com.example.apicandidato.clienteCadastro.controller;

import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.candidato.service.CandidatoService;
import com.example.apicandidato.cargo.service.CargoService;
import com.example.apicandidato.clienteCadastro.model.CadastroAdicionalEntrada;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroEntrada;
import com.example.apicandidato.clienteCadastro.model.ClienteCadastroSaida;
import com.example.apicandidato.clienteCadastro.service.ClienteCadastroService;
import com.example.apicandidato.experiencia.service.ExperienciaService;
import com.example.apicandidato.ferramenta.service.FerramentaService;
import com.example.apicandidato.framework.service.FrameworkService;
import com.example.apicandidato.habilidades.service.HabilidadesService;
import com.example.apicandidato.projetos.service.ProjetoService;
import com.example.apicandidato.tecnologia.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "cadastro", produces = "application/json")
public class ClienteCadastroController {
    @Autowired
    private ClienteCadastroService clienteCadastroService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private ExperienciaService experienciaService;
    @Autowired
    private HabilidadesService habilidadesService;
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private TecnologiaService tecnologiaService;
    @Autowired
    private FrameworkService frameworkService;
    @Autowired
    private FerramentaService ferramentaService;

    @RequestMapping(value = "/criar")
    public ModelAndView criar() {
        return new ModelAndView("/candidato/cadastro/cadastroCompleto");
    }

    @PostMapping("/criar")
    public String criar(@Valid ClienteCadastroEntrada entrada, HttpServletRequest request) throws Exception {
        clienteCadastroService.criar(entrada, CandidatoSessao.getId(request));

        return "redirect:gerenciar";
    }

    @RequestMapping("/criar/cadastroAdicional")
    public String criarCadastroAdicional(HttpServletRequest request, Model model) {
        if (candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request)) != 1)
            return "redirect:/cadastro/gerenciar";

        model.addAttribute("experiencia", experienciaService.buscarPorIdCliente(CandidatoSessao.getId(request)));
        model.addAttribute("habilidade", habilidadesService.buscarPorIdCLiente(CandidatoSessao.getId(request)));
        model.addAttribute("projeto", projetoService.buscarPorIdCLiente(CandidatoSessao.getId(request)));

        return "/candidato/cadastro/cadastroAdicional";
    }

    @PostMapping("/criar/cadastroAdicional")
    public String salvarCadastroAdicional(HttpServletRequest request) {
        if (candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request)) != 1)
            return "redirect:gerenciar";

        candidatoService.atualizarCadastro(CandidatoSessao.getId(request), 2);

        return "redirect:/cadastro/gerenciar";
    }

    @GetMapping("/criar/cadastroCargo")
    public ModelAndView criarCadastroCargo(@RequestParam(value = "idCargo", required = false) Long idCargo) {
        ModelAndView modelAndView = new ModelAndView("/candidato/cadastro/cadastroCargo");

        modelAndView.addObject("cargo", cargoService.buscarEMapear(idCargo));

        return modelAndView;
    }

    @PostMapping("/criar/cadastroCargo")
    public String salvarCadastroCargo(@ModelAttribute CadastroAdicionalEntrada cadastroAdicionalEntrada, HttpServletRequest request){
        tecnologiaService.atualizar(cadastroAdicionalEntrada.getTecnologia(), CandidatoSessao.getId(request));
        frameworkService.atualizar(cadastroAdicionalEntrada.getFramework(), CandidatoSessao.getId(request));
        ferramentaService.atualizar(cadastroAdicionalEntrada.getFerramenta(), CandidatoSessao.getId(request));

        return "redirect:/";
    }

    @RequestMapping("/gerenciar")
    public String gerenciarCadastro(HttpServletRequest request) throws Exception {
        int nivelCadastroRealizado = candidatoService.cadastroBasicoRealizado(CandidatoSessao.getId(request));

        if (nivelCadastroRealizado == 0) return "redirect:criar";

        if (nivelCadastroRealizado == 1) return "redirect:criar/cadastroAdicional";

        if (nivelCadastroRealizado == 2) return "redirect:criar/cadastroCargo";

        return "redirect:procurar";
    }

    @GetMapping("/buscar/{id}")
    public ModelAndView buscar(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("/admin/candidato/cadastroCompleto");

        ClienteCadastroSaida saida = clienteCadastroService.buscar(id);

        mv.addObject("cliente", saida.getCliente());
        mv.addObject("dadosPessoais", saida.getDadosPessoais());
        mv.addObject("dadosAdicionais", saida.getDadosAdicionais());
        mv.addObject("registro", saida.getRegistro());
        mv.addObject("habilidades", saida.getHabilidades());
        mv.addObject("experiencia", saida.getExperiencia());

        return mv;
    }

    @GetMapping("/procurar")
    public ModelAndView procurar(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/candidato/cadastro/buscar");

        ClienteCadastroSaida saida = clienteCadastroService.buscar(CandidatoSessao.getId(request));

        mv.addObject("cliente", saida.getCliente());
        mv.addObject("dadosPessoais", saida.getDadosPessoais());
        mv.addObject("dadosAdicionais", saida.getDadosAdicionais());
        mv.addObject("registro", saida.getRegistro());
        mv.addObject("habilidades", saida.getHabilidades());
        mv.addObject("experiencia", saida.getExperiencia());

        return mv;
    }
}
