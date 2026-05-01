package com.semana2.citas.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MedicoResponseDTO extends RepresentationModel<MedicoResponseDTO> {
    
    private Long idMedico;
    private String rut;
    private String nombre;
    private String apellido;
    private String especialidad;

    
}
