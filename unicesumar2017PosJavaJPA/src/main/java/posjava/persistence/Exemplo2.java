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

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("posjavaPU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		List<Departamento> departamentos = new ArrayList<Departamento>();
		for (int i = 0; i < 4; i++) {
			departamentos.add(new Departamento().setNome("Departamento " + i));
		}

		departamentos.stream().forEach(d -> {
			em.persist(d);
		});
		tx1.commit();

		tx1 = em.getTransaction();
		tx1.begin();
		List<Garagem> garagens = new ArrayList<Garagem>();
		for (int i = 0; i < 10; i++) {
			garagens.add(new Garagem().setLocalizacao("Local " + i).setNumero(Long.valueOf(i)));
		}

		garagens.stream().forEach(g -> {
			em.persist(g);
		});
		tx1.commit();

		tx1 = em.getTransaction();
		tx1.begin();
		List<Projeto> projetos = new ArrayList<Projeto>();
		for (int i = 0; i < 5; i++) {
			projetos.add(new Projeto().setNome("Projeto " + i));
		}

		projetos.stream().forEach(p -> {
			em.persist(p);
		});
		tx1.commit();

		tx1 = em.getTransaction();
		tx1.begin();
		List<Empregado> empregados = new ArrayList<Empregado>();
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

			empregados.add(new Empregado().setComentario("Comentario " + i).setDepartamento(departamentos.get(depart))
					.setGaragem(garagens.get(garag)).setProjetos(projs).setNome("Empregado " + 1)
					.setSalario(new BigDecimal("10")));
			depart++;
			garag++;
			proj++;
		}

		empregados.stream().forEach(e -> {
			em.persist(e);
		});

		tx1.commit();
	}
}
