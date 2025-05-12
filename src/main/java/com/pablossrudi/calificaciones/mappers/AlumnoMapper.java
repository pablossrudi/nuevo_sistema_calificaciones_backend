package com.pablossrudi.calificaciones.mappers;

import com.pablossrudi.calificaciones.dtos.AlumnoMateriaNotasResponseDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoResponseDTO;
import com.pablossrudi.calificaciones.models.Alumno;
import com.pablossrudi.calificaciones.models.AlumnoMateria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AlumnoMapper {
    public AlumnoResponseDTO toDTO(Alumno alumno) {
        Map<String, List<AlumnoMateriaNotasResponseDTO.NotaDTO>> materiasConNotas = new HashMap<>();

        for (AlumnoMateria alumnoMateria : alumno.getMaterias()) {
            String alumnoMateriaId = alumnoMateria.getAlumnoMateriaId();
            String materiasNombre = alumnoMateria.getMateriaId().getMateriaNombre();
            BigDecimal nota = alumnoMateria.getNota();

            AlumnoMateriaNotasResponseDTO.NotaDTO notaDTO = AlumnoMateriaNotasResponseDTO.NotaDTO.builder()
                    .alumnoMateriaId(alumnoMateriaId)
                    .nota(nota)
                    .build();

            materiasConNotas.computeIfAbsent(materiasNombre, k -> new ArrayList<>()).add(notaDTO);
        }

        List<AlumnoMateriaNotasResponseDTO> materiasDto = materiasConNotas.entrySet().stream()
                .map(entry -> AlumnoMateriaNotasResponseDTO.builder()
                        .materiaNombre(entry.getKey())
                        .evaluaciones(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return AlumnoResponseDTO.builder()
                .alumnoId(alumno.getAlumnoId())
                .alumnoNombre(alumno.getAlumnoNombre())
                .alumnoRut(alumno.getAlumnoRut())
                .alumnoDireccion(alumno.getAlumnoDireccion())
                .estado(alumno.getEstado())
                .materias(materiasDto)
                .build();
    }

    public Alumno toEntity(AlumnoRequestDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setAlumnoNombre(dto.getAlumnoNombre());
        alumno.setAlumnoRut(dto.getAlumnoRut());
        alumno.setAlumnoDireccion(dto.getAlumnoDireccion());

        return alumno;
    }

    public Page<AlumnoResponseDTO> toPage(Page<Alumno> dtoPage) {
        return dtoPage.map(this::toDTO);
    }
}
