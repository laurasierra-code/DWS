package com.example.logistica.dao;

import com.example.logistica.dto.ParadaDTO;
import com.example.logistica.model.Parada;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ParadaDao {

    private final JdbcTemplate jdbcTemplate;

    public ParadaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TOD EXAMEN (Tarea 2): Completa el RowMapper si necesitas ajustar algún campo.
    private final RowMapper<ParadaDTO> dtoMapper = (rs, rowNum) -> {
        ParadaDTO dto = new ParadaDTO();
        dto.setId(rs.getInt("id"));
        dto.setVehiculoId(rs.getInt("vehiculo_id"));
        dto.setVehiculoMatricula(rs.getString("vehiculo_matricula"));
        dto.setVehiculoDescripcion(rs.getString("vehiculo_descripcion"));
        dto.setDestinoId(rs.getInt("destino_id"));
        dto.setDestinoNombre(rs.getString("destino_nombre"));
        dto.setDestinoCiudad(rs.getString("destino_ciudad"));
        dto.setFechaPrevista(toLocalDate(rs.getDate("fecha_prevista")));
        dto.setFechaLlegada(toLocalDate(rs.getDate("fecha_llegada")));
        dto.setObservaciones(rs.getString("observaciones"));
        return dto;
    };

    private LocalDate toLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    // TOD EXAMEN (Tarea 2): Revisa/completa la consulta SQL con JOIN para resolver nombres.
    private static final String BASE_SELECT =
            "SELECT p.id, p.vehiculo_id, p.destino_id, p.fecha_prevista, p.fecha_llegada, p.observaciones, " +
            "v.matricula AS vehiculo_matricula, CONCAT(v.marca, ' ', v.modelo) AS vehiculo_descripcion, " +
            "d.nombre AS destino_nombre, d.ciudad AS destino_ciudad " +
            "FROM parada p " +
            "JOIN vehiculo v ON p.vehiculo_id = v.id " +
            "JOIN destino d ON p.destino_id = d.id ";

    public List<ParadaDTO> findAll() {
        // TOD EXAMEN (Tarea 2): Usa BASE_SELECT, dtoMapper y añade el ORDER BY adecuado.
        return jdbcTemplate.query(BASE_SELECT+ "ORDER BY p.fecha_prevista, p.id", dtoMapper);
    }

    public Optional<ParadaDTO> findById(int id) {
        // TOD EXAMEN (Tarea 2): Usa BASE_SELECT, dtoMapper y filtra por id.
        List<ParadaDTO> list = jdbcTemplate.query(BASE_SELECT + "WHERE p.id = ?", dtoMapper,id);
        return list.stream().findFirst();
    }

    public void insert(Parada parada) {
        // TOD EXAMEN (Tarea 2): Implementa el INSERT en la tabla parada.
        jdbcTemplate.update(
        "INSERT INTO parada (vehiculo_id, destino_id, fecha_prevista, fecha_llegada, observaciones) VALUES (?, ?, ?, ?, ?)",
        parada.getVehiculoId(), parada.getDestinoId(), parada.getFechaPrevista(), parada.getFechaLlegada(), parada.getObservaciones()
);
    }
}
