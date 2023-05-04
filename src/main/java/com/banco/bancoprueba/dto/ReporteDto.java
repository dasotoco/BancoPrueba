package com.banco.bancoprueba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteDto {

    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private Boolean estado;
    private String tipoMovimiento;
    private Double saldoInicial;
    private Double movimiento;
    private Double saldoDisponible;

}
