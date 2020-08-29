package com.example.mac.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "mail", produces = "application/json")
@Configuration
@CrossOrigin
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("/send")
    public String enviarEmail(@RequestBody Mensagem mensagem) throws Exception {
        mailService.enviar(mensagem);

        return "enviado";
    }
}
