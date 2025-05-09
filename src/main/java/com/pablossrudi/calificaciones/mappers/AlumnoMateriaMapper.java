package com.pablossrudi.calificaciones.mappers;

import com.pablossrudi.calificaciones.dtos.AlumnoMateriaResponseDTO;
import com.pablossrudi.calificaciones.models.AlumnoMateria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AlumnoMateriaMapper {
    public AlumnoMateriaResponseDTO toDTO(AlumnoMateria alumnoMateria) {
        return AlumnoMateriaResponseDTO.builder()
                .alumnoMateriaId(alumnoMateria.getAlumnoMateriaId())
                .alumnoId(alumnoMateria.getAlumnoId().getAlumnoId())
                .materiaId(alumnoMateria.getMateriaId().getMateriaId())
                .nota(alumnoMateria.getNota())
                .build();
    }

    public Page<AlumnoMateriaResponseDTO> toPage(Page<AlumnoMateria> dtoPage) {
        return dtoPage.map(this::toDTO);
    }
}
