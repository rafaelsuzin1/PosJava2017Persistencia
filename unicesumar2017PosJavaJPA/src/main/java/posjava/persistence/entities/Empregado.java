package posjava.persistence.entities;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPREGADO")
public class Empregado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_ID")
	private Long id;

	@Column(name = "EMP_NOME")
	private String nome;

	@Column(name = "EMP_SALARIO")
	private BigDecimal salario;

	@Column(name = "EMP_COMENTARIO")
	@Basic(fetch = FetchType.EAGER)
	private String comentario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GARAGEM_ID")
	private Departamento departamento;

	@OneToOne(mappedBy = "empregado")
	@JoinColumn(name = "GARAGEM_ID", referencedColumnName = "GAR_ID")
	private Garagem garagem;

	@ManyToMany
	@JoinTable(name = "EMP_PROJ", joinColumns = { @JoinColumn(name = "EMP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJ_ID") })
	private Collection<Projeto> projetos;

	public Long getId() {
		return id;
	}

	public Collection<Projeto> getProjetos() {
		return projetos;
	}

	public Empregado setProjetos(Collection<Projeto> projetos) {
		this.projetos = projetos;
		return this;
	}

	public Empregado setId(Long id) {
		this.id = id;
		return this;
	}

	public String getNome() {
		return nome;
	}

	public Empregado setNome(String nome) {
		this.nome = nome;
		return this;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Empregado setSalario(BigDecimal salario) {
		this.salario = salario;
		return this;
	}

	public String getComentario() {
		return comentario;
	}

	public Empregado setComentario(String comentario) {
		this.comentario = comentario;
		return this;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public Empregado setDepartamento(Departamento departamento) {
		this.departamento = departamento;
		return this;
	}

	public Garagem getGaragem() {
		return garagem;
	}

	public void setGaragem(Garagem garagem) {
		this.garagem = garagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empregado other = (Empregado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
