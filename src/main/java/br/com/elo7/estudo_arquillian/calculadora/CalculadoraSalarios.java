package br.com.elo7.estudo_arquillian.calculadora;

import java.io.Serializable;

import br.com.elo7.estudo_arquillian.model.Funcionario;

public interface CalculadoraSalarios extends Serializable{

	public double calculaSalario( Funcionario funcionario );
}
