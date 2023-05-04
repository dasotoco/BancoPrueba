package com.banco.bancoprueba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="cliente")
@PrimaryKeyJoinColumn(name="clienteId")
@Data
@SuperBuilder
public class Cliente extends Persona {

    private int contrasena;
    private Boolean estado;

    public Cliente() {
        super();
    }
}