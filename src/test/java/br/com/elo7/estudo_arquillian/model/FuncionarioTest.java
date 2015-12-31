package br.com.elo7.estudo_arquillian.model;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.com.elo7.estudo_arquillian.calculadora.CalculadoraSalarios;
import br.com.elo7.estudo_arquillian.calculadora.CalculadoraSalariosDezPorCento;
import br.com.elo7.estudo_arquillian.producer.LoggerProducer;

@RunWith(Arquillian.class)
public class FuncionarioTest {

	@PersistenceContext(unitName = "testPU")
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	@Deployment
	public static WebArchive createDeployment() {
		WebArchive jar = ShrinkWrap.create(WebArchive.class, "test.war")
				.addClass(Funcionario.class)
				.addClass(CalculadoraSalarios.class)
				.addClass(CalculadoraSalariosDezPorCento.class)
				.addClass(LoggerProducer.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
	            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

		System.out.println(jar.toString(true));

		return jar;
	}
	
	@Test
	@Transactional
	public void deveIncluirRecuperarFuncionariosUsandoTransacao(){
		TypedQuery<Funcionario> query =	em.createQuery("select f from Funcionario f", Funcionario.class);
		
		assertEquals(0, query.getResultList().size());
		
		Funcionario funcionario1 = new Funcionario("Xico", Escolaridade.MEDIO, 2000.0);
		Funcionario funcionario2 = new Funcionario("Maria", Escolaridade.SUPERIOR_COMPLETO, 4000.0);
		
		em.persist(funcionario1);
		em.persist(funcionario2);
		
		assertThat(funcionario1.getId(), equalTo(1L));
		assertThat(funcionario2.getId(), equalTo(2L));
		
		for ( Funcionario funcionarioRecuperado : query.getResultList() ) {
			log.info( funcionarioRecuperado.toString() );
		}

		assertThat(query.getResultList().size(), equalTo(2));
	}
}
