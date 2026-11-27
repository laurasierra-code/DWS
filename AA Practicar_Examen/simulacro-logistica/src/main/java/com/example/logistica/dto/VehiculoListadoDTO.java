package com.example.logistica.dto;

import lombok.Data;

@Data
public class VehiculoListadoDTO {
    // TODO EXAMEN (Tarea 1): DTO para el listado de vehículos.
    // Campos requeridos: id, matricula, marca, modelo, capacidadKg, numeroDestinos
    private Integer id;
    private String matricula;
    private String marca;
    private String modelo;
    private Integer capacidadKg;
    private Long numeroDestinos;
}
