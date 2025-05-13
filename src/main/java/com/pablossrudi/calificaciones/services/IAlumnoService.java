package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.AlumnoRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAlumnoService {
    AlumnoResponseDTO findAlumnoById(String id);
    Page<AlumnoResponseDTO> findAllAlumnos(Pageable pageable);
    void saveAlumno(AlumnoRequestDTO alumno);
    AlumnoResponseDTO updateAlumno(String id, AlumnoRequestDTO alumnoRequestDTO);
    boolean deleteAlumno(String id);
    AlumnoResponseDTO findAlumnoByRut(String rut);
}
