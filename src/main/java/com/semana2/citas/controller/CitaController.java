package com.semana2.citas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;
import com.semana2.citas.service.CitaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

	public CitaController(CitaService service) {

		this.service = service;
	}
    

    @PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody CrearCitaRequestDTO request) {

		CitaResponseDTO creada = service.crear(request);

		return ResponseEntity.ok(creada);
	}

    @GetMapping
	public ResponseEntity<List<CitaResponseDTO>> obtenerTodas() {

		return ResponseEntity.ok(service.obtenerTodas());
	}
    
    @PutMapping("/cancelar")
    public ResponseEntity<?> cancelar(@RequestParam String fecha, @RequestParam String hora, @RequestParam String nombreMedico) {

    CitaResponseDTO cancelada = service.cancelar(fecha, hora, nombreMedico);

        if (cancelada == null) {
         return ResponseEntity.status(404)
                 .body("No se encontró una cita PROGRAMADA para el medico " + nombreMedico + " en la fecha " + fecha + " en el horario " + hora);
    }

        return ResponseEntity.ok(cancelada);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<String>> disponibilidad(@RequestParam String nombreMedico, @RequestParam String fecha) {

        return ResponseEntity.ok(service.consultarDisponibilidad(nombreMedico, fecha));
}






}


