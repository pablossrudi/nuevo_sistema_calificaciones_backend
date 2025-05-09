package com.pablossrudi.calificaciones.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "materia_id", unique = true, nullable = false)
    private String materiaId;
    @Column(name = "materia_nombre", nullable = false, length = 100)
    private String materiaNombre;

    @OneToMany(mappedBy = "materiaId")
    @ToString.Exclude
    private Set<AlumnoMateria> alumnos;
}
