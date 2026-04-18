package com.semana2.citas.entity;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cita_medica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaMedicaEntity {

    @Id
    @Column(name = "id_cita", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(name = "fecha_cita", nullable = false)
    private LocalDate fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private String horaCita;

    
    @Column(name = "fecha_emision", nullable = false)
    private String fechaEmision;


    @PrePersist
    public void prePersist() {
        this.fechaEmision = LocalDate.now().toString();
    }



    @Column(name = "activa", nullable = false)
    private Integer activa;

    @ManyToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "id_medico")
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente"
    )
    private PacienteEntity paciente;




}
