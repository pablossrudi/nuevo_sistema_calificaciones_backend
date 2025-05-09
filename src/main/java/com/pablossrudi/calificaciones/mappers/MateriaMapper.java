package com.pablossrudi.calificaciones.mappers;

import com.pablossrudi.calificaciones.dtos.MateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.MateriaResponseDTO;
import com.pablossrudi.calificaciones.models.Materia;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MateriaMapper {
    public MateriaResponseDTO toDTO(Materia materia) {
        return MateriaResponseDTO.builder()
                .materiaId(materia.getMateriaId())
                .materiaNombre(materia.getMateriaNombre())
                .build();
    }

    public Materia toEntity(MateriaRequestDTO dto) {
        Materia materia = new Materia();
        materia.setMateriaNombre(dto.getMateriaNombre());
        return materia;
    }

    public Page<MateriaResponseDTO> toPage(Page<Materia> dtoPage) {
        return dtoPage.map(this::toDTO);
    }
}
