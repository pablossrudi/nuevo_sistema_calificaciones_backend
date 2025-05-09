package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDTO {
    private String userName;
    private String password;
}
