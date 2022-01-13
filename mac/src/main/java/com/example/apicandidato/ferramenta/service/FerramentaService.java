package com.example.apicandidato.ferramenta.service;

import com.example.apicandidato.ferramenta.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FerramentaService {
    @Autowired
    private FerramentaRepository ferramentaRepository;

}
