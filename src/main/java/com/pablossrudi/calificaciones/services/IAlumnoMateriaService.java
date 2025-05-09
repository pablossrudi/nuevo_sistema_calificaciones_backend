package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.AlumnoMateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoMateriaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAlumnoMateriaService {
    AlumnoMateriaResponseDTO findAlumnoMateriaById(String id);
    Page<AlumnoMateriaResponseDTO> findAllAlumnoMaterias(Pageable pageable);
    void saveAlumnoMateria(AlumnoMateriaRequestDTO alumnoMateria);
    AlumnoMateriaResponseDTO updateAlumnoMateria(String id, AlumnoMateriaRequestDTO.AlumnoMateriaPutRequestDTO alumnoMateria);
    boolean deleteAlumnoMateria(String id);
}
