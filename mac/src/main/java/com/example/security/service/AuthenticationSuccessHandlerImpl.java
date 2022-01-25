package com.example.security.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.security.CandidatoAutenticacaoServiceImpl;
import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.model.EmpresaSessao;
import com.example.apiempresa.security.EmpresaAutenticacaoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private HttpSession session;
    @Autowired
    private CandidatoAutenticacaoServiceImpl candidatoAutenticacaoService;
    @Autowired
    private EmpresaAutenticacaoServiceImpl empresaAutenticacaoService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String userName = ((User) authentication.getPrincipal()).getUsername();

        if (userName.contains("@")) {
            CandidatoEntity candidatoEntity = candidatoAutenticacaoService.findByEmail(userName);

            CandidatoSessao candidatoSessao = new CandidatoSessao();
            candidatoSessao.setId(candidatoEntity.getId());
            candidatoSessao.setNome(candidatoEntity.getNome());

            session.setAttribute("usuarioLogado", candidatoSessao);

            response.sendRedirect("/");
        } else {
            EmpresaEntity empresaEntity = empresaAutenticacaoService.findByUsername(userName);

            EmpresaSessao empresaSessao = new EmpresaSessao();
            empresaSessao.setId(empresaEntity.getId());
            empresaSessao.setNome(empresaEntity.getRazaoSocial());

            session.setAttribute("empresaLogada", empresaSessao);

            response.sendRedirect("/admin");
        }
    }

}
