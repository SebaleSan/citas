package com.semana2.citas.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PacienteResponseDTO extends RepresentationModel<PacienteResponseDTO> {

    private Long idPaciente;
    private String rut;
    private String nombre;
    private String apellido;
    private Integer edad;


    
}
