package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AlumnoResponseDTO {
    private String alumnoId;
    private String alumnoNombre;
    private String alumnoRut;
    private String alumnoDireccion;
    private Boolean estado;
    private List<AlumnoMateriaNotasResponseDTO> materias;

}
