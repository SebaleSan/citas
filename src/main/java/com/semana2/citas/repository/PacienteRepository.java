package com.semana2.citas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semana2.citas.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    List<PacienteEntity> findByNombreContaining(String nombre);
    List<PacienteEntity> findByApellidoContaining(String apellido);

    @Query("SELECT p FROM PacienteEntity p WHERE p.nombre = :nombre")
    List<PacienteEntity> findPacientesByNombreExacto(@Param("nombre") String nombre);

    @Query("SELECT p FROM PacienteEntity p WHERE p.apellido = :apellido")
    List<PacienteEntity> findPacientesByApellidoExacto(@Param("apellido") String apellido);

    @Query("SELECT p FROM PacienteEntity p WHERE p.rut = :rut")
    PacienteEntity findPacienteByRut(@Param("rut") String rut);

    @Query("SELECT p FROM PacienteEntity p WHERE p.nombre = :nombre AND p.nombre LIKE %:nombre%")
    List<PacienteEntity> findPacientesByNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM PacienteEntity p WHERE p.apellido = :apellido AND p.nombre LIKE %:apellido%")
    List<PacienteEntity> findPacientesByApellido(@Param("apellido") String apellido);


    @Query("SELECT p FROM PacienteEntity p WHERE p.edad = :edad")
    List<PacienteEntity> findPacientesByEdad(@Param("edad") Integer edad);

    @Query("SELECT p FROM PacienteEntity p WHERE p.edad >= :edad")
    List<PacienteEntity> findPacientesByEdadGreaterThanEqual(@Param("edad") Integer edad);

    
    
}