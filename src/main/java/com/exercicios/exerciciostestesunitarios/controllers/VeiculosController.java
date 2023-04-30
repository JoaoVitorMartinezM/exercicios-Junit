package com.exercicios.exerciciostestesunitarios.controllers;

import com.exercicios.exerciciostestesunitarios.dto.MultaResponse;
import com.exercicios.exerciciostestesunitarios.dto.VeiculoRequest;
import com.exercicios.exerciciostestesunitarios.exceptions.VeiculoNaoEncontradoException;
import com.exercicios.exerciciostestesunitarios.models.Veiculos;
import com.exercicios.exerciciostestesunitarios.services.VeiculoService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/veiculos")
public class VeiculosController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<Veiculos>> findAll() {
        List<Veiculos> veiculos = service.findAll();

        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("{placa}")
    ResponseEntity findByPlaca(@PathVariable("placa") String placa) throws Exception {


        try{
            Veiculos veiculo = service.findByPlaca(placa);
            return ResponseEntity.ok(veiculo);


        }catch (VeiculoNaoEncontradoException e){
            log.error("Exceção lançada: {}", e.getMessage());

        }
        return ResponseEntity.notFound().build();


    }

    @PostMapping
    public ResponseEntity<Veiculos> save(@RequestBody VeiculoRequest request){
        log.debug("Dados da request: {}", request);
        Veiculos parse = mapper.map(request, Veiculos.class);
        Veiculos veiculo = service.save(parse);
        return ResponseEntity.created(URI.create(veiculo.getPlaca().toString())).body(veiculo);
    }

    @DeleteMapping("{placa}")
    public void deleteByPlaca(@PathVariable("placa") String placa){

        service.deleteByPlaca(placa);
    }

    @PutMapping("{placa}/multas")
    public ResponseEntity<MultaResponse> gerarMulta(@PathVariable("placa") String placa){
        Veiculos veiculo = service.gerarMulta(placa);
        MultaResponse resp = mapper.map(veiculo, MultaResponse.class);

        return ResponseEntity.ok(resp);

    }


}
