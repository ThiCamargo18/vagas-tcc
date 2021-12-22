package com.example.apicandidato.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void enviar(Mensagem mensagem) throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mensagem.getRemetente());
        simpleMailMessage.setTo(mensagem.getDestinatarios()
                .toArray(new String[mensagem.getDestinatarios().size()]));
        simpleMailMessage.setSubject(mensagem.getAssunto());
        simpleMailMessage.setText(mensagem.getCorpo());

        try{
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e){
            log.error("Não foi possivel encaminhar o e-mail!");
        }

    }

}
