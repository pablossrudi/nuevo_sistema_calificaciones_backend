package com.pablossrudi.calificaciones.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "alumnos_materias")
public class AlumnoMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "alumno_materia_id", unique = true, nullable = false)
    private String alumnoMateriaId;

    @ManyToOne
    @JoinColumn(name = "alumno_id", referencedColumnName = "alumno_id", nullable = false)
    @ToString.Exclude
    private Alumno alumnoId;

    @ManyToOne
    @JoinColumn(name = "materia_id", referencedColumnName = "materia_id", nullable = false)
    @ToString.Exclude
    private Materia materiaId;

    @Column(name = "nota", nullable = false)
    private BigDecimal nota;
}
