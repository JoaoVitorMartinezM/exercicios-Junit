package com.exercicios.exerciciostestesunitarios.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "VEICULOS")
public class Veiculos {

    @Id
    String placa;
    String tipo;
    String cor;
    Integer anoDeFabricacao;
    Integer qtdMultas;



}
