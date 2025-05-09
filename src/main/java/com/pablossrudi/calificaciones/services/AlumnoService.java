package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.AlumnoRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoResponseDTO;
import com.pablossrudi.calificaciones.mappers.AlumnoMapper;
import com.pablossrudi.calificaciones.models.Alumno;
import com.pablossrudi.calificaciones.repositores.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AlumnoService implements IAlumnoService{
    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    public AlumnoResponseDTO findAlumnoById(String id) {
        return alumnoMapper.toDTO(alumnoRepository.findAlumnosById(id));
    }

    @Override
    public Page<AlumnoResponseDTO> findAllAlumnos(Pageable pageable) {
        return alumnoMapper.toPage(alumnoRepository.findAllAlumnos(pageable));
    }

    @Override
    public void saveAlumno(AlumnoRequestDTO alumno) {
        alumnoRepository.save(alumnoMapper.toEntity(alumno));
    }

    @Override
    public AlumnoResponseDTO updateAlumno(String id, AlumnoRequestDTO alumno) {
        Alumno alumnosById = alumnoRepository.findAlumnosById(id);

        if (alumnosById == null) {
            return null;
        }

        alumnosById.setAlumnoNombre(alumno.getAlumnoNombre());
        alumnosById.setAlumnoRut(alumno.getAlumnoRut());
        alumnosById.setAlumnoDireccion(alumno.getAlumnoDireccion());

        alumnoRepository.save(alumnosById);
        return alumnoMapper.toDTO(alumnosById);
    }

    @Override
    public boolean deleteAlumno(String id) {
        if (!alumnoRepository.existsById(id)) {
            return false;
        }
        alumnoRepository.deleteById(id);
        return true;
    }
}
