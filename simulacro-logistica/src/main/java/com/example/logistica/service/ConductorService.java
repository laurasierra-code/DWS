package com.example.logistica.service;

import com.example.logistica.dao.ConductorDao;
import com.example.logistica.model.Conductor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {
    private final ConductorDao conductorDao;
    public ConductorService(ConductorDao conductorDao) { this.conductorDao = conductorDao; }

    public List<Conductor> findAll() { return conductorDao.findAll(); }
    public Optional<Conductor> findById(int id) { return conductorDao.findById(id); }
    public void save(Conductor conductor) { conductorDao.save(conductor); }
    public void deleteById(int id) { conductorDao.deleteById(id); }
}
