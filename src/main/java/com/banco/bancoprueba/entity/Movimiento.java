package com.banco.bancoprueba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="movimiento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimiento {

    @Id
    private UUID id;
    private String fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldoDisponible;
    private Double saldoInicial;
    private String cuentaId;

}
