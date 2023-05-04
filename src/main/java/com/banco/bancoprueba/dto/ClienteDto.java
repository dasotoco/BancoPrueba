package com.banco.bancoprueba.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDto {
    private int contrasena;
    private Boolean estado;
    private String identificacion;
    private String nombre;
    private String genero;
    private String direccion;
    private int edad;
    private String telefono;
}
