package com.example.logistica.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Parada {
    private Integer id;
    private Integer vehiculoId;
    private Integer destinoId;
    private LocalDate fechaPrevista;
    private LocalDate fechaLlegada;
    private String observaciones;
}
