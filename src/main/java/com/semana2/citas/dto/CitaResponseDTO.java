package com.semana2.citas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaResponseDTO {

   private Long id;
   private String fechaCita;
   private String horaCita;
   private String fechaEmision;
   private Integer activa;
   private Long medico;
   private Long paciente;
    
}
