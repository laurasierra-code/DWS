package com.ejemplo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador MVC para gestionar las vistas de la aplicación con Thymeleaf.
 */
@Controller //devuelve vistas HTML en lugar de texto plano
public class HolaViewController {

    /**
     * Renderiza la plantilla HTML accesible en http://localhost:8080/holaVistahttp://localhost:8080/holaVista
     */
    @GetMapping("/holaVista")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Módulo DWES - Demo");
        model.addAttribute("mensaje", "¡Bienvenido a la aplicación web desarrollada con Spring Boot y Thymeleaf!");
        
        // Devuelve el archivo src/main/resources/templates/holaVista.html (sin la extensión .html)
        return "holaVista"; 
    }
}


