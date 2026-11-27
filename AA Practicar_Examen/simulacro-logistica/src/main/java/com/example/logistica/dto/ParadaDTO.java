package com.example.logistica.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ParadaDTO {
    // TODO EXAMEN (Tarea 2): DTO para pasar datos de parada a las vistas.
    // Campos requeridos por el enunciado:
    private Integer id;
    private Integer vehiculoId;
    private String vehiculoMatricula;
    private String vehiculoDescripcion;
    private Integer destinoId;
    private String destinoNombre;
    private String destinoCiudad;
    private LocalDate fechaPrevista;
    private LocalDate fechaLlegada;
    private String observaciones;
}
