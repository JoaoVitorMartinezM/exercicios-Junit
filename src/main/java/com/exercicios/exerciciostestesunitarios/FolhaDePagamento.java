package com.exercicios.exerciciostestesunitarios;

import java.util.List;

public class FolhaDePagamento {

    public Double calcularSalarioBruto(Double salarioBase, Double gratificacao, Funcao funcao) {
        Double salario = salarioBase;
        if (gratificacao != null) {
            salario += gratificacao;
        }
        if (Funcao.GERENTE.equals(funcao)) {
            salario *= 1.30;
        }
        return salario;
    }

    public Double calcularSalarioLiquido(Double salario, List<Double> descontos) {
        if (descontos == null || descontos.isEmpty()) {
            return salario;
        }
        for(Double desconto : descontos) {
            salario -= desconto;
        }
        if (salario < 0) {
            throw new IllegalStateException();
        }
        return salario;
    }
}
