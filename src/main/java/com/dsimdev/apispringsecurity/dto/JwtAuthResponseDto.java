package com.dsimdev.apispringsecurity.dto;
public class JwtAuthResponseDto {

    private String tokenDeAcceso;
    private String tipoDeToken = "Bearer";

    public JwtAuthResponseDto() {
    }

    public JwtAuthResponseDto(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public JwtAuthResponseDto(String tokenDeAcceso, String tipoDeToken) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }


    public String getTokenDeAcceso() {
        return tokenDeAcceso;
    }

    public void setTokenDeAcceso(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }

    public String getTipoDeToken() {
        return tipoDeToken;
    }

    public void setTipoDeToken(String tipoDeToken) {
        this.tipoDeToken = tipoDeToken;
    }
}
