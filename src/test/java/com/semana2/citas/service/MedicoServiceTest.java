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

import com.semana2.citas.dto.MedicoRequestDTO;
import com.semana2.citas.dto.MedicoResponseDTO;
import com.semana2.citas.entity.MedicoEntity;
import com.semana2.citas.repository.MedicoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias - MedicoService")
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    private MedicoEntity medicoEntity;
    private MedicoRequestDTO medicoRequestDTO;
    

    @BeforeEach
    void setUp() {
        medicoEntity = new MedicoEntity();
        medicoEntity.setIdMedico(1L);
        medicoEntity.setNombre("Juan");
        medicoEntity.setApellido("Perez");
        medicoEntity.setRut("12345678-9");
        medicoEntity.setEspecialidad("Cardiologo");

        medicoRequestDTO = new MedicoRequestDTO();
        medicoRequestDTO.setNombre("Juan");
        medicoRequestDTO.setApellido("Perez");
        medicoRequestDTO.setRut("12345678-9");
        medicoRequestDTO.setEspecialidad("Cardiologo");

    }

    @Test
    @DisplayName("Deberia obtener todos los medicos")
    void deberiaObtenerTodosLosMedicos()
    {
        MedicoEntity medico2 = new MedicoEntity();
        medico2.setIdMedico(2L);
        medico2.setNombre("Maria");
        medico2.setApellido("Gonzalez");
        medico2.setRut("98765432-1");
        medico2.setEspecialidad("Pediatra");
        
        when(medicoRepository.findAll()).thenReturn(Arrays.asList(medicoEntity, medico2));

        List<MedicoResponseDTO> medicos = medicoService.obtenerTodos();

        assertNotNull(medicos);
        assertEquals(2, medicos.size());
        assertEquals("Juan", medicos.get(0).getNombre());
        assertEquals("Maria", medicos.get(1).getNombre());
        verify(medicoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Deberia crear medico")
    void deberiaCrearMedico()
    {
        when(medicoRepository.save(any(MedicoEntity.class))).thenReturn(medicoEntity);

        MedicoResponseDTO medicoCreado = medicoService.crearMedico(medicoRequestDTO);

        assertNotNull(medicoCreado);
        assertEquals("Juan", medicoCreado.getNombre());
        assertEquals("Cardiologo", medicoCreado.getEspecialidad());
        assertEquals("12345678-9", medicoCreado.getRut());
        verify(medicoRepository, times(1)).save(any(MedicoEntity.class));
    }

    @Test
    @DisplayName("Deberia obtener medico por id")
    void deberiaObtenerMedicoPorId()
    {
        when(medicoRepository.findById(1L)).thenReturn(Optional.of(medicoEntity));

        MedicoResponseDTO medico = medicoService.obtenerPorId(1L);

        assertNotNull(medico);
        assertEquals("Juan", medico.getNombre());
        assertEquals("Cardiologo", medico.getEspecialidad());
        assertEquals("12345678-9", medico.getRut());
        verify(medicoRepository, times(1)).findById(1L);
    }

}
