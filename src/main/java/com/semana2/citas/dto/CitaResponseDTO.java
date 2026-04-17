package com.semana2.citas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponseDTO {

   private Long id;
   private LocalDate fechaCita;
   private String horaCita;
   private String fechaEmision;
   private Integer activa;
   private Long medico;
   private Long paciente;
    
}
