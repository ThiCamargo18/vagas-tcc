package com.example.apicandidato.tecnologia.service;

import com.example.apicandidato.tecnologia.repository.TecnologiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaService {
    @Autowired
    private TecnologiaRepository tecnologiaRepository;


}
