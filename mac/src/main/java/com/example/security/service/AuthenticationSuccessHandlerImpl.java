package com.example.security.service;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.model.CandidatoSessao;
import com.example.apicandidato.security.CandidatoAutenticacaoServiceImpl;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String userName = ((User) authentication.getPrincipal()).getUsername();

        CandidatoEntity candidatoEntity = candidatoAutenticacaoService.findByEmail(userName);
        CandidatoSessao candidatoSessao = new CandidatoSessao();
        candidatoSessao.setId(candidatoEntity.getId());
        candidatoSessao.setNome(candidatoEntity.getNome());

        session.setAttribute("usuarioLogado", candidatoSessao);

        response.sendRedirect("/");
    }

}
