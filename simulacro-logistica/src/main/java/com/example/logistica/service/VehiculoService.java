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

    public VehiculoService(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }

    public List<VehiculoListadoDTO> findAllConNumeroDestinos() {
        return vehiculoDao.findAllConNumeroDestinos();
    }

    public List<Vehiculo> findAll() {
        return vehiculoDao.findAll();
    }

    public Optional<Vehiculo> findById(int id) {
        return vehiculoDao.findById(id);
    }

    public void save(Vehiculo vehiculo) {
        vehiculoDao.save(vehiculo);
    }

    public void deleteById(int id) {
        vehiculoDao.deleteById(id);
    }
}
