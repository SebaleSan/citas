package com.semana2.citas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;

@Service
public class CitaService {

    private final List<CitaResponseDTO> citas = new ArrayList<>();

    public CitaService() {

		citas.add(CitaResponseDTO.builder().id("1").nombrePaciente("Juan Perez").rutPaciente("12.345.678-9").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-01").hora("09:00").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("2").nombrePaciente("Maria Gonzalez").rutPaciente("11.222.333-4").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-01").hora("10:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("3").nombrePaciente("Carlos Rojas").rutPaciente("22.333.444-5").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-02").hora("11:00").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("4").nombrePaciente("Ana Torres").rutPaciente("33.444.555-6").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-02").hora("12:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("5").nombrePaciente("Juan Perez").rutPaciente("12.345.678-9").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-03").hora("09:30").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("6").nombrePaciente("Maria Gonzalez").rutPaciente("11.222.333-4").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-03").hora("10:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("7").nombrePaciente("Diego Morales").rutPaciente("44.555.666-7").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-04").hora("11:30").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("8").nombrePaciente("Camila Torres").rutPaciente("55.666.777-8").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-04").hora("12:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("9").nombrePaciente("Juan Perez").rutPaciente("12.345.678-9").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-05").hora("09:00").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("10").nombrePaciente("Ana Torres").rutPaciente("33.444.555-6").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-05").hora("10:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("11").nombrePaciente("Luis Herrera").rutPaciente("66.777.888-9").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-01").hora("09:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("12").nombrePaciente("Sofia Diaz").rutPaciente("77.888.999-0").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-01").hora("09:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("13").nombrePaciente("Pedro Castillo").rutPaciente("88.999.000-1").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-01").hora("10:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("14").nombrePaciente("Claudia Rios").rutPaciente("99.000.111-2").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-01").hora("10:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("15").nombrePaciente("Jorge Paredes").rutPaciente("10.111.222-3").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-02").hora("11:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("16").nombrePaciente("Valeria Mendez").rutPaciente("11.222.333-4").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-02").hora("11:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("17").nombrePaciente("Fernando Silva").rutPaciente("12.333.444-5").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-02").hora("12:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("18").nombrePaciente("Daniela Torres").rutPaciente("13.444.555-6").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-02").hora("12:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("19").nombrePaciente("Luis Herrera").rutPaciente("66.777.888-9").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-03").hora("09:45").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("20").nombrePaciente("Sofia Diaz").rutPaciente("77.888.999-0").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-03").hora("10:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("21").nombrePaciente("Pedro Castillo").rutPaciente("88.999.000-1").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-03").hora("10:45").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("22").nombrePaciente("Claudia Rios").rutPaciente("99.000.111-2").nombreMedico("Dr. Lopez").especialidad("Medicina General").fecha("2026-04-03").hora("11:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("23").nombrePaciente("Jorge Paredes").rutPaciente("10.111.222-3").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-04").hora("11:45").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("24").nombrePaciente("Valeria Mendez").rutPaciente("11.222.333-4").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-04").hora("12:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("25").nombrePaciente("Fernando Silva").rutPaciente("12.333.444-5").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-04").hora("12:45").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("26").nombrePaciente("Daniela Torres").rutPaciente("13.444.555-6").nombreMedico("Dr. Vega").especialidad("Pediatria").fecha("2026-04-04").hora("13:00").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("27").nombrePaciente("Luis Herrera").rutPaciente("66.777.888-9").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-05").hora("09:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("28").nombrePaciente("Sofia Diaz").rutPaciente("77.888.999-0").nombreMedico("Dr. Soto").especialidad("Traumatologia").fecha("2026-04-05").hora("09:30").estado("PROGRAMADA").build());

        citas.add(CitaResponseDTO.builder().id("29").nombrePaciente("Pedro Castillo").rutPaciente("88.999.000-1").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-05").hora("10:15").estado("PROGRAMADA").build());
        citas.add(CitaResponseDTO.builder().id("30").nombrePaciente("Claudia Rios").rutPaciente("99.000.111-2").nombreMedico("Dr. Ramirez").especialidad("Dermatologia").fecha("2026-04-05").hora("10:30").estado("PROGRAMADA").build());

	}

    public List<CitaResponseDTO> obtenerTodas() {

		return citas;
	}

    
    
    public CitaResponseDTO crear(CrearCitaRequestDTO request) {

    // Validar que el medico exista
    boolean medicoExiste = false;

    for (CitaResponseDTO cita : citas) {
        if (cita.getNombreMedico().equalsIgnoreCase(request.getNombreMedico())) {
            medicoExiste = true;
            break;
        }
    }

    if (!medicoExiste) {
        throw new RuntimeException("El medico ingresado no existe");
    }

    // convertir a minutos
    String[] nuevaHoraSplit = request.getHora().split(":");
    int nuevaMin = Integer.parseInt(nuevaHoraSplit[0]) * 60 + Integer.parseInt(nuevaHoraSplit[1]);

    // horarios permitidos entre (09:00 a 18:00)
    int inicio = 9 * 60;
    int fin = 18 * 60;

    if (nuevaMin < inicio || nuevaMin > fin) {
        throw new RuntimeException("Las citas solo pueden agendarse entre 09:00 y 18:00");
    }

    // diferencia de 15 minutos minimo entre cada cita
    for (CitaResponseDTO cita : citas) {

        boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(request.getNombreMedico());
        boolean mismaFecha = cita.getFecha().equals(request.getFecha());
        boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

        if (mismoMedico && mismaFecha && activa) {

            String[] horaExistenteSplit = cita.getHora().split(":");
            int existenteMin = Integer.parseInt(horaExistenteSplit[0]) * 60 + Integer.parseInt(horaExistenteSplit[1]);

            int diferencia = Math.abs(nuevaMin - existenteMin);

            if (diferencia < 15) {
                throw new RuntimeException("Debe existir al menos 15 minutos entre citas para el mismo medico");
            }
        }
    }

    int nuevoId = citas.size() + 1;

    CitaResponseDTO nuevaCita = CitaResponseDTO.builder()
            .id(String.valueOf(nuevoId))
            .nombrePaciente(request.getNombrePaciente())
            .rutPaciente(request.getRutPaciente())
            .nombreMedico(request.getNombreMedico())
            .especialidad(request.getEspecialidad())
            .fecha(request.getFecha())
            .hora(request.getHora())
            .estado("PROGRAMADA")
            .build();

    citas.add(nuevaCita);
    return nuevaCita;
}


    //cancelar citas mediante nombre doc, fecha y hora
    public CitaResponseDTO cancelar(String fecha, String hora, String nombreMedico) {

    for (CitaResponseDTO cita : citas) {

        boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(nombreMedico);
        boolean mismaFecha = cita.getFecha().equals(fecha);
        boolean mismaHora = cita.getHora().equals(hora);
        boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

        if (mismoMedico && mismaFecha && mismaHora && activa) {
            cita.setEstado("CANCELADA");
            return cita;
        }
    }

    return null;
    }

