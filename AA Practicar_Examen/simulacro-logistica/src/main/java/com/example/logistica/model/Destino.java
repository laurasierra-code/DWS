package com.example.logistica.model;

import lombok.Data;

@Data
public class Destino {
    private Integer id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Integer vehiculoId;
    private String vehiculoMatricula;
}
