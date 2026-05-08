package com.semana2.citas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;
import com.semana2.citas.service.CitaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import jakarta.validation.Valid;


@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

	public CitaController(CitaService service) {

		this.service = service;
	}
    



    @PostMapping
    public ResponseEntity<CitaResponseDTO> crear(@Valid @RequestBody CrearCitaRequestDTO citaRequest) {

        CitaResponseDTO citaCreada = service.crear(citaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreada);
    }

    @GetMapping
	public ResponseEntity<List<CitaResponseDTO>> obtenerTodas() {
		List<CitaResponseDTO> citas = service.obtenerTodas();
		citas.forEach(this::agregarLinks);
		return ResponseEntity.ok(citas);
	}


    @GetMapping("/disponibilidad")
    public ResponseEntity<?> consultarDisponibilidad(
        @RequestParam String rutMedico,
        @RequestParam String fecha) {


    if (!fecha.matches("^(\\d{2}-\\d{2}-\\d{4}" +   
                       "|\\d{2}/\\d{2}/\\d{2}" +   
                       "|\\d{2}/\\d{2}/\\d{4}" +   
                       "|\\d{4}-\\d{2}-\\d{2}" +   
                       "|\\d{4}/\\d{2}/\\d{2})$")) { 
        return ResponseEntity.badRequest().body(
            "La fecha debe tener formato dd-MM-yyyy, dd/MM/yy, dd/MM/yyyy, yyyy-MM-dd o yyyy/MM/dd"
        );
    }

    List<String> disponibles = service.consultarDisponibilidad(rutMedico, fecha);
    return ResponseEntity.ok(disponibles);
    }






    
	@PutMapping("/cancelar")
	public ResponseEntity<?> cancelar(@RequestParam String fecha,
						  @RequestParam String hora,
						  @RequestParam String rutPaciente) {

		try {
			if (!fecha.matches("^(\\d{2}-\\d{2}-\\d{4}|\\d{2}/\\d{2}/\\d{2}|\\d{2}/\\d{2}/\\d{4}|\\d{4}-\\d{2}-\\d{2})$")) {
    return ResponseEntity.badRequest().body(
        "La fecha debe tener formato dd-MM-yyyy, dd/MM/yy, dd/MM/yyyy o yyyy-MM-dd"
    );
}


			CitaResponseDTO cancelada = service.cancelar(fecha, hora, rutPaciente);
			return ResponseEntity.ok(cancelada);

		} catch (RuntimeException ex) {
			return ResponseEntity.status(404)
					.body("No se encontró una cita PROGRAMADA para el paciente con RUT " + rutPaciente +
						" en la fecha " + fecha + " en el horario " + hora);
		}
	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminar(@RequestParam String fecha,
						  @RequestParam String hora,
						  @RequestParam String rutPaciente) {

		try {
			if (!fecha.matches("^(\\d{2}-\\d{2}-\\d{4}|\\d{2}/\\d{2}/\\d{2}|\\d{2}/\\d{2}/\\d{4}|\\d{4}-\\d{2}-\\d{2})$")) {
    return ResponseEntity.badRequest().body(
        "La fecha debe tener formato dd-MM-yyyy, dd/MM/yy, dd/MM/yyyy o yyyy-MM-dd"
    );
			}

			service.eliminar(fecha, hora, rutPaciente);
			return ResponseEntity.ok("Cita eliminada exitosamente");

		} catch (RuntimeException ex) {
			return ResponseEntity.status(404)
					.body("No se encontró una cita PROGRAMADA para el paciente con RUT " + rutPaciente +
						" en la fecha " + fecha + " en el horario " + hora);
		}
	}




	private void agregarLinks(CitaResponseDTO cita) {


  
    cita.add(linkTo(methodOn(CitaController.class)
        .obtenerTodas())
        .withRel("citas"));

   
    cita.add(linkTo(methodOn(CitaController.class)
        .crear(null)) // null porque solo necesitas la firma
        .withRel("crear"));

    cita.add(linkTo(methodOn(CitaController.class)
        .cancelar(
            cita.getFechaCita().toString(),
            cita.getHoraCita(),
            cita.getRutPaciente()
        ))
        .withRel("cancelar"));

    
    cita.add(linkTo(methodOn(CitaController.class)
        .eliminar(
            cita.getFechaCita().toString(),
            cita.getHoraCita(),
            cita.getRutPaciente()
        ))
        .withRel("eliminar"));

    
    cita.add(linkTo(methodOn(CitaController.class)
        .consultarDisponibilidad(
            cita.getRutMedico(),
            cita.getFechaCita().toString()
        ))
        .withRel("disponibilidad"));
}







}

								  