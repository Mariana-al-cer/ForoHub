package com.aluracursos.ForoHub.domain.usuario;

import com.aluracursos.ForoHub.domain.usuario.DatosDeRegistroUsuario;
import com.aluracursos.ForoHub.domain.usuario.Usuario;
import com.aluracursos.ForoHub.domain.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicio() {
        this.passwordEncoder = new BCryptPasswordEncoder(); // Puedes inyectar esto si prefieres
    }

    public void registrarUsuario(DatosDeRegistroUsuario datos) {
        // Aquí podrías agregar validaciones adicionales si es necesario
        String claveOculta = passwordEncoder.encode(datos.password()); // Encriptar la contraseña
        Usuario nuevoUsuario = new Usuario(datos, claveOculta);
        usuarioRepositorio.save(nuevoUsuario);
    }
}
