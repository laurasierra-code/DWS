package com.example.logistica.model;

import lombok.Data;

@Data
public class Vehiculo {
    private Integer id;
    private String matricula;
    private String marca;
    private String modelo;
    private Integer capacidadKg;
}
