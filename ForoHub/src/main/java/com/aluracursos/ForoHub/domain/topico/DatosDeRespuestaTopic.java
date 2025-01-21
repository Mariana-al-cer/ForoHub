package com.aluracursos.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDeRespuestaTopic(Long Id,
                                    String titulo,
                                    String mensaje,
                                    LocalDateTime fechaPublicacion,
                                    String nombreUsuario,
                                    String curso) {
    public DatosDeRespuestaTopic(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaPublicacion(),
                topico.getUsuario().getNombre(),
                topico.getCurso());
    }


}
