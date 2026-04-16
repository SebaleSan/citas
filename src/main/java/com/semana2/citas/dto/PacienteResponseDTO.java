package com.semana2.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Long idPaciente;
    private String rut;
    private String nombre;
    private String apellido;
    private Integer edad;


    
}
