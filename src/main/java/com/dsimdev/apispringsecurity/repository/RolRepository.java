package com.dsimdev.apispringsecurity.repository;

import com.dsimdev.apispringsecurity.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByTipo(String tipo);

}
