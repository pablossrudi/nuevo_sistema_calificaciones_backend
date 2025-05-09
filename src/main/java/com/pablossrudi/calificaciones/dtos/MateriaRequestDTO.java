package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MateriaRequestDTO {
    private String materiaNombre;
}
