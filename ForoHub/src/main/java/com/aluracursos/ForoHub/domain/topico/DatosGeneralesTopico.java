package com.aluracursos.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosGeneralesTopico(Long id,
                                   String titulo,
                                   String mensaje,
                                   LocalDateTime fechaPublicacion,
                                   boolean status,
                                   String autor,
                                   String curso) {
}
