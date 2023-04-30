package com.exercicios.exerciciostestesunitarios.services;

import com.exercicios.exerciciostestesunitarios.exceptions.VeiculoNaoEncontradoException;
import com.exercicios.exerciciostestesunitarios.models.Veiculos;
import com.exercicios.exerciciostestesunitarios.repositories.VeiculoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VeiculoService {

    @Autowired
    private VeiculoRepository repo;

    public List<Veiculos> findAll(){
        List<Veiculos> veiculos = repo.findAll();
        log.info("Foram encontrados {} resultados", veiculos.size());
        return veiculos;

    }

    public Veiculos save(Veiculos veiculo) {
        Optional<Veiculos> veiculoOpt = repo.findByPlaca(veiculo.getPlaca());

        if (veiculoOpt.isPresent()){
            log.error("Veículo com placa {} já cadastrado.", veiculo.getPlaca());
            return veiculoOpt.get();
        }


        log.info("Veículo com placa {} cadastrado com sucesso.", veiculo.getPlaca());
        Veiculos retornoDoBanco = repo.save(veiculo);
        return retornoDoBanco;
    }

    public Veiculos findByPlaca(String placa) throws Exception {
         Optional<Veiculos> veiculoOpt = repo.findByPlaca(placa);

         if (veiculoOpt.isEmpty()){

             log.error("Veículo com placa {} não foi encontrado.", placa);
             throw new VeiculoNaoEncontradoException("Veículo com placa "+ placa +" não foi encontrado.");
         }

        Veiculos veiculo = veiculoOpt.get();
        log.info("Veículo com placa {} retornado: {}", placa, veiculo);

        return veiculo;
    }

    public void deleteByPlaca(String placa) {
        repo.deleteById(placa);
    }


    public Veiculos gerarMulta(String placa) {
        Optional<Veiculos> veiculoOpt = repo.findByPlaca(placa);
        Integer multas = veiculoOpt.get().getQtdMultas();
        veiculoOpt.get().setQtdMultas(++multas);

        return repo.save(veiculoOpt.get());
    }
}
