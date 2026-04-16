package com.semana2.citas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

		return ResponseEntity.ok(pacienteService.obtenerTodos());
	}
 
    
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@Valid @RequestBody PacienteRequestDTO pacienteRequest) {

        PacienteResponseDTO pacienteCreado = pacienteService.crearPaciente(pacienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCreado);
    }
    

}
