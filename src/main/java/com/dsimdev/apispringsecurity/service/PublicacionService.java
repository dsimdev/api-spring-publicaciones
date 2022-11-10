package com.dsimdev.apispringsecurity.service;

import com.dsimdev.apispringsecurity.dto.PublicacionDto;
import com.dsimdev.apispringsecurity.dto.PublicacionRespuesta;

public interface PublicacionService {

    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto);

    public PublicacionRespuesta obtenerTodasLasPublicaciones(int pageNo, int pageSize, String orderBy, String sortBy);

    public PublicacionDto obtenerPublicacionById(long id);

    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, long id);

    public void eliminarPublicacion(long id);
}
