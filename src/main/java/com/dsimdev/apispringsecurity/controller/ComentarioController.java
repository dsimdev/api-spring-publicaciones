package com.dsimdev.apispringsecurity.controller;

import com.dsimdev.apispringsecurity.dto.ComentarioDto;
import com.dsimdev.apispringsecurity.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDto> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId") Long publicacionId) {
        return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
    }

    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDto> obtenerComentarioPorId(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId) {
        ComentarioDto comentarioDto = comentarioService.obtenerComentarioPorId(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(@PathVariable(value = "publicacionId") Long publicacionId,@Valid @RequestBody ComentarioDto comentarioDto) {
        return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDto> actualizarComentario(@PathVariable(value = "publicacionId") Long publicacionId, @PathVariable(value = "comentarioId") Long comentarioId, @Valid @RequestBody ComentarioDto comentarioDto) {
        ComentarioDto comentarioDtoActualizado = comentarioService.actualizarComentario(publicacionId, comentarioId, comentarioDto);
        return new ResponseEntity<>(comentarioDtoActualizado,HttpStatus.OK);
    }


}
