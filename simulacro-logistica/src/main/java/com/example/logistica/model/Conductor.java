package com.example.logistica.model;

import lombok.Data;

@Data
public class Conductor {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private Integer vehiculoId;
    private String vehiculoMatricula;

    public String getNombreCompleto() {
        StringBuilder sb = new StringBuilder();
        if (nombre != null) sb.append(nombre);
        if (apellido1 != null) sb.append(" ").append(apellido1);
        if (apellido2 != null && !apellido2.isBlank()) sb.append(" ").append(apellido2);
        return sb.toString().trim();
    }
}
