package com.example.logistica.controller;

import com.example.logistica.dto.ParadaDTO;
import com.example.logistica.model.Parada;
import com.example.logistica.service.DestinoService;
import com.example.logistica.service.ParadaService;
import com.example.logistica.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/paradas")
public class ParadaController {

    private final ParadaService paradaService;
    private final VehiculoService vehiculoService;
    private final DestinoService destinoService;

    public ParadaController(ParadaService paradaService, VehiculoService vehiculoService, DestinoService destinoService) {
        this.paradaService = paradaService;
        this.vehiculoService = vehiculoService;
        this.destinoService = destinoService;
    }

    @GetMapping
    public String list(Model model) {
        // TODO EXAMEN (Tarea 2): Obtener el listado de ParadaDTO desde el servicio y añadirlo al modelo.
        model.addAttribute("paradas", paradaService.findAll());
        return "paradas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        // TODO EXAMEN (Tarea 2): Preparar el formulario de alta (objeto Parada y combos de vehículos/destinos).
        model.addAttribute("parada", new Parada());
        cargarCombos(model);
        return "paradas/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Parada parada) {
        // TODO EXAMEN (Tarea 2): Guardar la parada usando el servicio y redirigir al listado.
        paradaService.insert(parada);
        return "redirect:/paradas";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable int id, Model model) {
        // TODO EXAMEN (Tarea 2): Obtener el ParadaDTO por id y pasarlo a la vista.
        ParadaDTO dto = paradaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Parada no encontrada"));
        model.addAttribute("parada", dto);
        return "paradas/detalle";
    }

    private void cargarCombos(Model model) {
        // TODO EXAMEN (Tarea 2): Cargar vehículos y destinos para los selects del formulario.
        model.addAttribute("vehiculos", vehiculoService.findAll());
        model.addAttribute("destinos", destinoService.findAll());
    }
}
