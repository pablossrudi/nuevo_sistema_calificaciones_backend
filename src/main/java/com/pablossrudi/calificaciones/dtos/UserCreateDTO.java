package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserCreateDTO {
    private String UserName;
    private String rut;
    private String Password;
    private String email;
    private Set<String> Roles;
}
