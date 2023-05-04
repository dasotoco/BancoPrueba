package com.banco.bancoprueba.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class CuentaDto {
    @NotBlank
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private Boolean estado;
    @NotBlank
    private UUID idCliente;
}
