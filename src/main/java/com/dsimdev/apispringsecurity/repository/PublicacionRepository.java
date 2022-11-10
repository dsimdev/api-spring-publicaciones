package com.dsimdev.apispringsecurity.repository;

import com.dsimdev.apispringsecurity.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
