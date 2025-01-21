package com.aluracursos.ForoHub.controller;


import com.aluracursos.ForoHub.domain.usuario.DatosDeAutenticacionUsuario;
import com.aluracursos.ForoHub.domain.usuario.Usuario;
import com.aluracursos.ForoHub.infra.security.DatosJWTToken;
import com.aluracursos.ForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosDeAutenticacionUsuario datosDeAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosDeAutenticacionUsuario.username(),
                datosDeAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
