package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlumnoRequestDTO {
    private String alumnoNombre;
    private String alumnoRut;
    private String alumnoDireccion;
}
