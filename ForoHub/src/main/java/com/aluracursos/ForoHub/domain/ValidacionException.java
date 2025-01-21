package com.aluracursos.ForoHub.domain;

public class ValidacionException extends RuntimeException {

    public ValidacionException(String mensaje) {
        super(mensaje); //envia el mensaje a la clase de la cual hereda (RunTimeException)
    }
}