//consultar la disponibilidad de un doc en un dia filtrando los horarios que ya tienen una cita programada

    public List<String> consultarDisponibilidad(String nombreMedico, String fecha) {

    // Validar formato de fecha
    if (!fecha.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
        throw new RuntimeException("La fecha debe tener formato yyyy-MM-dd");
    }

    // Validar que el médico exista
    boolean medicoExiste = false;

    for (CitaResponseDTO cita : citas) {
        if (cita.getNombreMedico().equalsIgnoreCase(nombreMedico)) {
            medicoExiste = true;
            break;
        }
    }

    if (!medicoExiste) {
        throw new RuntimeException("El medico no existe");
    }


    List<String> disponibles = new ArrayList<>();

    int inicio = 9 * 60;   // 09:00
    int fin = 18 * 60;     // 18:00

    for (int minuto = inicio; minuto <= fin; minuto += 15) {

        int hora = minuto / 60;
        int min = minuto % 60;

        String horaFormateada = String.format("%02d:%02d", hora, min);

        boolean ocupado = false;

        for (CitaResponseDTO cita : citas) {

            boolean mismoMedico = cita.getNombreMedico().equalsIgnoreCase(nombreMedico);
            boolean mismaFecha = cita.getFecha().equals(fecha);
            boolean mismaHora = cita.getHora().equals(horaFormateada);
            boolean activa = cita.getEstado().equalsIgnoreCase("PROGRAMADA");

            if (mismoMedico && mismaFecha && mismaHora && activa) {
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






    
}
