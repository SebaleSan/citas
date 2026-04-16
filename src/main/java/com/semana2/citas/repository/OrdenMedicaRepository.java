package com.semana2.citas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semana2.citas.entity.OrdenMedicaEntity;

public interface OrdenMedicaRepository extends JpaRepository<OrdenMedicaEntity, Long> {
    
    List<OrdenMedicaEntity> findByPacienteId(Long id_paciente);
    List<OrdenMedicaEntity> findByMedicoId(Long id_medico);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.paciente.id_paciente = :id_paciente")
    List<OrdenMedicaEntity> findOrdenesMedicasByPacienteId(@Param("id_paciente") Long id_paciente);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.medico.id_medico = :id_medico")
    List<OrdenMedicaEntity> findOrdenesMedicasByMedicoId(@Param("id_medico") Long id_medico);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.paciente.id_paciente = :id_paciente AND o.medico.id_medico = :id_medico")
    List<OrdenMedicaEntity> findOrdenesMedicasByPacienteIdAndMedicoId(@Param("id_paciente") Long id_paciente, @Param("id_medico") Long id_medico);


    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.fecha_cita = :fechaCita")
    List<OrdenMedicaEntity> findOrdenesMedicasByFechaCita(@Param("fechaCita") String fechaCita);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.hora_cita = :horaCita")
    List<OrdenMedicaEntity> findOrdenesMedicasByHoraCita(@Param("horaCita") String horaCita);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.activa = 1")
    List<OrdenMedicaEntity> findOrdenesMedicasByActiva();

     @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.fecha_emision = :fechaEmision")
    List<OrdenMedicaEntity> findOrdenesMedicasByFechaEmision(@Param("fechaEmision") String fechaEmision);


}
