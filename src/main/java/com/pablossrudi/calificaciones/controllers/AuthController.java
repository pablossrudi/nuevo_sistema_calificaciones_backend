package com.pablossrudi.calificaciones.controllers;

import com.pablossrudi.calificaciones.dtos.AuthenticationResponseDTO;
import com.pablossrudi.calificaciones.dtos.AuthenticationRequestDTO;
import com.pablossrudi.calificaciones.dtos.UserCreateDTO;
import com.pablossrudi.calificaciones.dtos.UserResponseDTO;
import com.pablossrudi.calificaciones.security.JwtUtil;
import com.pablossrudi.calificaciones.services.AuthService;
import com.pablossrudi.calificaciones.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        if (!authService.userExists(authenticationRequestDTO.getUserName())) {
            throw new UsernameNotFoundException("Usuario no encontrado" + authenticationRequestDTO.getUserName());
        }

        if (!authService.isUserActive(authenticationRequestDTO.getUserName())) {
            throw new UsernameNotFoundException("Usuario no esta activo" + authenticationRequestDTO.getUserName());
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDTO.getUserName(),
                        authenticationRequestDTO.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        String role = authService.getUserRoles(authenticationRequestDTO.getUserName());

        return ResponseEntity.ok(new AuthenticationResponseDTO(role, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?>registerUser(@RequestBody UserCreateDTO user) {
        UserResponseDTO userCreated = userService.createUser(user);

        return ResponseEntity.ok(userCreated);
    }
}
