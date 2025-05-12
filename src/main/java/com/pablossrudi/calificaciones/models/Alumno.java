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
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name = "alumno_id", nullable = false, unique = true)
    private String alumnoId;
    @Column(name = "alumno_rut", nullable = false, unique = true, length = 20)
    private String alumnoRut;
    @Column(name = "alumno_nombre", nullable = false, length = 100)
    private String alumnoNombre;
    @Column(name = "alumno_direccion", nullable = false, length = 200)
    private String alumnoDireccion;
    @Column(name = "estado", columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean estado;

    @OneToMany(mappedBy = "alumnoId")
    @ToString.Exclude
    private Set<AlumnoMateria> materias;

//    @PrePersist
//    protected void onCreate() {
//        if (estado == null) {
//            estado = true;
//        }
//    }
}
