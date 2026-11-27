package com.example.logistica.service;

import com.example.logistica.dao.VehiculoDao;
import com.example.logistica.dto.VehiculoListadoDTO;
import com.example.logistica.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoService {
    private final VehiculoDao vehiculoDao;
    public VehiculoService(VehiculoDao vehiculoDao) { this.vehiculoDao = vehiculoDao; }

    public List<VehiculoListadoDTO> findAllConNumeroDestinos() {
        // TODO EXAMEN (Tarea 1): Obtener el listado desde VehiculoDao con el número de destinos.
        // Por ahora se devuelve el listado sin el conteo para que la vista compile.
        return vehiculoDao.findAll().stream().map(v -> {
            VehiculoListadoDTO dto = new VehiculoListadoDTO();
            dto.setId(v.getId());
            dto.setMatricula(v.getMatricula());
            dto.setMarca(v.getMarca());
            dto.setModelo(v.getModelo());
            dto.setCapacidadKg(v.getCapacidadKg());
            dto.setNumeroDestinos(null); // TODO EXAMEN (Tarea 1): completar con el número de destinos
            return dto;
        }).collect(Collectors.toList());
    }

    public List<Vehiculo> findAll() { return vehiculoDao.findAll(); }
    public Optional<Vehiculo> findById(int id) { return vehiculoDao.findById(id); }
    public void save(Vehiculo vehiculo) { vehiculoDao.save(vehiculo); }
    public void deleteById(int id) { vehiculoDao.deleteById(id); }
}
