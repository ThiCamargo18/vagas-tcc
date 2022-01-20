package com.example.apicandidato.security;

import com.example.apicandidato.candidato.model.CandidatoEntity;
import com.example.apicandidato.candidato.repository.CandidatoRepository;
import com.example.apiempresa.empresa.model.EmpresaEntity;
import com.example.apiempresa.empresa.repository.EmpresaRepository;
import com.example.security.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CandidatoDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CandidatoRepository userRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        if (username.contains("@")){
            CandidatoEntity user = userRepository.findByEmail(username);
            if (user == null) throw new UsernameNotFoundException(username);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (RoleEntity role : user.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), grantedAuthorities);
        } else {
            EmpresaEntity empresaEntityOptional = empresaRepository.findByLogin(username);
            if (empresaEntityOptional == null) throw new UsernameNotFoundException(username);

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (RoleEntity role : empresaEntityOptional.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(empresaEntityOptional.getLogin(), empresaEntityOptional.getSenha(), grantedAuthorities);
        }
    }
}
