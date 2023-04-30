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
    @DisplayName("Quando existe o registro, deve ser excluído.")
    void excluiRegistroQuandoExistente(){

        String placa = "placa";
        Mockito.when(

        )
        Mockito.when(repo.findByPlaca(placa)).thenReturn(Optional.of(new Veiculos()));
        assertDoesNotThrow(() -> service.deleteByPlaca(placa));
    }


}
