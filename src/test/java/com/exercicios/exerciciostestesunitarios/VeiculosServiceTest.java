package com.exercicios.exerciciostestesunitarios;

import com.exercicios.exerciciostestesunitarios.models.Veiculos;
import com.exercicios.exerciciostestesunitarios.repositories.VeiculoRepository;
import com.exercicios.exerciciostestesunitarios.services.VeiculoService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class VeiculosServiceTest {

    @Mock
    private VeiculoRepository repo;

    @InjectMocks
    private VeiculoService service;



    @Test
    @DisplayName("Quando não tem registros, retorna a lista vazia")
    void consultarListaVazia(){
        Mockito.when(repo.findAll()).thenReturn(new ArrayList<>());

        List<Veiculos> veiculos = service.findAll();
        assertNotNull(veiculos);
        assertTrue(veiculos.isEmpty());
    }

    @Test
    @DisplayName("Quando tem registros, retorna uma lista")
    void consultarResultados(){
        Mockito.when(repo.findAll()).thenReturn(List.of(new Veiculos("123qwe", "carro", "preto", 2011, 0)));

        List<Veiculos> veiculos = service.findAll();
        assertNotNull(veiculos);
        assertFalse(veiculos.isEmpty());
        assertTrue(veiculos.size() == 1);
        assertTrue(veiculos.get(0).getPlaca() == "123qwe");

    }

//    @Test
//    @DisplayName("Quando tenta cadastrar um veículo existente, retorna o veículo")
//    void cadastrarVeiculoQueJaExiste(){
//        Veiculos veiculo = new Veiculos("123qwe", "carro", "preto", 2011, 0);
//        Mockito.when(repo.save(veiculo)).thenReturn(veiculo);
//        Mockito.when(repo.findByPlaca("werf2345")).thenReturn(Optional.of(veiculo));
//
//
//    }

    @Test
    @DisplayName("Quando existe o registro, deve ser excluído.")
    void excluiRegistroQuandoExistente(){


    }


}
