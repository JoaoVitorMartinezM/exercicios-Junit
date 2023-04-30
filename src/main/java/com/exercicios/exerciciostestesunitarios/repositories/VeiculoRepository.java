package com.exercicios.exerciciostestesunitarios.repositories;

import com.exercicios.exerciciostestesunitarios.models.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculos, String> {

    List<Veiculos> findAll();

    Veiculos save(Veiculos veiculo);

    Optional<Veiculos> findByPlaca(String placa);

    void deleteById(String placa);
}
