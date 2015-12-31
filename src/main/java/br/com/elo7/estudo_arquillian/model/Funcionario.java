package br.com.elo7.estudo_arquillian.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue
	@Column(updatable=false)
	private Long id;
	
	@NotBlank
	@Size(min=4, max=50)
	@Column(length=50, nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private Escolaridade escolaridade;
	
	@Min(700) @Max(100_000)
	@Column(nullable=false)
	private double salario;
	
	//JPA Apenas
	Funcionario() {}
	
	public Funcionario(Long id) {
		this.id = id;
	}

	public Funcionario( String nome, Escolaridade escolaridade, double salario) {
		this.nome = nome;
		this.escolaridade = escolaridade;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public double getSalario() {
		return salario;
	}

	@Override
	public String toString() {
		return String.format("Funcionario [id=%s, nome=%s, escolaridade=%s, salario=%s]", id, nome, escolaridade,
				salario);
	}
}
