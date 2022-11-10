package com.dsimdev.apispringsecurity.controller;

import com.dsimdev.apispringsecurity.dto.PublicacionDto;
import com.dsimdev.apispringsecurity.dto.PublicacionRespuesta;
import com.dsimdev.apispringsecurity.service.PublicacionService;
import com.dsimdev.apispringsecurity.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public PublicacionRespuesta listarPublicaciones(@RequestParam(value = "pageNo", defaultValue = Constants.NO_PAGE_DEFAULT, required = false) int pageNo,
                                                    @RequestParam(value = "pageSize", defaultValue = Constants.SIZE_PAGE_DEFAULT, required = false) int pageSize,
                                                    @RequestParam(value = "orderBy", defaultValue = Constants.ORDER_BY_DEFAULT, required = false) String orderBy,
                                                    @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY_DEFAULT, required = false) String sortBy) {
        return publicacionService.obtenerTodasLasPublicaciones(pageNo, pageSize, orderBy, sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDto> obtenerPublicacionById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicacionDto> guardarPublicacion(@Valid @RequestBody PublicacionDto publicacionDto) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDto> actualizarPublicacion(@Valid @RequestBody PublicacionDto publicacionDto, @PathVariable(name = "id") long id) {
        PublicacionDto publicacionResponse = publicacionService.actualizarPublicacion(publicacionDto, id);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") long id) {
        publicacionService.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicación eliminada con exito", HttpStatus.OK);
    }
}
