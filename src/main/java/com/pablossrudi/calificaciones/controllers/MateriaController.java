package com.pablossrudi.calificaciones.controllers;

import com.pablossrudi.calificaciones.dtos.MateriaRequestDTO;
import com.pablossrudi.calificaciones.dtos.MateriaResponseDTO;
import com.pablossrudi.calificaciones.services.IMateriaService;
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
@RequestMapping("api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final IMateriaService materiaService;

    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponseDTO> getMateria(@PathVariable String id) {
        MateriaResponseDTO materia = materiaService.findMateriaById(id);
        if (materia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(materia);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllMaterias(
            @PageableDefault(page = 0, size = 5) Pageable pageable
    ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        Page<MateriaResponseDTO> materiaPage = materiaService.findAllMaterias(pageRequest);

        return ResponseEntity.ok(createPaginationResponse(materiaPage));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MateriaResponseDTO> createMateria(@RequestBody MateriaRequestDTO materiaRequestDTO) {
        materiaService.saveMateria(materiaRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MateriaResponseDTO> updateMateria(@PathVariable String id, @RequestBody MateriaRequestDTO materiaRequestDTO) {
        MateriaResponseDTO materia = materiaService.updateMateria(id, materiaRequestDTO);
        if (materia == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(materia);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteMateria(@PathVariable String id) {
        boolean deleted = materiaService.deleteMateria(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    private Map<String, Object> createPaginationResponse(Page<?> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("numberOfElements", page.getNumberOfElements());
        response.put("numberPage", page.getNumber());
        response.put("materias", page.getContent());
        return response;
    }
}
