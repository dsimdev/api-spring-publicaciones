package com.dsimdev.apispringsecurity.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long SerialVersionUID = 1L;

    private String nombreDelRecurso;
    private String nombreDelCampo;
    private long valorDeCampo;

    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, long valorDeCampo) {
        super(String.format("%s no encontrada con: %s='%s'", nombreDelRecurso, nombreDelCampo, valorDeCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDeCampo = valorDeCampo;
    }

    public String getNombreDelRecurso() {
        return nombreDelRecurso;
    }

    public void setNombreDelRecurso(String nombreDelRecurso) {
        this.nombreDelRecurso = nombreDelRecurso;
    }

    public String getNombreDelCampo() {
        return nombreDelCampo;
    }

    public void setNombreDelCampo(String nombreDelCampo) {
        this.nombreDelCampo = nombreDelCampo;
    }

    public long getValorDeCampo() {
        return valorDeCampo;
    }

    public void setValorDeCampo(long valorDeCampo) {
        this.valorDeCampo = valorDeCampo;
    }
}
