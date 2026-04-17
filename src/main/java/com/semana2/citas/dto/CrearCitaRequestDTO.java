package com.semana2.citas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearCitaRequestDTO {

   private String rutPaciente;
    private String rutMedico;
    private String fechaCita;
    private String horaCita;
    
}
