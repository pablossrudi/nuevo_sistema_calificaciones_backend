package com.pablossrudi.calificaciones.repositores;

import com.pablossrudi.calificaciones.models.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, String> {
    @Query("SELECT ma FROM Materia ma WHERE ma.materiaId = :id")
    Materia findMateriaById (@Param("id") String id);

    @Query("SELECT ma FROM Materia ma ORDER BY ma.materiaId ASC")
    Page<Materia> findAllMaterias (Pageable pageable);
}
