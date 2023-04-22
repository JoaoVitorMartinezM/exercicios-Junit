package com.exercicios.exerciciostestesunitarios;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolhaDePagamentoTest {

    FolhaDePagamento pagamento = new FolhaDePagamento();

    @Test
    @DisplayName("Calcular salario quando a gratificação for null e a função for atendende")
    void calcularSalarioBrutoQuandoSemGratificacaoENaoEhGerente() {
        //when
        Double salario = pagamento.calcularSalarioBruto(1000.00, null, Funcao.ATENDENTE);
        assertEquals(1000.00, salario);


    }

    @Test
    @DisplayName("Calcular salario quando a gratificação for null e a função for gerente")
    void calcularSalarioBrutoQuandoEhGerente() {
        //when
        Double salario = pagamento.calcularSalarioBruto(1000.00, null, Funcao.GERENTE);
        assertEquals(1000.00 * 1.30, salario);


    }
    @Test
    @DisplayName("Calcular salario quando tem gratificação e a função for gerente")
    void calcularSalarioBrutoComGratificacaoQuandoEhGerente() {
        //when
        Double salario = pagamento.calcularSalarioBruto(1000.00, 50.00, Funcao.GERENTE);
        assertEquals((1000.00 + 50.00) * 1.30, salario);


    }


    @Test
    @DisplayName("Quando salário é negativo lança exceção")
    void calcularSalarioLiquidoQuandoSalarioEhNegativo() {

        List<Double> descontos = List.of(50.0, 100.0);
        assertThrows(IllegalStateException.class, () -> pagamento.calcularSalarioLiquido(-1000.0, descontos) );

    }

    @Test
    @DisplayName("Quando tem desconto retorna a subtração do salário")
    void calcularSalarioLiquidoQuandoTemDesconto() {

        List<Double> descontos = List.of(50.0, 100.0);
        Double salarioComDesconto = pagamento.calcularSalarioLiquido(1000.0, descontos);
        assertEquals(850.0, salarioComDesconto );

    }

    @Test
    @DisplayName("Quando desconto é nulo retorna o salário sem alteração")
    void calcularSalarioLiquidoQuandoDescontoEhNulo() {

        List<Double> descontos = null;
        Double salario = pagamento.calcularSalarioLiquido(1000.0, descontos);
        assertEquals(1000, salario );


    }
}