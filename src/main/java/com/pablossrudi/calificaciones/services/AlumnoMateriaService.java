package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.assemblers.AlumnoMateriaAssembler;
import com.pablossrudi.calificaciones.dtos.AlumnoMateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoMateriaResponseDTO;
import com.pablossrudi.calificaciones.mappers.AlumnoMateriaMapper;
import com.pablossrudi.calificaciones.models.AlumnoMateria;
import com.pablossrudi.calificaciones.repositores.AlumnoMateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AlumnoMateriaService implements IAlumnoMateriaService {
    private final AlumnoMateriaRepository alumnoMateriaRepository;
    private final AlumnoMateriaMapper alumnoMateriaMapper;
    private final AlumnoMateriaAssembler alumnoMateriaAssembler;

    @Override
    public AlumnoMateriaResponseDTO findAlumnoMateriaById(String id) {
        return alumnoMateriaMapper.toDTO(alumnoMateriaRepository.findAlumnoMateriaById(id));
    }

    @Override
    public Page<AlumnoMateriaResponseDTO> findAllAlumnoMaterias(Pageable pageable) {
        return alumnoMateriaMapper.toPage(alumnoMateriaRepository.findAllAlumnoMaterias(pageable));
    }

    @Override
    public void saveAlumnoMateria(AlumnoMateriaRequestDTO alumnoMateria) {
        if (!rangoNota(alumnoMateria.getNota())){
            return;
        }
        AlumnoMateria almateria = alumnoMateriaAssembler.fromDTO(alumnoMateria);
        alumnoMateriaRepository.save(almateria);
    }

    @Override
    public AlumnoMateriaResponseDTO updateAlumnoMateria(String id, AlumnoMateriaRequestDTO.AlumnoMateriaPutRequestDTO alumnoMateria) {
        if (!rangoNota(alumnoMateria.getNota())){
            return null;
        }
        AlumnoMateria alumnoMateriaById = alumnoMateriaRepository.findAlumnoMateriaById(id);
        if (alumnoMateriaById == null) {
            return null;
        }
        alumnoMateriaById.setNota(alumnoMateria.getNota());
        alumnoMateriaRepository.save(alumnoMateriaById);

        return alumnoMateriaMapper.toDTO(alumnoMateriaById);
    }

    @Override
    public boolean deleteAlumnoMateria(String id) {
        if (!alumnoMateriaRepository.existsById(id)) {
            return false;
        }
        alumnoMateriaRepository.deleteById(id);
        return true;
    }

    private boolean rangoNota(BigDecimal nota){
        return nota.compareTo(BigDecimal.ZERO) >= 0 && nota.compareTo(BigDecimal.valueOf(7.0)) <= 0;
    }
}
