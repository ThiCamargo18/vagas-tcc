package com.example.apicandidato.security;

import com.example.apicandidato.candidato.model.CandidatoEntrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CandidatoValidacao implements Validator {
    @Autowired
    private CandidatoAutenticacaoService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return CandidatoEntrada.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CandidatoEntrada user = (CandidatoEntrada) object;

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
