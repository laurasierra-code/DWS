package com.example.logistica.controller;

import com.example.logistica.model.Destino;
import com.example.logistica.service.DestinoService;
import com.example.logistica.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;
    private final VehiculoService vehiculoService;

    public DestinoController(DestinoService destinoService, VehiculoService vehiculoService) {
        this.destinoService = destinoService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("destinos", destinoService.findAll());
        return "destinos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("destino", new Destino());
        model.addAttribute("vehiculos", vehiculoService.findAll());
        return "destinos/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Destino destino) {
        destino.setId(null);
        destinoService.save(destino);
        return "redirect:/destinos";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable int id, Model model) {
        Destino destino = destinoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Destino no encontrado: " + id));
        model.addAttribute("destino", destino);
        return "destinos/detalle";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Destino destino = destinoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Destino no encontrado: " + id));
        model.addAttribute("destino", destino);
        model.addAttribute("vehiculos", vehiculoService.findAll());
        return "destinos/form";
    }

    @PostMapping("/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute Destino destino) {
        destino.setId(id);
        destinoService.save(destino);
        return "redirect:/destinos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable int id) {
        destinoService.deleteById(id);
        return "redirect:/destinos";
    }
}
