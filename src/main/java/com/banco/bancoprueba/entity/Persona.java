package com.banco.bancoprueba.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
@AllArgsConstructor
public class Persona {

    @Id
    private UUID id;
    public String identificacion;
    public String nombre;
    public String genero;
    public String direccion;
    public int edad;
    public String telefono;

    public Persona() {

    }
}
