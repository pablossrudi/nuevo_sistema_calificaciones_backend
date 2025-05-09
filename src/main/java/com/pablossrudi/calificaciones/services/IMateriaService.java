package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.MateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.MateriaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMateriaService {
    MateriaResponseDTO findMateriaById(String id);
    Page<MateriaResponseDTO> findAllMaterias(Pageable pageable);
    void saveMateria(MateriaRequestDTO materia);
    MateriaResponseDTO updateMateria(String id, MateriaRequestDTO materiaRequestDTO);
    boolean deleteMateria(String id);
}
