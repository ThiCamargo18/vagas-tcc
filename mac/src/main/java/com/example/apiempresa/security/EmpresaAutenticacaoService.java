package com.example.apiempresa.security;

import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.empresa.model.EmpresaEntrada;

public interface EmpresaAutenticacaoService {
    EmpresaEntity findByUsername(String email);

    EmpresaEntity save(EmpresaEntrada empresaEntrada);
}
