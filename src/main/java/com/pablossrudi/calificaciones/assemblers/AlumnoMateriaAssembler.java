package com.pablossrudi.calificaciones.assemblers;

import com.pablossrudi.calificaciones.dtos.AlumnoMateriaRequestDTO;
import com.pablossrudi.calificaciones.models.Alumno;
import com.pablossrudi.calificaciones.models.AlumnoMateria;
import com.pablossrudi.calificaciones.models.Materia;
import com.pablossrudi.calificaciones.repositores.AlumnoRepository;
import com.pablossrudi.calificaciones.repositores.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlumnoMateriaAssembler {

    private final MateriaRepository materiaRepository;
    private final AlumnoRepository alumnoRepository;

    public AlumnoMateria fromDTO(AlumnoMateriaRequestDTO dto) {
        if (dto.getAlumnoId() == null) {
            throw new IllegalArgumentException("El ID del alumno no puede ser null");
        }

        if (dto.getMateriaId() == null) {
            throw new IllegalArgumentException("El ID de la materia no puede ser null");
        }
        
        Alumno alumno = alumnoRepository.findAlumnosById(dto.getAlumnoId());

        Materia materia = materiaRepository.findMateriaById(dto.getMateriaId());

        if (alumno == null) {
            throw new IllegalArgumentException("Alumno id cannot be null");
        }
        if (materia == null) {
            throw new IllegalArgumentException("Materia id cannot be null");
        }

        AlumnoMateria alumnoMateria = new AlumnoMateria();
        alumnoMateria.setAlumnoId(alumno);
        alumnoMateria.setMateriaId(materia);
        alumnoMateria.setNota(dto.getNota());

        return alumnoMateria;
    }

}
