package com.aluracursos.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDeTodosLosTopicos(Long id,
                                     String titulo,
                                     String mensaje,
                                     LocalDateTime fechaPublicacion,
                                     Boolean status,
                                     String usuario,
                                     String curso) {
    public DatosDeTodosLosTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaPublicacion(), topico.getStatus(), topico.getUsuario().getNombre(), topico.getCurso());
    }
}
