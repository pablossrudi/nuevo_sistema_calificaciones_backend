package com.pablossrudi.calificaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AlumnoMateriaNotasResponseDTO {
    private String materiaNombre;
    private List<NotaDTO> evaluaciones;

    @Data
    @Builder
    @AllArgsConstructor
    public static class NotaDTO {
        private String alumnoMateriaId;
        private BigDecimal nota;
    }
}
