package com.semana2.citas.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    
    private String fechaCita;


    @NotBlank(message = "La hora de la cita es obligatoria")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "La hora debe estar en formato HH:mm")
    private String horaCita;

    @NotNull(message = "El rut del médico es obligatorio")
    private String rutMedico;

    @NotNull(message = "El rut del paciente es obligatorio")
    private String rutPaciente;
}
