package com.dsimdev.apispringsecurity.service;

import com.dsimdev.apispringsecurity.dto.ComentarioDto;
import com.dsimdev.apispringsecurity.exeception.AppException;
import com.dsimdev.apispringsecurity.exeception.ResourceNotFoundException;
import com.dsimdev.apispringsecurity.entity.Comentario;
import com.dsimdev.apispringsecurity.entity.Publicacion;
import com.dsimdev.apispringsecurity.repository.ComentarioRepository;
import com.dsimdev.apispringsecurity.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDto crearComentario(Long publicacionId, ComentarioDto comentarioDto) {
        Comentario comentario = mapearEntidad(comentarioDto);
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);

        return mapearDto(nuevoComentario);
    }

    @Override
    public List<ComentarioDto> obtenerComentariosPorPublicacionId(Long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearDto(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
        Comentario comentario = buscarComentarioYPublicacion(publicacionId, comentarioId);
        return mapearDto(comentario);
    }

    @Override
    public ComentarioDto actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDto comentarioRequest) {
        Comentario comentario = buscarComentarioYPublicacion(publicacionId, comentarioId);
        comentario.setNombre(comentarioRequest.getNombre());
        comentario.setEmail(comentarioRequest.getEmail());
        comentario.setMensaje(comentarioRequest.getMensaje());

        Comentario comentarioActualizado = comentarioRepository.save(comentario);
        return mapearDto(comentarioActualizado);
    }

    private Comentario buscarComentarioYPublicacion (Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

        if(comentario.getPublicacion().getId() != publicacion.getId()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        return comentario;
    }

    private ComentarioDto mapearDto(Comentario comentario) {
        ComentarioDto comentarioDto = modelMapper.map(comentario, ComentarioDto.class);
        return comentarioDto;
    }

    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario = modelMapper.map(comentarioDto, Comentario.class);
        return comentario;
    }
}
