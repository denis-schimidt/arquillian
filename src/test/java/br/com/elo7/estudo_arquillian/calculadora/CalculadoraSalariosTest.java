package br.com.elo7.estudo_arquillian.calculadora;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.elo7.estudo_arquillian.model.Escolaridade;
import br.com.elo7.estudo_arquillian.model.Funcionario;

@RunWith(Arquillian.class)
public class CalculadoraSalariosTest {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClass(Funcionario.class)
				.addClass(CalculadoraSalariosDezPorCento.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(jar.toString(true));

		return jar;
	}

	@Inject
	private CalculadoraSalariosDezPorCento calculadoraSalarios;

	@Test
	public void deveAumentarSalarioBaseEmDezPorCento() {
		Funcionario funcionario = new Funcionario("Fulano", Escolaridade.MEDIO, 4000.0);
		double salario = calculadoraSalarios.calculaSalario(funcionario);
		
		assertEquals(4400.0, salario, 0.001);
	}
}
