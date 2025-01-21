package com.aluracursos.ForoHub.controller;

import com.aluracursos.ForoHub.domain.usuario.DatosDeRegistroUsuario;
import com.aluracursos.ForoHub.domain.usuario.Usuario;
import com.aluracursos.ForoHub.domain.usuario.UsuarioRepositorio;
import com.aluracursos.ForoHub.domain.usuario.UsuarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @RequestMapping("/registro")
    private ResponseEntity registrarTopico(@RequestBody @Valid DatosDeRegistroUsuario datosDeRegistroUsuario){

        String claveOculta = passwordEncoder.encode(datosDeRegistroUsuario.password());
        usuarioRepositorio.save(new Usuario(datosDeRegistroUsuario, claveOculta ));
        return ResponseEntity.ok("Su registro fue realizado exitosamente");

    }

    @PostMapping
    @RequestMapping("/registrar")
    public void registrarUsuario(@RequestBody DatosDeRegistroUsuario datos) {
        usuarioServicio.registrarUsuario(datos);
    }
}
