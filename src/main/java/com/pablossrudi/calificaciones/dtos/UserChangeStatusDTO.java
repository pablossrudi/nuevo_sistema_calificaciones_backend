package com.pablossrudi.calificaciones.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserChangeStatusDTO {
    private Boolean isActive;
}
