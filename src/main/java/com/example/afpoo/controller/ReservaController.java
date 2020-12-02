package com.example.afpoo.controller;

import com.example.afpoo.model.Reserva;
import com.example.afpoo.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @GetMapping("/{cod}")
    public ResponseEntity<Reserva> getReservaByCod(@PathVariable int cod) {
        Reserva reserva = reservaService.getReservaByCod(cod);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/clientes/{cod}")
    public ResponseEntity<Reserva> getReservaByCliente(@PathVariable int cod) {
        Reserva reserva = reservaService.getReservaByCodCliente(cod);  
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/veiculos/{cod}")
    public ResponseEntity<Reserva> getReservaByVeiculo(@PathVariable int cod) {
        Reserva reserva = reservaService.getReservaByCodVeiculo(cod);
        return ResponseEntity.ok(reserva);
    }
}
