package com.semana2.citas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semana2.citas.entity.CitaMedicaEntity;
import com.semana2.citas.entity.MedicoEntity;

public interface CitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Long> {
    
    List<CitaMedicaEntity> findByPacienteIdPaciente(Long idPaciente);
    List<CitaMedicaEntity> findByMedicoIdMedico(Long idMedico);

    List<CitaMedicaEntity> findByIdCita(Long idCita);

    @Query("SELECT o FROM CitaMedicaEntity o WHERE o.idCita = :idCita")
    List<CitaMedicaEntity> findCitaMedicaByIdCita(@Param("idCita") Long idCita);

    @Query("SELECT o FROM CitaMedicaEntity o WHERE o.paciente.idPaciente = :idPaciente AND o.medico.idMedico = :idMedico")
    List<CitaMedicaEntity> findOrdenesMedicasByPacienteAndMedico(@Param("idPaciente") Long idPaciente, @Param("idMedico") Long idMedico);

    @Query("SELECT o FROM CitaMedicaEntity o WHERE o.fechaCita = :fechaCita")
    List<CitaMedicaEntity> findOrdenesMedicasByFechaCita(@Param("fechaCita") LocalDate fechaCita);

    @Query("SELECT o FROM CitaMedicaEntity o WHERE o.horaCita = :horaCita")
    List<CitaMedicaEntity> findOrdenesMedicasByHoraCita(@Param("horaCita") String horaCita);

    @Query("SELECT o FROM CitaMedicaEntity o WHERE o.activa = 1")
    List<CitaMedicaEntity> findOrdenesMedicasByActiva();

     @Query("SELECT o FROM CitaMedicaEntity o WHERE o.fechaEmision = :fechaEmision")
    List<CitaMedicaEntity> findOrdenesMedicasByFechaEmision(@Param("fechaEmision") String fechaEmision);
    
     List<CitaMedicaEntity> findByMedicoAndFechaCitaAndActiva(MedicoEntity medico, LocalDate fechaCita, Integer activa);


}
