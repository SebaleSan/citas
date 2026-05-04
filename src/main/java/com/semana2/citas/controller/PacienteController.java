package com.semana2.citas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.semana2.citas.dto.PacienteRequestDTO;
import com.semana2.citas.dto.PacienteResponseDTO;

import com.semana2.citas.service.PacienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
	public ResponseEntity<List<PacienteResponseDTO>> obtenerTodos() {
        List<PacienteResponseDTO> pacientes = pacienteService.obtenerTodos();
        pacientes.forEach(this::agregarLinks);

		return ResponseEntity.ok(pacientes);
	}
 
    
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {

        PacienteResponseDTO pacienteCreado = pacienteService.crearPaciente(pacienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado);
    }


    @GetMapping("/{id}")
	public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {

		PacienteResponseDTO paciente = pacienteService.obtenerPorId(id);

		if (paciente == null) {

			return ResponseEntity.notFound().build();
		}

		agregarLinks(paciente);
		return ResponseEntity.ok(paciente);
	}
    
    private void agregarLinks(PacienteResponseDTO paciente) {
        
        paciente.add(linkTo(methodOn(PacienteController.class)
            .obtenerTodos()) 
            .withSelfRel());

        paciente.add(linkTo(methodOn(PacienteController.class).obtenerPorId(paciente.getIdPaciente())).withSelfRel());
    

    
        paciente.add(linkTo(methodOn(PacienteController.class)
            .obtenerTodos())
            .withRel("pacientes"));

    
        paciente.add(linkTo(methodOn(PacienteController.class)
            .crearPaciente(null))
            .withRel("crear"));
    }


}
