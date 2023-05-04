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
@Table(name="cuenta")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    private String numeroCuenta;
    private String tipoCuenta;
    private Double saldo;
    private Boolean estado;
    private UUID clienteId;

}
