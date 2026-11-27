package com.example.logistica.controller;

import com.example.logistica.model.Conductor;
import com.example.logistica.service.ConductorService;
import com.example.logistica.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/conductores")
public class ConductorController {

    private final ConductorService conductorService;
    private final VehiculoService vehiculoService;

    public ConductorController(ConductorService conductorService, VehiculoService vehiculoService) {
        this.conductorService = conductorService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("conductores", conductorService.findAll());
        return "conductores/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("conductor", new Conductor());
        model.addAttribute("vehiculos", vehiculoService.findAll());
        return "conductores/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Conductor conductor) {
        conductor.setId(null);
        conductorService.save(conductor);
        return "redirect:/conductores";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable int id, Model model) {
        Conductor conductor = conductorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado: " + id));
        model.addAttribute("conductor", conductor);
        return "conductores/detalle";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Conductor conductor = conductorService.findById(id).orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado: " + id));
        model.addAttribute("conductor", conductor);
        model.addAttribute("vehiculos", vehiculoService.findAll());
        return "conductores/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute Conductor conductor) {
        conductor.setId(id);
        conductorService.save(conductor);
        return "redirect:/conductores";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable int id) {
        conductorService.deleteById(id);
        return "redirect:/conductores";
    }
}
