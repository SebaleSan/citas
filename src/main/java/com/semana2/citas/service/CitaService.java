package com.semana2.citas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;
import com.semana2.citas.entity.CitaMedicaEntity;
import com.semana2.citas.entity.MedicoEntity;
import com.semana2.citas.entity.PacienteEntity;
import com.semana2.citas.repository.CitaMedicaRepository;
import com.semana2.citas.repository.MedicoRepository;
import com.semana2.citas.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class CitaService {

   private final MedicoRepository medicoRepository;
   private final PacienteRepository pacienteRepository;
   private final CitaMedicaRepository citaMedicaRepository;

   public CitaService(MedicoRepository medicoRepository, PacienteRepository pacienteRepository, CitaMedicaRepository citaMedicaRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.citaMedicaRepository = citaMedicaRepository;
    }

    private CitaResponseDTO toDTO(CitaMedicaEntity citaMedica){

        return new CitaResponseDTO(
            citaMedica.getIdCita(),
            citaMedica.getFechaCita(),
            citaMedica.getHoraCita(),
            citaMedica.getFechaEmision(),
            citaMedica.getActiva(),
            citaMedica.getMedico().getRut(),
            citaMedica.getPaciente().getRut(),
            citaMedica.getMedico().getEspecialidad()
        );
    }


    public List<CitaResponseDTO> obtenerTodas() {

        return citaMedicaRepository.findAll().stream().map(this::toDTO).toList();
    }


    // Crear una nueva cita médica con validaciones

    public CitaResponseDTO crear(CrearCitaRequestDTO request) {

    // Buscar médico por RUT
    MedicoEntity medico = medicoRepository.findByRut(request.getRutMedico())
            .orElseThrow(() -> new RuntimeException("El médico ingresado no existe"));

    // Buscar paciente por RUT
    PacienteEntity paciente = pacienteRepository.findByRut(request.getRutPaciente())
            .orElseThrow(() -> new RuntimeException("El paciente ingresado no existe"));

    // convertir a minutos
    String[] nuevaHoraSplit = request.getHoraCita().split(":");
    int nuevaMin = Integer.parseInt(nuevaHoraSplit[0]) * 60 + Integer.parseInt(nuevaHoraSplit[1]);

    // horarios permitidos entre (09:00 a 18:00)
    int inicio = 9 * 60;
    int fin = 18 * 60;

    if (nuevaMin < inicio || nuevaMin > fin) {
        throw new RuntimeException("Las citas solo pueden agendarse entre 09:00 y 18:00");
    }

    // diferencia de 15 minutos mínimo entre cada cita activa del mismo médico en la misma fecha
    List<CitaMedicaEntity> citasExistentes = citaMedicaRepository.findByMedicoIdMedico(medico.getIdMedico());
    for (CitaMedicaEntity cita : citasExistentes) {
        boolean mismaFecha = cita.getFechaCita().equals(request.getFechaCita());
        boolean activa = cita.getActiva() == 1;

        if (mismaFecha && activa) {
            String[] horaExistenteSplit = cita.getHoraCita().split(":");
            int existenteMin = Integer.parseInt(horaExistenteSplit[0]) * 60 + Integer.parseInt(horaExistenteSplit[1]);

            int diferencia = Math.abs(nuevaMin - existenteMin);

            if (diferencia < 15) {
                throw new RuntimeException("Debe existir al menos 15 minutos entre citas para el mismo médico");
            }
        }
    }

    CitaMedicaEntity nuevaCita = new CitaMedicaEntity();
    nuevaCita.setFechaCita(request.getFechaCita());
    nuevaCita.setHoraCita(request.getHoraCita());
    nuevaCita.setActiva(1); // por defecto activa
    nuevaCita.setMedico(medico);
    nuevaCita.setPaciente(paciente);

    CitaMedicaEntity guardada = citaMedicaRepository.save(nuevaCita);

    return new CitaResponseDTO(
            guardada.getIdCita(),
            guardada.getFechaCita(),             guardada.getHoraCita(),
            guardada.getFechaEmision(),
            guardada.getActiva(),
            guardada.getMedico().getRut(),
            guardada.getPaciente().getRut(),
            guardada.getMedico().getEspecialidad()
    );
}


  


//consultar la disponibilidad de un doc en un dia filtrando los horarios que ya tienen una cita programada

    public List<String> consultarDisponibilidad(String rutMedico, String fecha) {

    // Validar formato de fecha dd-MM-yyyy
    if (!fecha.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
        throw new RuntimeException("La fecha debe tener formato dd-MM-yyyy");
    }

    LocalDate fechaNormalizada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    //Comprobacion de existencia del médico
    MedicoEntity medico = medicoRepository.findByRut(rutMedico)
            .orElseThrow(() -> new RuntimeException("El médico ingresado no existe"));

    // Obtener todas las citas activas del médico en esa fecha
    List<CitaMedicaEntity> citasExistentes = citaMedicaRepository
            .findByMedicoAndFechaCitaAndActiva(medico, fechaNormalizada, 1);

    List<String> disponibles = new ArrayList<>();

    int inicio = 9 * 60;   // 09:00
    int fin = 18 * 60;     // 18:00

    for (int minuto = inicio; minuto <= fin; minuto += 15) {
        int hora = minuto / 60;
        int min = minuto % 60;

        String horaFormateada = String.format("%02d:%02d", hora, min);

        boolean ocupado = false;

        for (CitaMedicaEntity cita : citasExistentes) {
            boolean mismaHora = cita.getHoraCita().equals(horaFormateada);

            if (mismaHora) {
                ocupado = true;
                break;
            }
        }

        if (!ocupado) {
            disponibles.add(horaFormateada);
        }
    }

    return disponibles;
}

// Cancelar una cita médica (cambiar estado activa = 0)
    @Transactional
    public CitaResponseDTO cancelar(LocalDate fecha, String hora, String rutMedico) {

        MedicoEntity medico = medicoRepository.findByRut(rutMedico)
                .orElseThrow(() -> new RuntimeException("El médico ingresado no existe"));

        CitaMedicaEntity cita = citaMedicaRepository
                .findByMedicoAndFechaCitaAndHoraCitaAndActiva(medico, fecha, hora, 1)
                .orElseThrow(() -> new RuntimeException("No existe una cita programada con esos datos"));

        cita.setActiva(0);
        citaMedicaRepository.save(cita);

        return new CitaResponseDTO(
                cita.getIdCita(),
                cita.getFechaCita(),
                cita.getHoraCita(),
                cita.getFechaEmision(),
                cita.getActiva(),
                cita.getMedico().getRut(),
                cita.getPaciente().getRut(),
                cita.getMedico().getEspecialidad()
        );
    }

	public void eliminar(LocalDate fechaNormalizada, String hora, String rutMedico) {
		
        MedicoEntity medico = medicoRepository.findByRut(rutMedico)
                .orElseThrow(() -> new RuntimeException("El médico ingresado no existe"));

        CitaMedicaEntity cita = citaMedicaRepository
                .findByMedicoAndFechaCitaAndHoraCita(medico, fechaNormalizada, hora)
                .orElseThrow(() -> new RuntimeException("No existe una cita con esos datos"));

        citaMedicaRepository.delete(cita);
	}













    
}
