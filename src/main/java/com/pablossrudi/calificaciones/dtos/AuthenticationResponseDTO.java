package com.pablossrudi.calificaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseDTO {
    private String role;
    private String jwt;
}
