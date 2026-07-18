package com.app.CitaMed.Repository.Medico;

import com.app.CitaMed.DTO.MedicoActivoDTO;
import com.app.CitaMed.Model.Medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

@Repository

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    long count();

    boolean existsByDni(String dni);

    @Query("SELECT m FROM Medico m JOIN FETCH m.especialidad LEFT JOIN FETCH m.consultorio WHERE m.especialidad.id = :especialidadId AND m.activo = true")
    List<Medico> findByEspecialidadIdAndActivoTrue(@Param("especialidadId") Long especialidadId);

    @Query("""
            SELECT new com.app.CitaMed.DTO.MedicoActivoDTO(
            SUBSTRING(c.medico.nombre,1,1),
            CONCAT(c.medico.nombre,' ',c.medico.apellidoPaterno),
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre,
            COUNT(c)
            )
            FROM Cita c
            GROUP BY c.medico.id,
            c.medico.nombre,
            c.medico.apellidoPaterno,
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre
            ORDER BY COUNT(c) DESC
            """)
    List<MedicoActivoDTO> medicosActivos();

    @Query("""
            SELECT new com.app.CitaMed.DTO.MedicoActivoDTO(
            SUBSTRING(c.medico.nombre,1,1),
            CONCAT(c.medico.nombre,' ',c.medico.apellidoPaterno),
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre,
            COUNT(c)
            )
            FROM Cita c
            WHERE YEAR(c.fechaHora) = :anio
            GROUP BY c.medico.id,
            c.medico.nombre,
            c.medico.apellidoPaterno,
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre
            ORDER BY COUNT(c) DESC
            """)
    List<MedicoActivoDTO> medicosMasActivosPorAnio(@Param("anio") int anio, Pageable pageable);

    @Query("""
            SELECT new com.app.CitaMed.DTO.MedicoActivoDTO(
            SUBSTRING(c.medico.nombre,1,1),
            CONCAT(c.medico.nombre,' ',c.medico.apellidoPaterno),
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre,
            COUNT(c)
            )
            FROM Cita c
            WHERE c.fechaHora BETWEEN :inicio AND :fin
            GROUP BY c.medico.id,
            c.medico.nombre,
            c.medico.apellidoPaterno,
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre
            ORDER BY COUNT(c) DESC
            """)
    List<MedicoActivoDTO> medicosMasActivosBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin, Pageable pageable);

    Optional<Medico> findByUsuarioUserName(String userName);

    @Query("""
            SELECT new com.app.CitaMed.DTO.MedicoActivoDTO(
            SUBSTRING(c.medico.nombre,1,1),
            CONCAT(c.medico.nombre,' ',c.medico.apellidoPaterno),
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre,
            COUNT(c)
            )
            FROM Cita c
            WHERE c.medico.id = :medicoId
            GROUP BY c.medico.id,
            c.medico.nombre,
            c.medico.apellidoPaterno,
            c.medico.numeroColegiatura,
            c.medico.especialidad.nombre
            """)
    List<MedicoActivoDTO> medicosActivosPorMedico(@Param("medicoId") Long medicoId);

    @Query("SELECT m FROM Medico m JOIN FETCH m.especialidad LEFT JOIN FETCH m.consultorio WHERE m.activo = true")
    List<Medico> findByActivoTrue();
}