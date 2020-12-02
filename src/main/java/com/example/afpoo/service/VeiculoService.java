package com.example.afpoo.service;

import java.util.List;
import java.util.Optional;

import com.example.afpoo.dto.VeiculoDTO;
import com.example.afpoo.model.Veiculo;
import com.example.afpoo.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo fromDTO(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setValorDiaria(dto.getValorDiaria());
        veiculo.setModelo(dto.getModelo());
        return veiculo;
    }

    public List<Veiculo> getVeiculos() {
        return veiculoRepository.getVeiculos();
    }

    public Veiculo getVeiculoByCod(int cod) {
        Optional<Veiculo> op = veiculoRepository.getVeiculoByCod(cod);
        return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não consta!"));
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo update(Veiculo veiculo) {
        getVeiculoByCod(veiculo.getCod());
        return veiculoRepository.update(veiculo);
    }

    public void delete(int cod) {
        veiculoRepository.delete(getVeiculoByCod(cod));
    }
}
