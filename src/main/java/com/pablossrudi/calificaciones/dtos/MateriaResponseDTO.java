package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MateriaResponseDTO {
    private String materiaId;
    private String materiaNombre;
    private List<AlumnoDTO> alumnos;

    @Data
    @Builder
    public static class AlumnoDTO {
        private String alumnoId;
        private String alumnoNombre;
    }
}
