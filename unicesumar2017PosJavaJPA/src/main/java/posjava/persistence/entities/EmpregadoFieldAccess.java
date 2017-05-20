package posjava.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmpregadoFieldAccess {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nome;
	private BigDecimal salario;
	public Long getId() {
		return id;
	}
	public EmpregadoFieldAccess setId(Long id) {
		this.id = id;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public EmpregadoFieldAccess setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public EmpregadoFieldAccess setSalario(BigDecimal salario) {
		this.salario = salario;
		return this;
	}
	
}
