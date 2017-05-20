package posjava.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GARAGEM")
public class Garagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAR_ID")
	private Long id;
	private Long numero;
	private String localizacao;

	@OneToOne
	@JoinColumn(name = "EMPREGADO_ID", referencedColumnName = "EMP_ID" )
	private Empregado empregado;

	public Long getId() {
		return id;
	}

	public Garagem setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getNumero() {
		return numero;
	}

	public Garagem setNumero(Long numero) {
		this.numero = numero;
		return this;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public Garagem setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
		return this;
	}

	public Empregado getEmpregado() {
		return empregado;
	}

	public Garagem setEmpregado(Empregado empregado) {
		this.empregado = empregado;
		return this;
	}
}
