package com.semana2.citas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.semana2.citas.dto.MedicoResponseDTO;
import com.semana2.citas.dto.PacienteResponseDTO;
import com.semana2.citas.entity.MedicoEntity;
import com.semana2.citas.entity.PacienteEntity;
import com.semana2.citas.repository.MedicoRepository;
import com.semana2.citas.dto.MedicoRequestDTO;



@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<MedicoResponseDTO> obtenerTodos() {
        return medicoRepository.findAll().stream().map(this::toDTO).toList();
    }

    private MedicoResponseDTO toDTO(MedicoEntity medico) {
        return new MedicoResponseDTO(
            medico.getIdMedico(),
            medico.getRut(),
            medico.getNombre(),
            medico.getApellido(),
            medico.getEspecialidad()
        );
    }

    public MedicoResponseDTO obtenerPorId(Long id) {

		Optional<MedicoEntity> medico = medicoRepository.findById(id);
		return medico.map(this::toDTO).orElse(null);
	}


    public MedicoResponseDTO crearMedico(MedicoRequestDTO medicoRequest) {

		MedicoEntity medico = new MedicoEntity();
		medico.setRut(medicoRequest.getRut());
		medico.setNombre(medicoRequest.getNombre());
		medico.setApellido(medicoRequest.getApellido());
		medico.setEspecialidad(medicoRequest.getEspecialidad());


		MedicoEntity medicoGuardado = medicoRepository.save(medico);
		return toDTO(medicoGuardado);
	}

  

    
    
}
