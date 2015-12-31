package br.com.elo7.estudo_arquillian.calculadora;

import static org.junit.Assert.*;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.elo7.estudo_arquillian.model.Funcionario;

@RunWith(Arquillian.class)
public class AmbiguidadeTest {

	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClass(Funcionario.class)
				.addClass(CalculadoraSalarios.class)
				.addClass(CalculadoraSalariosDezPorCento.class)
				.addClass(CalculadoraSalariosVintePorCento.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(jar.toString(true));

		return jar;
	}

	@Inject
	private Instance<CalculadoraSalarios> calculadoraSalarios;

	@Test
	public void deveApresentarAmbiguidade() {
		assertTrue(calculadoraSalarios.isAmbiguous());
	}

	@Test
	public void naoDeveApresentarAmbiguidade() {
		Instance<? extends CalculadoraSalarios> calculadoraEspecifica = calculadoraSalarios
				.select(CalculadoraSalariosVintePorCento.class);
		assertFalse(calculadoraEspecifica.isAmbiguous());
	}
}
