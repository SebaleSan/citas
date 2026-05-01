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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.semana2.citas.dto.CitaResponseDTO;
import com.semana2.citas.dto.CrearCitaRequestDTO;
import com.semana2.citas.entity.CitaMedicaEntity;
import com.semana2.citas.entity.MedicoEntity;
import com.semana2.citas.entity.PacienteEntity;
import com.semana2.citas.repository.CitaMedicaRepository;
import com.semana2.citas.repository.MedicoRepository;
import com.semana2.citas.repository.PacienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias - CitaService")
class CitaServiceTest {

    @Mock 
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;


    @InjectMocks
    private CitaService citaService;

    private CitaMedicaEntity citaMedicaEntity;
    private CrearCitaRequestDTO crearCitaRequestDTO;
    private MedicoEntity medicoEntity;
    private PacienteEntity pacienteEntity;

    

    


    @BeforeEach
    void setUp() {
        medicoEntity = new MedicoEntity();
        medicoEntity.setIdMedico(1L);
        medicoEntity.setNombre("Juan");
        medicoEntity.setApellido("Perez");
        medicoEntity.setRut("12345678-9");
        medicoEntity.setEspecialidad("Cardiologo");

        pacienteEntity = new PacienteEntity();
        pacienteEntity.setIdPaciente(1L);
        pacienteEntity.setNombre("Maria");
        pacienteEntity.setApellido("Gonzalez");
        pacienteEntity.setRut("98765432-1");
        pacienteEntity.setEdad(30);
        

        citaMedicaEntity = new CitaMedicaEntity();
        citaMedicaEntity.setIdCita(1L);
        citaMedicaEntity.setFechaCita(LocalDate.parse("2023-10-26"));
        citaMedicaEntity.setHoraCita("10:00");
        citaMedicaEntity.setMedico(medicoEntity);
        citaMedicaEntity.setPaciente(pacienteEntity);
        citaMedicaEntity.setActiva(1);

        crearCitaRequestDTO = new CrearCitaRequestDTO();
        crearCitaRequestDTO.setFechaCita(LocalDate.parse("2023-10-26"));
        crearCitaRequestDTO.setHoraCita("10:00");
        crearCitaRequestDTO.setRutMedico("12345678-9");
        crearCitaRequestDTO.setRutPaciente("98765432-1");
    }

    @Test
    @DisplayName("Deberia obtener todas las citas")
    void deberiaObtenerTodasLasCitas()
    {
        CitaMedicaEntity cita2 = new CitaMedicaEntity();
        cita2.setIdCita(2L);
        cita2.setFechaCita(LocalDate.parse("2023-10-27"));
        cita2.setHoraCita("11:00");
        cita2.setMedico(medicoEntity);
        cita2.setPaciente(pacienteEntity);
        cita2.setActiva(1);

        when(citaMedicaRepository.findAll()).thenReturn(Arrays.asList(citaMedicaEntity, cita2));

        List<CitaResponseDTO> citas = citaService.obtenerTodas();

        assertNotNull(citas);
        assertEquals(2, citas.size());
        assertEquals(LocalDate.parse("2023-10-26"), citas.get(0).getFechaCita());
        assertEquals(LocalDate.parse("2023-10-27"), citas.get(1).getFechaCita());
        verify(citaMedicaRepository, times(1)).findAll();


    }

    @Test
    @DisplayName("Deberia crear cita")
    void deberiaCrearCita()
    {
        when(medicoRepository.findByRut("12345678-9")).thenReturn(Optional.of(medicoEntity));
        when(pacienteRepository.findByRut("98765432-1")).thenReturn(Optional.of(pacienteEntity));
        when(citaMedicaRepository.findByMedicoIdMedico(1L)).thenReturn(Collections.emptyList());
        when(citaMedicaRepository.save(any(CitaMedicaEntity.class))).thenReturn(citaMedicaEntity);

    
        CitaResponseDTO citaCreada = citaService.crear(crearCitaRequestDTO);

        assertNotNull(citaCreada);
        assertEquals(LocalDate.parse("2023-10-26"), citaCreada.getFechaCita());
        assertEquals("10:00", citaCreada.getHoraCita());
        assertEquals("12345678-9", citaCreada.getRutMedico());
        assertEquals("98765432-1", citaCreada.getRutPaciente());
        verify(citaMedicaRepository, times(1)).save(any(CitaMedicaEntity.class));


    }


}
