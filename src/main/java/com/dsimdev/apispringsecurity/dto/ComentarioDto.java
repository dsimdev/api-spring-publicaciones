package com.dsimdev.apispringsecurity.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDto {

    private long id;
    @NotEmpty(message = "El nombre debe ser obligatorio")
    private String nombre;
    @NotEmpty(message = "El email debe ser obligatorio")
    @Email(message = "Formato incorrecto")
    private String email;
    @NotEmpty
    @Size(min=10, message = "El cuerpo del comentario debe tener al menos 10 caracteres.")
    private String mensaje;

    public ComentarioDto() {
    }

    public ComentarioDto(long id, String nombre, String email, String mensaje) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.mensaje = mensaje;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
