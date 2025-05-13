package com.pablossrudi.calificaciones.controllers;

import com.pablossrudi.calificaciones.dtos.AlumnoRequestDTO;
import com.pablossrudi.calificaciones.dtos.AlumnoResponseDTO;
import com.pablossrudi.calificaciones.mappers.AlumnoMapper;
import com.pablossrudi.calificaciones.services.IAlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/alumnos")
@RequiredArgsConstructor
public class AlumnoController {
    private final IAlumnoService alumnoService;
    private final AlumnoMapper alumnoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> getAlumno(@PathVariable String id){
        AlumnoResponseDTO alumno = alumnoService.findAlumnoById(id);
        if(alumno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alumno);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAlumnos(
            @PageableDefault(size = 5, page = 0) Pageable pageable
            ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<AlumnoResponseDTO> alumnoPage = alumnoService.findAllAlumnos(pageRequest);

        return ResponseEntity.ok(createPaginationResponse(alumnoPage));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AlumnoResponseDTO> createAlumno(@RequestBody AlumnoRequestDTO alumnoRequestDTO) {
        alumnoService.saveAlumno(alumnoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AlumnoResponseDTO> editAlumno(@PathVariable String id, @RequestBody AlumnoRequestDTO alumnoRequestDTO) {
        AlumnoResponseDTO alumno = alumnoService.updateAlumno(id, alumnoRequestDTO);
        if(alumno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alumno);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<AlumnoResponseDTO> getAlumnoByRut(@PathVariable String rut){
        AlumnoResponseDTO alumno = alumnoService.findAlumnoByRut(rut);
        if(alumno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(alumno);
    }

    @PutMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Boolean> deleteAlumno(@PathVariable String id) {
        AlumnoResponseDTO alumnoResponseDTO = alumnoService.findAlumnoById(id);
        alumnoResponseDTO.setEstado(false);

        AlumnoResponseDTO alumno = alumnoService.updateAlumno(id,alumnoMapper.toEntityRequest(alumnoResponseDTO));

        if(alumno == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(true);
    }

    private Map<String, Object> createPaginationResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("numberOfElements", page.getNumberOfElements());
        response.put("numberPage", page.getNumber());
        response.put("alumnos", page.getContent());
        return response;
    }
}
