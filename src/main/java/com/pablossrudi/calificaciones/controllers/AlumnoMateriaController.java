package com.pablossrudi.calificaciones.controllers;

import com.pablossrudi.calificaciones.dtos.AlumnoMateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoMateriaResponseDTO;
import com.pablossrudi.calificaciones.services.IAlumnoMateriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/alumnoMateria")
@RequiredArgsConstructor
public class AlumnoMateriaController {
    private final IAlumnoMateriaService alumnoMateriaService;

    @PostMapping
    @Transactional
    public ResponseEntity<AlumnoMateriaResponseDTO> createAlumnoMateria(@RequestBody AlumnoMateriaRequestDTO alumnoMateriaRequestDTO) {
        alumnoMateriaService.saveAlumnoMateria(alumnoMateriaRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlumnoMateriaResponseDTO> editAlumnoMateria(@PathVariable String id, @RequestBody AlumnoMateriaRequestDTO.AlumnoMateriaPutRequestDTO alumnoMateriaRequestDTO) {
        AlumnoMateriaResponseDTO alumnoMateria = alumnoMateriaService.updateAlumnoMateria(id, alumnoMateriaRequestDTO);
        if (alumnoMateria == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alumnoMateria);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAlumnoMateria(@PathVariable String id) {
        boolean deleted = alumnoMateriaService.deleteAlumnoMateria(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }
}
