package com.example.apiempresa.entrevista.service;

import com.example.apiempresa.entrevista.mapper.EntrevistaMapper;
import com.example.apiempresa.entrevista.model.EntrevistaEntity;
import com.example.apiempresa.entrevista.model.EntrevistaEntrada;
import com.example.apiempresa.entrevista.model.EntrevistaSaida;
import com.example.apiempresa.entrevista.repository.EntrevistaRepository;
import com.example.apicandidato.enums.Presenca;
import com.example.apicandidato.mail.MailService;
import com.example.apicandidato.mail.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EntrevistaService {
    @Autowired
    EntrevistaRepository entrevistaRepository;
    @Autowired
    MailService mailService;


    public EntrevistaSaida criar(EntrevistaEntrada entrevistaEntrada) throws Exception {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);
        entrevistaEntity.setPresenca(Presenca.AGENDADO);
        entrevistaEntity.setData(dataParaDDMMAAA(entrevistaEntity.getData()));

        entrevistaRepository.save(entrevistaEntity);

        Mensagem mensagem = new Mensagem(entrevistaEntrada.getMensagemEmail().getRemetente(),
                                        entrevistaEntrada.getMensagemEmail().getDestinatarios(),
                                        entrevistaEntrada.getMensagemEmail().getAssunto(),
                                        entrevistaEntrada.getMensagemEmail().getCorpo());

        mailService.enviar(mensagem);

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public List<EntrevistaSaida> listar() {
        List<EntrevistaEntity> entrevistas = entrevistaRepository.findAll();

        return EntrevistaMapper.INSTANCE.mapToSaidaList(entrevistas);
    }

    public String deletar(Long id) {
        entrevistaRepository.deleteById(id);

        return "concluido";
    }

    public EntrevistaSaida novaEntrevistaPelaNavbar(EntrevistaEntrada entrevistaEntrada) throws Exception {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);
        entrevistaEntity.setPresenca(Presenca.AGENDADO);
        entrevistaEntity.setData(dataParaDDMMAAA(entrevistaEntity.getData()));

        entrevistaRepository.save(entrevistaEntity);

        Mensagem mensagem = new Mensagem(entrevistaEntrada.getMensagemEmail().getRemetente(),
                entrevistaEntrada.getMensagemEmail().getDestinatarios(),
                entrevistaEntrada.getMensagemEmail().getAssunto(),
                entrevistaEntrada.getMensagemEmail().getCorpo());

        if(!mensagem.getRemetente().equals("") && !mensagem.getAssunto().equals("") && !mensagem.getCorpo().equals("")){
            mailService.enviar(mensagem);
        }


        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public String dataParaDDMMAAA(String data){
        if(data.equals("")) return data;

        String validador1 = String.valueOf(data.charAt(2));
        String validador2 = String.valueOf(data.charAt(4));

        if(validador1.equals("/") || validador2.equals("/")) return data; //Já está formatado, se entrar no localDate quebra

        LocalDate localDate = LocalDate.parse(data);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        data = dateTimeFormatter.format(localDate);

        return data;
    }

    public EntrevistaSaida atualizar(long id, EntrevistaEntrada entrevistaEntrada) throws Exception {
        EntrevistaEntity entrevistaEntity = EntrevistaMapper.INSTANCE.mapToEntity(entrevistaEntrada);
        entrevistaEntity.setId(id);
        entrevistaEntity.setData(dataParaDDMMAAA(entrevistaEntity.getData()));

        entrevistaRepository.save(entrevistaEntity);

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }

    public EntrevistaSaida buscar(long id) throws Exception {
        Optional<EntrevistaEntity> entrevistaEntity = entrevistaRepository.findById(id);

        if(!entrevistaEntity.isPresent()){
            throw new Exception("Entrevista não encontrada");
        }

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity.get());
    }

    public EntrevistaSaida buscarPorIdCandidato(Long idCliente) throws Exception {
        EntrevistaEntity entrevistaEntity = entrevistaRepository.findByIdCliente(idCliente);

        if(entrevistaEntity == null){
            throw new Exception("No momento ainda não possui nenhuma entrevista agendada no seu nome! " +
                    "" +
                    "Você receberá um e-mail informando se for selecionado!");
        }

        return EntrevistaMapper.INSTANCE.mapToSaida(entrevistaEntity);
    }
}
