package com.semana2.citas.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadResponseDTO {

    private String nombreMedico;
    private String fecha;
    private List<String> horariosDisponibles;
}