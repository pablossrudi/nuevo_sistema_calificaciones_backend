package com.pablossrudi.calificaciones.mappers;

import com.pablossrudi.calificaciones.dtos.AlumnoResponseDTO;
import com.pablossrudi.calificaciones.dtos.MateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.MateriaResponseDTO;
import com.pablossrudi.calificaciones.models.Alumno;
import com.pablossrudi.calificaciones.models.AlumnoMateria;
import com.pablossrudi.calificaciones.models.Materia;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MateriaMapper {
    public MateriaResponseDTO toDTO(Materia materia) {
        List<MateriaResponseDTO.AlumnoDTO> alumnos = new ArrayList<>();

        for (AlumnoMateria alumno : materia.getAlumnos()) {
            String alumnoId = alumno.getAlumnoId().getAlumnoId();
            String alumnoNombre = alumno.getAlumnoId().getAlumnoNombre();

            MateriaResponseDTO.AlumnoDTO alumnoDTO = MateriaResponseDTO.AlumnoDTO.builder()
                    .alumnoId(alumnoId)
                    .alumnoNombre(alumnoNombre)
                    .build();

            alumnos.add(alumnoDTO);
        }

        return MateriaResponseDTO.builder()
                .materiaId(materia.getMateriaId())
                .materiaNombre(materia.getMateriaNombre())
                .alumnos(alumnos).build();
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
