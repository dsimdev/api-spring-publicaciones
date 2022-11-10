package com.dsimdev.apispringsecurity.service;

import com.dsimdev.apispringsecurity.dto.ComentarioDto;

import java.util.List;

public interface ComentarioService {

    public ComentarioDto crearComentario(Long publicacionId, ComentarioDto comentarioDto);

    public List<ComentarioDto> obtenerComentariosPorPublicacionId(Long publicacionId);

    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId);

    public ComentarioDto actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDto comentarioRequest);
}
