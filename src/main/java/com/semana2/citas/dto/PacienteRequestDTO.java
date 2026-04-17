package com.semana2.citas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotBlank(message = "El rut es obligatorio")
    private String rut;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1, message = "La edad no puede ser negativa o 0")
    private Integer edad;
}
