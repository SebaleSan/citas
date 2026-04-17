package com.semana2.citas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.semana2.citas.entity.MedicoEntity;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    List<MedicoEntity> findByEspecialidad(String especialidad);
    List<MedicoEntity> findByNombreContaining(String nombre);
    MedicoEntity findByNombreAndApellido(String nombre, String apellido);

    // @Query("SELECT m FROM MedicoEntity m WHERE m.rut = :rut")
    // MedicoEntity findByRut(@Param("rut") String rut);

    Optional<MedicoEntity> findByRut(String rut);

    @Query("SELECT m FROM MedicoEntity m WHERE m.idMedico = :idMedico")
    MedicoEntity findMedicoById(@Param("idMedico") Long idMedico);

    @Query("SELECT m FROM MedicoEntity m WHERE m.especialidad = :especialidad")
    List<MedicoEntity> findMedicosByEspecialidad(@Param("especialidad") String especialidad);

    @Query("SELECT m FROM MedicoEntity m WHERE m.nombre = :nombre")
    List<MedicoEntity> findMedicosByNombreExacto(@Param("nombre") String nombre);

      @Query("SELECT m FROM MedicoEntity m WHERE m.apellido = :apellido")
    List<MedicoEntity> findMedicosByApellidoExacto(@Param("apellido") String apellido);

    @Query("SELECT m FROM MedicoEntity m WHERE m.rut = :rut")
    MedicoEntity findMedicoByRut(@Param("rut") String rut);

    @Query("SELECT m FROM MedicoEntity m WHERE m.nombre = :nombre AND m.nombre LIKE %:nombre%")
    List<MedicoEntity> findMedicosByNombre(@Param("nombre") String nombre);

    @Query("SELECT m FROM MedicoEntity m WHERE m.apellido = :apellido AND m.nombre LIKE %:apellido%")
    List<MedicoEntity> findMedicosByApellido(@Param("apellido") String apellido);

}
