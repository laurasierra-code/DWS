package com.ejemplo.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController //devuelve texto plano en lugar de una vista HTML
public class HolaController {


   @GetMapping("/hola")
   public String holaMundo() {
       return "¡Proyecto Spring Boot funcionando correctamente en VS Code!";
   }
}

