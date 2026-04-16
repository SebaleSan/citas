package com.semana2.citas.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orden_medica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_cita", nullable = false)
    private String fechaCita;

    @Column(name = "hora_cita", nullable = false)
    private String horaCita;


    @Column(name = "fecha_emision", nullable = false)
    private String fechaEmision;

    @ManyToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "id_medico")
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente"
    )
    private PacienteEntity paciente;

}
