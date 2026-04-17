package com.semana2.citas.service;

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
            citaMedica.getMedico().getIdMedico(),
            citaMedica.getPaciente().getIdPaciente()
        );
    }


    public List<CitaResponseDTO> obtenerTodas() {

        return citaMedicaRepository.findAll().stream().map(this::toDTO).toList();
    }


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

    // Crear nueva cita
    CitaMedicaEntity nuevaCita = new CitaMedicaEntity();
    nuevaCita.setFechaCita(request.getFechaCita());
    nuevaCita.setHoraCita(request.getHoraCita());
    nuevaCita.setActiva(1); // por defecto activa
    nuevaCita.setMedico(medico);
    nuevaCita.setPaciente(paciente);

    

    CitaMedicaEntity guardada = citaMedicaRepository.save(nuevaCita);

    // Convertir a DTO
    return new CitaResponseDTO(
            guardada.getIdCita(),
            guardada.getFechaCita(),
            guardada.getHoraCita(),
            guardada.getFechaEmision(),
            guardada.getActiva(),
            guardada.getMedico().getIdMedico(),
            guardada.getPaciente().getIdPaciente()
    );
}

  

    
    
//     public CitaResponseDTO crear(CrearCitaRequestDTO request) {


//     boolean medicoExiste = false;

//     for (CitaResponseDTO cita : citas) {
//         if (cita.getNombreMedico().equalsIgnoreCase(request.getNombreMedico())) {
//             medicoExiste = true;
//             break;
//         }
//     }

//     if (!medicoExiste) {
//         throw new RuntimeException("El medico ingresado no existe");
//     }

//     // convertir a minutos
//     String[] nuevaHoraSplit = request.getHora().split(":");
//     int nuevaMin = Integer.parseInt(nuevaHoraSplit[0]) * 60 + Integer.parseInt(nuevaHoraSplit[1]);

//     // horarios permitidos entre (09:00 a 18:00)
//     int inicio = 9 * 60;
//     int fin = 18 * 60;

//     if (nuevaMin < inicio || nuevaMin > fin) {
//         throw new RuntimeException("Las citas solo pueden agendarse entre 09:00 y 18:00");
//     }

//     // diferencia de 15 minutos minimo entre cada cita
//     for (CitaResponseDTO cita : citas) {

//         boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(request.getNombreMedico());
//         boolean mismaFecha = cita.getFecha().equals(request.getFecha());
//         boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

//         if (mismoMedico && mismaFecha && activa) {

//             String[] horaExistenteSplit = cita.getHora().split(":");
//             int existenteMin = Integer.parseInt(horaExistenteSplit[0]) * 60 + Integer.parseInt(horaExistenteSplit[1]);

//             int diferencia = Math.abs(nuevaMin - existenteMin);

//             if (diferencia < 15) {
//                 throw new RuntimeException("Debe existir al menos 15 minutos entre citas para el mismo medico");
//             }
//         }
//     }

//     int nuevoId = citas.size() + 1;

//     CitaResponseDTO nuevaCita = CitaResponseDTO.builder()
//             .id(String.valueOf(nuevoId))
//             .nombrePaciente(request.getNombrePaciente())
//             .rutPaciente(request.getRutPaciente())
//             .nombreMedico(request.getNombreMedico())
//             .especialidad(request.getEspecialidad())
//             .fecha(request.getFecha())
//             .hora(request.getHora())
//             .estado("PROGRAMADA")
//             .build();

//     citas.add(nuevaCita);
//     return nuevaCita;
// }


//     //cancelar citas mediante nombre doc, fecha y hora
//     public CitaResponseDTO cancelar(String fecha, String hora, String nombreMedico) {

//     for (CitaResponseDTO cita : citas) {

//         boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(nombreMedico);
//         boolean mismaFecha = cita.getFecha().equals(fecha);
//         boolean mismaHora = cita.getHora().equals(hora);
//         boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

//         if (mismoMedico && mismaFecha && mismaHora && activa) {
//             cita.setEstado("CANCELADA");
//             return cita;
//         }
//     }

//     return null;
//     }

// //consultar la disponibilidad de un doc en un dia filtrando los horarios que ya tienen una cita programada

//     public List<String> consultarDisponibilidad(String nombreMedico, String fecha) {

//     // Validar formato de fecha
//     if (!fecha.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
//         throw new RuntimeException("La fecha debe tener formato yyyy-MM-dd");
//     }

//     // Validar que el médico exista
//     boolean medicoExiste = false;

//     for (CitaResponseDTO cita : citas) {
//         if (cita.getNombreMedico().equalsIgnoreCase(nombreMedico)) {
//             medicoExiste = true;
//             break;
//         }
//     }

//     if (!medicoExiste) {
//         throw new RuntimeException("El medico no existe");
//     }


//     List<String> disponibles = new ArrayList<>();

//         int inicio = 9 * 60;   
//         int fin = 18 * 60;     

//     for (int minuto = inicio; minuto <= fin; minuto += 15) {

//         int hora = minuto / 60;
//         int min = minuto % 60;

//         String horaFormateada = String.format("%02d:%02d", hora, min);

//         boolean ocupado = false;

//         for (CitaResponseDTO cita : citas) {

//             boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(nombreMedico);
//             boolean mismaFecha = cita.getFecha().equals(fecha);
//             boolean mismaHora = cita.getHora().equals(horaFormateada);
//             boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

//             if (mismoMedico && mismaFecha && mismaHora && activa) {
//                 ocupado = true;
//                 break;
//             }
//         }

//         if (!ocupado) {
//             disponibles.add(horaFormateada);
//         }
//     }

//          return disponibles;
//     }






    
}
