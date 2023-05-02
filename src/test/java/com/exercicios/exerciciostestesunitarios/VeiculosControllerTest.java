package com.exercicios.exerciciostestesunitarios;

import com.exercicios.exerciciostestesunitarios.dto.VeiculoRequest;
import com.exercicios.exerciciostestesunitarios.exceptions.VeiculoNaoEncontradoException;
import com.exercicios.exerciciostestesunitarios.models.Veiculos;
import com.exercicios.exerciciostestesunitarios.services.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.List.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class VeiculosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;

    @Test
    @DisplayName("Quando não há registros retorna uma lista vazia")
    void consultarSemRegistro() throws Exception {
        mockMvc.perform(
                get("/api/veiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$", is(empty())
                ));
    }

    @Test
    @DisplayName("Quando há registros retorna uma lista com veículo")
    void consultarComRegistro() throws Exception {
        var Veiculos = List.of(new Veiculos("ABC1I21", "caminhao", "branco", 2010, 0));
        Mockito.when(service.findAll()).thenReturn(Veiculos);
        mockMvc.perform(
                        get("/api/veiculos")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                        "$", hasSize(1)
                ))
                .andExpect(jsonPath("$[0].tipo", is(Veiculos.get(0).getTipo())));
    }

    @Test
    @DisplayName("Quando procura pela placa, retorna o veiculo")
    void consultaPelaPlaca() throws Exception {
        String placa = "ABC";
        Mockito.when(service.findByPlaca(placa)).thenReturn(new Veiculos("ABC", "caminhao", "branco", 2011, 0));
        mockMvc.perform(
                get("/api/veiculos/ABC")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.placa", is(placa) ));
    }

    @Test
    @DisplayName("Quando procura por placa não cadastrada, retorna exceção")
    void consultaPorPlacaNaoCadastrada() throws Exception {
        String placa = "ABC";
        Mockito.when(service.findByPlaca(Mockito.anyString())).thenThrow(VeiculoNaoEncontradoException.class);
        mockMvc.perform(
                        get("/api/veiculos/{placa}", placa)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

//    @Test
//    @DisplayName("Quando cadastra um novo veículo, retorna o cadastro")
//    void cadastra() throws Exception {
//        Veiculos veiculo = new Veiculos("ABC", "carro", "branco", 2010, 1);
//        Mockito.when(service.save(veiculo)).thenReturn(veiculo);
//
//        VeiculoRequest request = mapper.map(veiculo, VeiculoRequest.class);
//
//        mockMvc.perform(
//                        post("/api/veiculos", request)
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated());
//    }

    @Test
    @DisplayName("Deleta o registro quando a placa existe")
    void deleta() throws Exception {
        String placa = "ABC";
        mockMvc.perform(
                        delete("/api/veiculos/{placa}", placa)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Adiciona multa ao veiculo")
    void gerarMulta() throws Exception {

        Veiculos veiculo = new Veiculos("ABC", "carro", "preto", 2010, 1);
        Mockito.when(service.gerarMulta("ABC")).thenReturn(veiculo);
        mockMvc.perform(
                        put("/api/veiculos/{placa}/multas", veiculo.getPlaca())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qtdMultas", is(veiculo.getQtdMultas())));
    }

}
