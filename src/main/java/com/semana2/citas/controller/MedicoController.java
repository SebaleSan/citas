package com.semana2.citas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semana2.citas.dto.MedicoRequestDTO;
import com.semana2.citas.dto.MedicoResponseDTO;
import com.semana2.citas.service.MedicoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    
    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(medicoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> crearMedico(@Valid @RequestBody MedicoRequestDTO medicoRequest) {
        MedicoResponseDTO medicoCreado = medicoService.crearMedico(medicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCreado);
    } 

    
    
}
