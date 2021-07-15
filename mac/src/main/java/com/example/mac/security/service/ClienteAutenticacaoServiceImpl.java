package com.example.mac.security.service;

import com.example.mac.cliente.mapper.ClienteMapper;
import com.example.mac.cliente.model.ClienteEntity;
import com.example.mac.cliente.model.ClienteEntrada;
import com.example.mac.cliente.repository.ClienteRepository;
import com.example.mac.security.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ClienteAutenticacaoServiceImpl implements ClienteAutenticacaoService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        ClienteEntity clienteEntity = clienteRepository.findByEmail(email);
//        if (clienteEntity == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(clienteEntity.getEmail(),
//                clienteEntity.getSenha(),
//                mapRolesToAuthorities(clienteEntity.getRoles()));
//    }

    @Override
    public ClienteEntity findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public ClienteEntity save(ClienteEntrada clienteEntrada) {
        ClienteEntity clienteEntity = ClienteMapper.INSTANCE.mapToEntity(clienteEntrada);
        clienteEntity.setSenha(passwordEncoder.encode(clienteEntrada.getSenha()));
        clienteEntity.setPrimeiroAcesso(true);
        clienteEntity.setSituacao("CONCORRENDO");
        clienteEntity.setRoles(clienteEntrada.getRoles());

        return clienteRepository.save(clienteEntity);
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles){
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
}
