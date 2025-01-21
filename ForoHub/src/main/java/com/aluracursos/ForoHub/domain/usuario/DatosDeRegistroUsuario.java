package com.aluracursos.ForoHub.domain.usuario;

public record DatosDeRegistroUsuario(
        String nombre,
        String username,
        String email,
        String password
) {
}
