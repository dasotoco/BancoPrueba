package com.banco.bancoprueba.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class MovimientoDto {

    private String fecha;
    private String tipoMovimiento;
    private Double valor;
    @NotBlank
    private String cuentaId;
}
