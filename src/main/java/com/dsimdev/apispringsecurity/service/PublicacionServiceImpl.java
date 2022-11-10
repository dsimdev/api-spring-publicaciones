package com.dsimdev.apispringsecurity.service;

import com.dsimdev.apispringsecurity.dto.PublicacionDto;
import com.dsimdev.apispringsecurity.dto.PublicacionRespuesta;
import com.dsimdev.apispringsecurity.exeception.ResourceNotFoundException;
import com.dsimdev.apispringsecurity.entity.Publicacion;
import com.dsimdev.apispringsecurity.repository.PublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {

        Publicacion publicacion = mapearEntity(publicacionDto);
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        PublicacionDto publicacionResponse = mapearDto(nuevaPublicacion);

        return publicacionResponse;
    }

    @Override
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int pageNo, int pageSize, String orderBy, String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);

        List<Publicacion> listaPublicaciones = publicaciones.getContent();
        List<PublicacionDto> contenido = listaPublicaciones.stream().map(publicacion -> mapearDto(publicacion)).collect(Collectors.toList());

        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenido(contenido);
        publicacionRespuesta.setPageNo(publicaciones.getNumber());
        publicacionRespuesta.setPageSize(publicaciones.getSize());
        publicacionRespuesta.setTotalElements(publicaciones.getTotalElements());
        publicacionRespuesta.setTotalPages(publicaciones.getTotalPages());
        publicacionRespuesta.setLast(publicaciones.isLast());

        return publicacionRespuesta;
    }

    @Override
    public PublicacionDto obtenerPublicacionById(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearDto(publicacion);
    }

    @Override
    public PublicacionDto actualizarPublicacion(PublicacionDto publicacionDto, long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescripcion(publicacionDto.getDescripcion());
        publicacion.setContenido(publicacionDto.getContenido());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearDto(publicacion);
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepository.delete(publicacion);
    }

    // Method: ENTITY to DTO
    private PublicacionDto mapearDto(Publicacion publicacion) {
        return modelMapper.map(publicacion, PublicacionDto.class);
    }

    // Method: DTO to ENTITY
    private Publicacion mapearEntity(PublicacionDto publicacionDto) {

        return modelMapper.map(publicacionDto, Publicacion.class);
    }

}
