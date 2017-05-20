package posjava.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import posjava.persistence.entities.Departamento;
import posjava.persistence.entities.Empregado;
import posjava.persistence.entities.Garagem;
import posjava.persistence.entities.Projeto;

public class Exemplo2 {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("posjavaPU");
	static EntityManager em = emf.createEntityManager();

	private static Garagem getGaragem(Long id) {
		return  (Garagem) em.createQuery("select g from Garagem g where g.id = " + id).getSingleResult();
	}

	public static void main(String[] args) {

		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		List<Departamento> departamentos = new ArrayList<Departamento>();
		for (int i = 0; i < 4; i++) {
			departamentos.add(new Departamento().setNome("Departamento " + i));
		}

		departamentos.stream().forEach(d -> {
			em.persist(d);
			em.flush();
		});

		List<Garagem> garagens = new ArrayList<Garagem>();
		for (int i = 0; i < 10; i++) {
			garagens.add(new Garagem().setLocalizacao("Local " + i).setNumero(Long.valueOf(i)));
		}

		garagens.stream().forEach(g -> {
			em.persist(g);
			em.flush();
		});
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (int i = 0; i < 5; i++) {
			projetos.add(new Projeto().setNome("Projeto " + i));
		}

		projetos.stream().forEach(p -> {
			em.persist(p);
		});

		int depart = 0;
		int garag = 0;
		int proj = 0;
		for (int i = 0; i < 8; i++) {
			if (depart == 4)
				depart = 0;

			if (proj == 5)
				proj = 0;

			List<Projeto> projs = new ArrayList<Projeto>();
			for (Projeto projeto : projetos) {
				if (projetos.indexOf(projeto) <= proj) {
					projs.add(projeto);
				} else
					break;
			}

			Empregado empregado = new Empregado().setComentario("Comentario " + i)
					.setDepartamento(departamentos.get(depart)).setProjetos(projs).setNome("Empregado " + 1)
					.setSalario(new BigDecimal("10"));
			Garagem garagem = garagens.get(garag);
			empregado.setGaragem(garagem);
			em.persist(empregado);
			em.flush();
			garagem.setEmpregado(empregado);
			em.persist(garagem);
			em.flush();
			depart++;
			garag++;
			proj++;
		}
		tx1.commit();
	}
}
