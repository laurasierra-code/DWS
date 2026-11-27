package com.example.logistica.dao;

import com.example.logistica.model.Vehiculo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehiculoDao {

    private final JdbcTemplate jdbcTemplate;

    public VehiculoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Vehiculo> mapper = (rs, rowNum) -> {
        Vehiculo v = new Vehiculo();
        v.setId(rs.getInt("id"));
        v.setMatricula(rs.getString("matricula"));
        v.setMarca(rs.getString("marca"));
        v.setModelo(rs.getString("modelo"));
        v.setCapacidadKg(rs.getInt("capacidad_kg"));
        return v;
    };

    // TODO EXAMEN (Tarea 1): Añadir método que devuelva la lista de vehículos con el número de destinos.
    // Consulta orientativa:
    // SELECT v.id, v.matricula, v.marca, v.modelo, v.capacidad_kg, COUNT(d.id) AS numero_destinos
    // FROM vehiculo v LEFT JOIN destino d ON v.id = d.vehiculo_id
    // GROUP BY v.id, v.matricula, v.marca, v.modelo, v.capacidad_kg
    // ORDER BY v.matricula

    public List<Vehiculo> findAll() {
        return jdbcTemplate.query("SELECT * FROM vehiculo ORDER BY matricula", mapper);
    }

    public Optional<Vehiculo> findById(int id) {
        List<Vehiculo> list = jdbcTemplate.query("SELECT * FROM vehiculo WHERE id = ?", mapper, id);
        return list.stream().findFirst();
    }

    public void save(Vehiculo v) {
        if (v.getId() == null) {
            jdbcTemplate.update("INSERT INTO vehiculo (matricula, marca, modelo, capacidad_kg) VALUES (?, ?, ?, ?)",
                    v.getMatricula(), v.getMarca(), v.getModelo(), v.getCapacidadKg());
        } else {
            jdbcTemplate.update("UPDATE vehiculo SET matricula = ?, marca = ?, modelo = ?, capacidad_kg = ? WHERE id = ?",
                    v.getMatricula(), v.getMarca(), v.getModelo(), v.getCapacidadKg(), v.getId());
        }
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM vehiculo WHERE id = ?", id);
    }
}
