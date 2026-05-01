package com.semana2.citas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.semana2.citas.repository.PacienteRepository;
import com.semana2.citas.dto.PacienteRequestDTO;
import com.semana2.citas.dto.PacienteResponseDTO;
import com.semana2.citas.entity.PacienteEntity;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> obtenerTodos() {
        return pacienteRepository.findAll().stream().map(this::toDTO).toList();
    }

    private PacienteResponseDTO toDTO(PacienteEntity paciente) {
        return new PacienteResponseDTO(
            paciente.getIdPaciente(),
            paciente.getRut(),
            paciente.getNombre(),
            paciente.getApellido(),
            paciente.getEdad()
        );
    }


    public PacienteResponseDTO crearPaciente(PacienteRequestDTO pacienteRequest) {

		PacienteEntity paciente = new PacienteEntity();
		paciente.setRut(pacienteRequest.getRut());
		paciente.setNombre(pacienteRequest.getNombre());
		paciente.setApellido(pacienteRequest.getApellido());
		paciente.setEdad(pacienteRequest.getEdad());


		PacienteEntity pacienteGuardado = pacienteRepository.save(paciente);
		return toDTO(pacienteGuardado);
	}

    public PacienteResponseDTO obtenerPorId(Long id) {

		Optional<PacienteEntity> paciente = pacienteRepository.findById(id);
		return paciente.map(this::toDTO).orElse(null);
	}



    
    
}
