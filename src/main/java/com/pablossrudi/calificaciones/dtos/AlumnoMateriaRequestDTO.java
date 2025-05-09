package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AlumnoMateriaRequestDTO {
    private String alumnoId;
    private String materiaId;
    private BigDecimal nota;

    @Data
    @Builder
    public static class AlumnoMateriaPutRequestDTO {
        private BigDecimal nota;
    }
}
