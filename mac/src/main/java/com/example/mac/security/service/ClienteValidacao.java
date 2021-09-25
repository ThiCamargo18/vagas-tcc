package com.example.mac.security.service;

import com.example.mac.candidato.model.CandidatoEntity;
import com.example.mac.candidato.model.CandidatoEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClienteValidacao implements Validator {
    @Autowired
    private ClienteAutenticacaoService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CandidatoEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CandidatoEntrada user = (CandidatoEntrada) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
//        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
//        if (userService.findByEmail(user.getEmail()) != null) {
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (user.getSenha().length() < 8 || user.getSenha().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//
//        if (!user.getRepetirSenha().equals(user.getSenha())) {
//            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
//        }
    }
}
