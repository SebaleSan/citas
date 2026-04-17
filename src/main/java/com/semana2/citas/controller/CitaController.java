package com.semana2.citas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;
import com.semana2.citas.dto.DisponibilidadResponseDTO;
import com.semana2.citas.dto.PacienteRequestDTO;
import com.semana2.citas.dto.PacienteResponseDTO;
import com.semana2.citas.service.CitaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

	public CitaController(CitaService service) {

		this.service = service;
	}
    

    // @PostMapping
	// public ResponseEntity<?> crear(@Valid @RequestBody CrearCitaRequestDTO request) {

	// 	CitaResponseDTO creada = service.crear(request);

	// 	return ResponseEntity.ok(creada);
	// }

    @PostMapping
    public ResponseEntity<CitaResponseDTO> crear(@Valid @RequestBody CrearCitaRequestDTO citaRequest) {

        CitaResponseDTO citaCreada = service.crear(citaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    @GetMapping
	public ResponseEntity<List<CitaResponseDTO>> obtenerTodas() {

		return ResponseEntity.ok(service.obtenerTodas());
	}
    
//     @PutMapping("/cancelar")
//     public ResponseEntity<?> cancelar(@RequestParam String fecha, @RequestParam String hora, @RequestParam String nombreMedico) {

//     CitaResponseDTO cancelada = service.cancelar(fecha, hora, nombreMedico);

//         if (cancelada == null) {
//          return ResponseEntity.status(404)
//                  .body("No se encontró una cita PROGRAMADA para el medico " + nombreMedico + " en la fecha " + fecha + " en el horario " + hora);
//     }

//         return ResponseEntity.ok(cancelada);
//     }

//     @GetMapping("/disponibilidad")
//     public ResponseEntity<DisponibilidadResponseDTO> disponibilidad(
//         @RequestParam String nombreMedico,
//         @RequestParam String fecha) {

//     List<String> horarios = service.consultarDisponibilidad(nombreMedico, fecha);

//     DisponibilidadResponseDTO response = DisponibilidadResponseDTO.builder()
//             .nombreMedico(nombreMedico)
//             .fecha(fecha)
//             .horariosDisponibles(horarios)
//             .build();

//         return ResponseEntity.ok(response);
// }






}


