package com.example.afpoo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.afpoo.dto.ReservaDTO;
import com.example.afpoo.model.Cliente;
import com.example.afpoo.model.Reserva;
import com.example.afpoo.model.Veiculo;
import com.example.afpoo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    public Reserva fromDTO(ReservaDTO dto, Cliente cliente, Veiculo veiculo) {
        Reserva reserva = new Reserva();
        reserva.setDataInicial(LocalDateTime.now());
        reserva.setDataFinal(dto.getDataFinal());
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        reserva.setValorTotal(reserva.valorTotalReserva());
        return reserva;
    }

    public Reserva save(ReservaDTO reservadto, int codCliente, int codVeiculo) {
        Cliente cliente = clienteService.getClienteByCod(codCliente);
        Veiculo veiculo = veiculoService.getVeiculoByCod(codVeiculo);
        
        Reserva reserva = fromDTO(reservadto, cliente, veiculo);

        cliente.addReserva(reserva);
        veiculo.addReserva(reserva);
        
        return reservaRepository.save(reserva);
    }
    
    public Reserva getReservaByCod(int cod) {
        Optional<Reserva> op = reservaRepository.getReservaByCod(cod);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não consta!"));
    }

    public Reserva getReservaByCodCliente(int cod) {
        Optional<Reserva> op = reservaRepository.getReservaByCodCliente(cod);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não consta!"));
    }

    public Reserva getReservaByCodVeiculo(int cod) {
        Optional<Reserva> op = reservaRepository.getReservaByCodVeiculo(cod);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não consta!"));
    }
}
