package com.semana2.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CitaResponseDTO {

    private String id;
    private String nombrePaciente;
    private String rutPaciente;
    private String nombreMedico;
    private String especialidad;
    private String fecha;
    private String hora;
    private String estado;
    
}
