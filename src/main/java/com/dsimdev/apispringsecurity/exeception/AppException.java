package com.dsimdev.apispringsecurity.exeception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus estado;

    private String mensaje;

    public AppException() {
    }

    public AppException(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
