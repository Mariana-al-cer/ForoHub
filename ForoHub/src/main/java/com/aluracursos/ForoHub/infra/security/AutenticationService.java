package com.aluracursos.ForoHub.infra.security;

import com.aluracursos.ForoHub.domain.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario = usuarioRepositorio.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return usuario;
    }
}
