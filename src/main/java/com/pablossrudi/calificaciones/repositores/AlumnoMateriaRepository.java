package com.pablossrudi.calificaciones.repositores;

import com.pablossrudi.calificaciones.models.AlumnoMateria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoMateriaRepository extends JpaRepository<AlumnoMateria, String> {
    @Query("SELECT am FROM AlumnoMateria am WHERE am.alumnoMateriaId = :id")
    AlumnoMateria findAlumnoMateriaById(@Param("id") String id);

    @Query("SELECT am FROM AlumnoMateria am ORDER BY am.alumnoMateriaId ASC")
    Page<AlumnoMateria> findAllAlumnoMaterias(Pageable pageable);
}
