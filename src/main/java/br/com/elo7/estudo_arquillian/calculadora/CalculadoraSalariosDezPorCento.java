package br.com.elo7.estudo_arquillian.calculadora;

import br.com.elo7.estudo_arquillian.model.Funcionario;

public class CalculadoraSalariosDezPorCento implements CalculadoraSalarios {
	private static final long serialVersionUID = 1L;

	@Override
	public double calculaSalario(Funcionario funcionario) {
		return funcionario.getSalario() * 1.1;
	}
}
