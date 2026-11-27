package com.example.logistica.dao;

import com.example.logistica.model.Destino;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DestinoDao {

    private final JdbcTemplate jdbcTemplate;

    public DestinoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Destino> mapper = (rs, rowNum) -> {
        Destino d = new Destino();
        d.setId(rs.getInt("id"));
        d.setNombre(rs.getString("nombre"));
        d.setDireccion(rs.getString("direccion"));
        d.setCiudad(rs.getString("ciudad"));
        int vehiculoId = rs.getInt("vehiculo_id");
        d.setVehiculoId(rs.wasNull() ? null : vehiculoId);
        try { d.setVehiculoMatricula(rs.getString("vehiculo_matricula")); } catch (Exception ignored) {}
        return d;
    };

    private static final String BASE_SELECT =
            "SELECT d.id, d.nombre, d.direccion, d.ciudad, d.vehiculo_id, v.matricula AS vehiculo_matricula " +
            "FROM destino d LEFT JOIN vehiculo v ON d.vehiculo_id = v.id ";

    public List<Destino> findAll() {
        return jdbcTemplate.query(BASE_SELECT + "ORDER BY d.ciudad, d.nombre", mapper);
    }

    public Optional<Destino> findById(int id) {
        List<Destino> list = jdbcTemplate.query(BASE_SELECT + "WHERE d.id = ?", mapper, id);
        return list.stream().findFirst();
    }

    public void save(Destino d) {
        if (d.getId() == null) {
            jdbcTemplate.update("INSERT INTO destino (nombre, direccion, ciudad, vehiculo_id) VALUES (?, ?, ?, ?)",
                    d.getNombre(), d.getDireccion(), d.getCiudad(), d.getVehiculoId());
        } else {
            jdbcTemplate.update("UPDATE destino SET nombre = ?, direccion = ?, ciudad = ?, vehiculo_id = ? WHERE id = ?",
                    d.getNombre(), d.getDireccion(), d.getCiudad(), d.getVehiculoId(), d.getId());
        }
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM destino WHERE id = ?", id);
    }
}
