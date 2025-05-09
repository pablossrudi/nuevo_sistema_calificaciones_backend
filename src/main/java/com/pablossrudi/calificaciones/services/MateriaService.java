package com.pablossrudi.calificaciones.services;

import com.pablossrudi.calificaciones.dtos.MateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.MateriaResponseDTO;
import com.pablossrudi.calificaciones.mappers.MateriaMapper;
import com.pablossrudi.calificaciones.models.Materia;
import com.pablossrudi.calificaciones.repositores.MateriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MateriaService implements IMateriaService {
    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    @Override
    public MateriaResponseDTO findMateriaById(String id) {
        return materiaMapper.toDTO(materiaRepository.findMateriaById(id));
    }

    @Override
    public Page<MateriaResponseDTO> findAllMaterias(Pageable pageable) {
        return materiaMapper.toPage(materiaRepository.findAllMaterias(pageable));
    }

    @Override
    public void saveMateria(MateriaRequestDTO materia) {
        materiaRepository.save(materiaMapper.toEntity(materia));
    }

    @Override
    public MateriaResponseDTO updateMateria(String id, MateriaRequestDTO materia) {
        Materia materiaById = materiaRepository.findMateriaById(id);

        if (materiaById == null) {
            return null;
        }

        materiaById.setMateriaNombre(materia.getMateriaNombre());
        materiaRepository.save(materiaById);

        return materiaMapper.toDTO(materiaById);
    }

    @Override
    public boolean deleteMateria(String id) {
        if(!materiaRepository.existsById(id)){
            return false;
        }
        materiaRepository.deleteById(id);
        return true;
    }
}
