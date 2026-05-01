package com.semana2.citas.dto;

import org.springframework.hateoas.RepresentationModel;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CitaResponseDTO extends RepresentationModel<CitaResponseDTO> {

   private Long id;
   private LocalDate fechaCita;
   private String horaCita;
   private String fechaEmision;
   private Integer activa;
   private String rutMedico;
   private String rutPaciente;
   private String especialidadMedico;
    
}
