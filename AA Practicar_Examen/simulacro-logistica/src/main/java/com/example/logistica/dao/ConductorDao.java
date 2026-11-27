package com.example.logistica.dao;

import com.example.logistica.model.Conductor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConductorDao {

    private final JdbcTemplate jdbcTemplate;

    public ConductorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Conductor> mapper = (rs, rowNum) -> {
        Conductor c = new Conductor();
        c.setId(rs.getInt("id"));
        c.setDni(rs.getString("dni"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido1(rs.getString("apellido1"));
        c.setApellido2(rs.getString("apellido2"));
        c.setTelefono(rs.getString("telefono"));
        int vehiculoId = rs.getInt("vehiculo_id");
        c.setVehiculoId(rs.wasNull() ? null : vehiculoId);
        try { c.setVehiculoMatricula(rs.getString("vehiculo_matricula")); } catch (Exception ignored) {}
        return c;
    };

    private static final String BASE_SELECT =
            "SELECT c.id, c.dni, c.nombre, c.apellido1, c.apellido2, c.telefono, c.vehiculo_id, v.matricula AS vehiculo_matricula " +
            "FROM conductor c LEFT JOIN vehiculo v ON c.vehiculo_id = v.id ";

    public List<Conductor> findAll() {
        return jdbcTemplate.query(BASE_SELECT + "ORDER BY c.apellido1, c.apellido2, c.nombre", mapper);
    }

    public Optional<Conductor> findById(int id) {
        List<Conductor> list = jdbcTemplate.query(BASE_SELECT + "WHERE c.id = ?", mapper, id);
        return list.stream().findFirst();
    }

    public void save(Conductor c) {
        if (c.getId() == null) {
            jdbcTemplate.update("INSERT INTO conductor (dni, nombre, apellido1, apellido2, telefono, vehiculo_id) VALUES (?, ?, ?, ?, ?, ?)",
                    c.getDni(), c.getNombre(), c.getApellido1(), c.getApellido2(), c.getTelefono(), c.getVehiculoId());
        } else {
            jdbcTemplate.update("UPDATE conductor SET dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, telefono = ?, vehiculo_id = ? WHERE id = ?",
                    c.getDni(), c.getNombre(), c.getApellido1(), c.getApellido2(), c.getTelefono(), c.getVehiculoId(), c.getId());
        }
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM conductor WHERE id = ?", id);
    }
}
