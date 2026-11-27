package com.example.logistica.service;

import com.example.logistica.dao.ParadaDao;
import com.example.logistica.dto.ParadaDTO;
import com.example.logistica.model.Parada;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {

    private final ParadaDao paradaDao;

    public ParadaService(ParadaDao paradaDao) {
        this.paradaDao = paradaDao;
    }

    public List<ParadaDTO> findAll() {
        // TODO EXAMEN (Tarea 2): Delegar en ParadaDao.
        return paradaDao.findAll();
    }

    public Optional<ParadaDTO> findById(int id) {
        // TODO EXAMEN (Tarea 2): Delegar en ParadaDao.
        return paradaDao.findById(id);
    }

    public void insert(Parada parada) {
        // TODO EXAMEN (Tarea 2): Delegar en ParadaDao.
        paradaDao.insert(parada);
    }
}
