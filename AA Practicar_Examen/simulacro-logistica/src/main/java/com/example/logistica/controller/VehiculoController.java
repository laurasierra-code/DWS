package com.example.logistica.controller;

import com.example.logistica.model.Vehiculo;
import com.example.logistica.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    public VehiculoController(VehiculoService vehiculoService) { this.vehiculoService = vehiculoService; }

    @GetMapping
    public String list(Model model) {
        // TODO EXAMEN (Tarea 1): Asegurar que el listado usa el DTO con numeroDestinos.
        model.addAttribute("vehiculos", vehiculoService.findAllConNumeroDestinos());
        return "vehiculos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Vehiculo vehiculo) {
        vehiculo.setId(null);
        vehiculoService.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable int id, Model model) {
        Vehiculo vehiculo = vehiculoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado: " + id));
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculos/detalle";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Vehiculo vehiculo = vehiculoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado: " + id));
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute Vehiculo vehiculo) {
        vehiculo.setId(id);
        vehiculoService.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable int id) {
        vehiculoService.deleteById(id);
        return "redirect:/vehiculos";
    }
}
