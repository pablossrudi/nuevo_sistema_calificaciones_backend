package com.pablossrudi.calificaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class AlumnoMateriaResponseDTO {
    private String alumnoMateriaId;
    private String alumnoId;
    private String materiaId;
    private BigDecimal nota;
}
