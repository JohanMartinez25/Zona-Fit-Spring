package gm.zona_fit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

    @GetMapping("/")
    public String inicio() {
        return "¡Bienvenido a la API de Zona Fit!";
    }


}
