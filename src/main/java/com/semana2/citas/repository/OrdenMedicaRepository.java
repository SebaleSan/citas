package com.semana2.citas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semana2.citas.entity.OrdenMedicaEntity;

public interface OrdenMedicaRepository extends JpaRepository<OrdenMedicaEntity, Long> {
    
    List<OrdenMedicaEntity> findByPacienteIdPaciente(Long idPaciente);
    List<OrdenMedicaEntity> findByMedicoIdMedico(Long idMedico);


    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.paciente.idPaciente = :idPaciente AND o.medico.idMedico = :idMedico")
    List<OrdenMedicaEntity> findOrdenesMedicasByPacienteAndMedico(@Param("idPaciente") Long idPaciente, @Param("idMedico") Long idMedico);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.fechaCita = :fechaCita")
    List<OrdenMedicaEntity> findOrdenesMedicasByFechaCita(@Param("fechaCita") String fechaCita);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.horaCita = :horaCita")
    List<OrdenMedicaEntity> findOrdenesMedicasByHoraCita(@Param("horaCita") String horaCita);

    @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.activa = 1")
    List<OrdenMedicaEntity> findOrdenesMedicasByActiva();

     @Query("SELECT o FROM OrdenMedicaEntity o WHERE o.fechaEmision = :fechaEmision")
    List<OrdenMedicaEntity> findOrdenesMedicasByFechaEmision(@Param("fechaEmision") String fechaEmision);


}
