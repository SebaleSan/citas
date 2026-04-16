package com.semana2.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoResponseDTO {
    
    private Long idMedico;
    private String rut;
    private String nombre;
    private String apellido;
    private String especialidad;

    
}
