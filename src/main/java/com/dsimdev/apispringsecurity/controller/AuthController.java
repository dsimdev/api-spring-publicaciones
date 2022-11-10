package com.dsimdev.apispringsecurity.controller;

import com.dsimdev.apispringsecurity.dto.JwtAuthResponseDto;
import com.dsimdev.apispringsecurity.dto.RegistroDto;
import com.dsimdev.apispringsecurity.entity.Usuario;
import com.dsimdev.apispringsecurity.entity.Rol;
import com.dsimdev.apispringsecurity.repository.RolRepository;
import com.dsimdev.apispringsecurity.repository.UsuarioRepository;
import com.dsimdev.apispringsecurity.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dsimdev.apispringsecurity.dto.LoginDto;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/iniciarSesion")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Obtenemos el token
        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponseDto(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario (@RequestBody RegistroDto registroDto) {
        if (usuarioRepository.existsByUsername(registroDto.getUsername())) {
            return new ResponseEntity<>("El username ya fue registrado", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepository.existsByEmail(registroDto.getEmail())){
            return new ResponseEntity<>("El email ya fue registrado", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        Rol roles = rolRepository.findByTipo("ROLE_ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);

    }
}
