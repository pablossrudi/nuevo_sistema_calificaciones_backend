package com.pablossrudi.calificaciones.repositores;

import com.pablossrudi.calificaciones.models.Alumno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, String> {
    @Query("SELECT al FROM Alumno al WHERE al.alumnoId = :id")
    Alumno findAlumnosById(@Param("id") String id);

    @Query("SELECT al FROM Alumno al ORDER BY al.alumnoRut ASC")
    Page<Alumno> findAllAlumnos(Pageable pageable);

    @Query("SELECT al FROM Alumno al WHERE al.alumnoRut = :rut")
    Alumno findAlumnosByRut(@Param("rut") String rut);
}
