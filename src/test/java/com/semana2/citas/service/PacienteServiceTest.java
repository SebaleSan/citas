package com.semana2.citas.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.semana2.citas.dto.PacienteRequestDTO;
import com.semana2.citas.dto.PacienteResponseDTO;
import com.semana2.citas.entity.PacienteEntity;
import com.semana2.citas.repository.PacienteRepository;

import jakarta.inject.Inject;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias - PacienteService")
class PacienteServiceTest {


    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    private PacienteEntity pacienteEntity;
    private PacienteRequestDTO pacienteRequestDTO;

    @BeforeEach
    void setUp() {
        pacienteEntity = new PacienteEntity();
        pacienteEntity.setIdPaciente(1L);
        pacienteEntity.setNombre("Ana");
        pacienteEntity.setApellido("Gomez");
        pacienteEntity.setRut("87654321-0");
        pacienteEntity.setEdad(30);

        pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Ana");
        pacienteRequestDTO.setApellido("Gomez");
        pacienteRequestDTO.setRut("87654321-0");
        pacienteRequestDTO.setEdad(30);
    }

    @Test
    @DisplayName("Deberia obtener todos los pacientes")
    void deberiaObtenerTodosLosPacientes() {
        PacienteEntity paciente2 = new PacienteEntity();
        paciente2.setIdPaciente(2L);
        paciente2.setNombre("Luis");
        paciente2.setApellido("Martinez");
        paciente2.setRut("12345678-9");
        paciente2.setEdad(25);

        when(pacienteRepository.findAll()).thenReturn(Arrays.asList(pacienteEntity, paciente2));

        List<PacienteResponseDTO> pacientes = pacienteService.obtenerTodos();

        assertNotNull(pacientes);
        assertEquals(2, pacientes.size());
        assertEquals("Ana", pacientes.get(0).getNombre());
        assertEquals("Luis", pacientes.get(1).getNombre());
        verify(pacienteRepository, times(1)).findAll();
    
    }

    @Test
    @DisplayName("Deberia obtener paciente por id")
    void deberiaObtenerPacientePorId() {
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(pacienteEntity));

        PacienteResponseDTO paciente = pacienteService.obtenerPorId(1L);

        assertNotNull(paciente);
        assertEquals("Ana", paciente.getNombre());
    }

    @Test
    @DisplayName("Deberia crear paciente")
    void deberiaCrearPaciente() {
        when(pacienteRepository.save(any(PacienteEntity.class))).thenReturn(pacienteEntity);

        PacienteResponseDTO pacienteCreado = pacienteService.crearPaciente(pacienteRequestDTO);

        assertNotNull(pacienteCreado);
        assertEquals("Ana", pacienteCreado.getNombre());
        assertEquals(30, pacienteCreado.getEdad());
        assertEquals("87654321-0", pacienteCreado.getRut());
        verify(pacienteRepository, times(1)).save(any(PacienteEntity.class));
    }













}



