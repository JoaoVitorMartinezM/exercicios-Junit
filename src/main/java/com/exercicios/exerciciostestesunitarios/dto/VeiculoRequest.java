package com.exercicios.exerciciostestesunitarios.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VeiculoRequest {

    String placa;
    String tipo;
    String cor;
    Integer anoDeFabricacao;
    Integer qtdMultas;
}
