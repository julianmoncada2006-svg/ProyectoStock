package com.inventario.sistema_inventario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InventarioController {

    private List<Producto> productos = new ArrayList<>();
    private int siguienteId = 1;

    public InventarioController() {
        productos.add(new Producto(
            siguienteId++, "Teclado", 10, 45000
        ));

        productos.add(new Producto(
            siguienteId++, "Mouse", 15, 25000
        ));
    }

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute("productos", productos);

        double total = 0;

        for (Producto p : productos) {
            total += p.getCantidad() * p.getPrecio();
        }

        model.addAttribute("total", total);

        return "index";
    }

    @PostMapping("/agregar")
    public String agregar(
        @RequestParam String nombre,
        @RequestParam int cantidad,
        @RequestParam double precio
    ) {

        productos.add(new Producto(
            siguienteId++, nombre, cantidad, precio
        ));

        return "redirect:/";
    }
}