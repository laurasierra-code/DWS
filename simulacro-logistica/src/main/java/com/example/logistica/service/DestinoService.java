package com.example.logistica.service;

import com.example.logistica.dao.DestinoDao;
import com.example.logistica.model.Destino;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    private final DestinoDao destinoDao;
    public DestinoService(DestinoDao destinoDao) { this.destinoDao = destinoDao; }

    public List<Destino> findAll() { return destinoDao.findAll(); }
    public Optional<Destino> findById(int id) { return destinoDao.findById(id); }
    public void save(Destino destino) { destinoDao.save(destino); }
    public void deleteById(int id) { destinoDao.deleteById(id); }
}
